/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.samples.porter.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.servicecomb.foundation.common.utils.JsonUtils;
import org.apache.servicecomb.samples.porter.user.api.SessionInfo;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import reactor.core.publisher.Mono;

@Configuration
public class RouteConfiguration {
  // session expires in 10 minutes, cache for 1 seconds to get rid of concurrent scenarios.
  private Cache<String, String> sessionCache = CacheBuilder.newBuilder()
      .expireAfterAccess(30, TimeUnit.SECONDS)
      .build();

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user-service", r -> r
            .path("/api/user-service/**")
            .filters(f -> f.stripPrefix(2))
            .uri("lb://user-service"))
        .route("file-service", r -> r
            .path("/api/file-service/**")
            .filters(f -> f.stripPrefix(2))
            .uri("lb://file-service"))
        .route("porter-website", r -> r
            .path("/ui/**")
            .filters(f -> f.stripPrefix(0))
            .uri("lb://porter-website"))
        .build();
  }

  @Bean
  public GlobalFilter globalFilter() {
    return new AuthFilter();
  }

  class AuthFilter implements GlobalFilter, Ordered {
    public AuthFilter() {
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

      ServerHttpRequest request = exchange.getRequest();
      if (request.getPath().value().equals("/api/user-service/v1/user/login")
          || request.getPath().value().equals("/api/user-service/v1/user/session")
          || request.getPath().value().startsWith("/ui")) {
        return chain.filter(exchange);
      } else {
        HttpCookie cookie = request.getCookies().getFirst("session-id");
        String sessionId = cookie != null ? cookie.getValue() : null;
        if (StringUtils.isEmpty(sessionId)) {
          ServerHttpResponse response = exchange.getResponse();
          response.setStatusCode(HttpStatus.FORBIDDEN);
          return response.setComplete();
        }

        String sessionInfo = sessionCache.getIfPresent(sessionId);
        if (sessionInfo == null) {
          sessionInfo = getAndSaveSessionInfo(sessionId);
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
      return 0;
    }

    private String getAndSaveSessionInfo(String sessionId) {
      RestTemplate restTemplate = new RestTemplate();
      SessionInfo sessionInfo = restTemplate
          .getForObject("http://localhost:8080/v1/user/session?sessionId=" + sessionId, SessionInfo.class);
      String sessionInfoStr = writeJson(sessionInfo);
      sessionCache.put(sessionId, sessionInfoStr);
      return sessionInfoStr;
    }

    private String writeJson(Object o) {
      try {
        return JsonUtils.writeValueAsString(o);
      } catch (Exception ee) {
        ee.printStackTrace();
      }
      return null;
    }
  }
}
