package com.huaweicloud.sample;

import java.time.LocalDateTime;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

@Component
public class MyJob implements SimpleJob {

  @Override
  public void execute(ShardingContext shardingContext) {
    switch (shardingContext.getShardingItem()) {
      case 0:
        System.out.printf("%s %s%s %s%s %s%n", LocalDateTime.now(), "作业分片-", shardingContext.getShardingItem(),
            "分片参数-", shardingContext.getShardingParameter(),
            "Elastic-Job One, Hello World Huawei.");
        break;
      case 1:
        System.out.printf("%s %s%s %s%s %s%n", LocalDateTime.now(), "作业分片-", shardingContext.getShardingItem(),
            "分片参数-", shardingContext.getShardingParameter(),
            "Elastic-Job Two, Hello World Huawei.");
        break;
    }
  }
}
