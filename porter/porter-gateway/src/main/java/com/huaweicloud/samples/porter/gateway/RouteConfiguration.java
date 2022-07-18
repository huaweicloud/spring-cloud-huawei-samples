package com.huaweicloud.samples.porter.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.foundation.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.huaweicloud.samples.porter.user.api.SessionInfo;

import reactor.core.publisher.Mono;

@Configuration
public class RouteConfiguration {
  private static final Logger LOGGER = LoggerFactory.getLogger(RouteConfiguration.class);

  // session expires in 10 minutes, cache for 1 seconds to get rid of concurrent scenarios.
  private final Cache<String, String> sessionCache = CacheBuilder.newBuilder()
      .expireAfterAccess(30, TimeUnit.SECONDS)
      .build();

  @Bean
  public GlobalFilter globalFilter(RestTemplate restTemplate) {
    return new AuthFilter(restTemplate);
  }

  class AuthFilter implements GlobalFilter, Ordered {
    private final RestTemplate restTemplate;

    public AuthFilter(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

      ServerHttpRequest request = exchange.getRequest();
      if (request.getPath().value().equals("/v1/user/login")
          || request.getPath().value().equals("/v1/user/session")
          || request.getPath().value().startsWith("/porter")) {
        return chain.filter(exchange);
      } else {
        HttpCookie cookie = request.getCookies().getFirst("session-id");
        String sessionId = cookie != null ? cookie.getValue() : null;
        if (StringUtils.isEmpty(sessionId)) {
          ServerHttpResponse response = exchange.getResponse();
          response.setRawStatusCode(403);
          return response.setComplete();
        }

        String sessionInfo = sessionCache.getIfPresent(sessionId);
        if (sessionInfo == null) {
          sessionInfo = getAndSaveSessionInfo(sessionId);
        }
        if (sessionInfo == null) {
          ServerHttpResponse response = exchange.getResponse();
          response.setRawStatusCode(403);
          return response.setComplete();
        }
        Map<String, String> cseContext = new HashMap<>();
        cseContext.put("session-id", sessionId);
        cseContext.put("session-info", sessionInfo);
        ServerHttpRequest nextRequest = exchange.getRequest().mutate()
            .header("x-cse-context", writeJson(cseContext))
            .build();
        ServerWebExchange nextExchange = exchange.mutate().request(nextRequest).build();
        return chain.filter(nextExchange);
      }
    }

    @Override
    public int getOrder() {
      return Ordered.HIGHEST_PRECEDENCE;
    }

    private String getAndSaveSessionInfo(String sessionId) {
      SessionInfo sessionInfo = this.restTemplate
          .getForObject("http://user-core/v1/user/session?sessionId=" + sessionId, SessionInfo.class);
      if (sessionInfo == null) {
        return null;
      }
      String sessionInfoStr = writeJson(sessionInfo);
      if (sessionInfoStr == null) {
        return null;
      }
      sessionCache.put(sessionId, sessionInfoStr);
      return sessionInfoStr;
    }

    private String writeJson(Object o) {
      try {
        return JsonUtils.writeValueAsString(o);
      } catch (Exception ee) {
        LOGGER.error("Unexpected error", ee);
      }
      return null;
    }
  }
}
