# Description
This project providers sample to show working with fault-injection Deploy. 

* fault-provider
A Microserivce using Spring Cloud with a REST interface.

* normal-provider
  A Microserivce using Spring Cloud with a REST interface.

* fault-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls fault-provider/normal-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run fault-provider

  In ${Project}/fault-provider/target/
  
        java -jar fault-provider-1.0-SNAPSHOT.jar

* Run normal-provider

  In ${Project}/normal-provider/target/

        java -jar normal-provider-1.0-SNAPSHOT.jar

* Run fault-consumer

  In ${Project}/fault-consumer/target/
  
        java -jar fault-consumer-1.0-SNAPSHOT.jar

* Testing

Open in browser：  

http://localhost:9090/fault-exception。 Test the scenario where an exception is returned during fault injection. For details, see the description of the consumer API.

http://localhost:9090/fault-null。 Test the scenario where null is returned during fault injection. For details, see the description of the consumer API.

http://localhost:9090/fault-percent。 Test the scenario where the service is fault injection by percentage. For details, see the description of the consumer API.

http://localhost:9090/fault-service-name。 Test the scenario where the fault injection takes effect based on the service name. For details, see the consumer interface description.

http://localhost:9090/fault-force。 Test the scenario where the fault injection policy forcibly disables the switch. For details, see the consumer interface description.

# 项目说明

这个项目提供了 Spring Cloud Huawei 客户端降级的例子。

* fault-provider
使用 Spring Cloud 开发一个 REST 接口。

* normal-provider
使用 Spring Cloud 开发一个 REST 接口。

* fault-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 fault-provider/normal-provider 的接口。

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 fault-provider

  进入目录 ${Project}/fault-provider/target/
         
        java -jar fault-provider-1.0-SNAPSHOT.jar

* 启动 normal-provider

  进入目录 ${Project}/normal-provider/target/

        java -jar normal-provider-1.0-SNAPSHOT.jar

* 启动 fault-consumer

  进入目录 ${Project}/fault-consumer/target/
         
        java -jar fault-consumer-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后，然后通过界面访问：

http://localhost:9090/fault-exception。 测试降级返回异常场景，详细情况见consumer接口说明。

http://localhost:9090/fault-null。 测试降级返回null场景，详细情况见consumer接口说明。

http://localhost:9090/fault-percent。 测试按百分比降级场景，详细情况见consumer接口说明。

http://localhost:9090/fault-service-name。 测试根据服务名生效降级策略场景，详细情况见consumer接口说明。

http://localhost:9090/fault-force。 测试降级策略强制关闭开关场景，详细情况见consumer接口说明。
