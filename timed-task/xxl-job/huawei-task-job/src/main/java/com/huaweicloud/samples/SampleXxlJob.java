package com.huaweicloud.samples;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;

@Component
public class SampleXxlJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleXxlJob.class);

  /**
   * 简单任务示例（Bean模式）
   */
  @XxlJob("huaweiDemoJobHandlerBean")
  public void demoJobHandler() throws Exception {
    XxlJobHelper.log("XXL-JOB Bean, Hello World huawei.");

    for (int i = 0; i < 5; i++) {
      XxlJobHelper.log("beat at:" + i);
      TimeUnit.SECONDS.sleep(2);
    }
  }

  /**
   * 生命周期任务示例：任务初始化与销毁时，支持自定义相关逻辑；
   */
  @XxlJob(value = "huaweiDemoJobHandlerLifeCycle", init = "init", destroy = "destroy")
  public void demoJobHandler2() throws Exception {
    XxlJobHelper.log("XXL-JOB Life Cycle, Hello World Huawei.");
  }

  public void init() {
    LOGGER.info("init");
  }

  public void destroy() {
    LOGGER.info("destory");
  }
}
