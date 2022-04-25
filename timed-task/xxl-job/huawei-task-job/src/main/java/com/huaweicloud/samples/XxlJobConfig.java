package com.huaweicloud.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

@Configuration
public class XxlJobConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobConfig.class);

  @Value("${xxl.job.admin.addresses}")
  private String adminAddresses;

  @Value("${xxl.job.accessToken}")
  private String accessToken;

  @Value("${xxl.job.executor.appName}")
  private String appName;

  @Value("${xxl.job.executor.address}")
  private String address;

  @Value("${xxl.job.executor.ip}")
  private String ip;

  @Value("${xxl.job.executor.port}")
  private int port;

  @Value("${xxl.job.executor.logpath}")
  private String logPath;

  @Value("${xxl.job.executor.logretentiondays}")
  private int logRetentionDays;


  @Bean
  public XxlJobSpringExecutor xxlJobExecutor() {
    LOGGER.info(">>>>>>>>>>> xxl-job config init.");
    XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
    xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
    xxlJobSpringExecutor.setAppname(appName);
    xxlJobSpringExecutor.setAddress(address);
    xxlJobSpringExecutor.setIp(ip);
    xxlJobSpringExecutor.setPort(port);
    xxlJobSpringExecutor.setAccessToken(accessToken);
    xxlJobSpringExecutor.setLogPath(logPath);
    xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

    return xxlJobSpringExecutor;
  }

}
