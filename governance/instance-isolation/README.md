# Description
This project providers sample to show working with instance-isolation Deploy. 

* provider-error
  A Microserivce using Spring Cloud with a REST interface.

* no-isolation-provider
  A Microserivce using Spring Cloud with a REST interface.

* isolation-provider
  A Microserivce using Spring Cloud with a REST interface.

* isolation-consumer
  A Microserivce using Spring Cloud with a REST interface. Consumer calls isolation-provider/no-isolation-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run isolation-provider

  In ${Project}/isolation-provider/target/
  
        java -jar isolation-provider-1.0-SNAPSHOT.jar

* Run no-isolation-provider

  In ${Project}/no-isolation-provider/target/
  
        java -jar no-isolation-provider-1.0-SNAPSHOT.jar

* Run provider-error

  In ${Project}/provider-error/target/

        java -jar provider-error-1.0-SNAPSHOT.jar

* Run isolation-consumer

  In ${Project}/isolation-consumer/target/

        java -jar isolation-consumer-1.0-SNAPSHOT.jar

* Testing

After the four microservices are started, access the page based on the API defined by the consumer and observe the returned result. 
The URI is http://localhost:8090/low-minimum-calls. You are advised to restart the consumer service after each API test to prevent instance isolation from affecting the test result.

# 项目说明

这个项目提供了 Spring Cloud Huawei 客户端实例隔离的例子。

* isolation-provider
使用 Spring Cloud 开发一个 REST 接口。

* no-isolation-provider
使用 Spring Cloud 开发一个 REST 接口。

* provider-error
使用 Spring Cloud 开发一个 REST 接口。

* isolation-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 isolation-provider/no-isolation-provider 的接口。 

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 isolation-provider

  进入目录 ${Project}/isolation-provider/target/
         
        java -jar isolation-provider-1.0-SNAPSHOT.jar

* 启动 no-isolation-provider

  进入目录 ${Project}/no-isolation-provider/target/
         
        java -jar no-isolation-provider-1.0-SNAPSHOT.jar

* 启动 provider-error

  进入目录 ${Project}/provider-error/target/

        java -jar provider-error-1.0-SNAPSHOT.jar

* 启动 isolation-consumer

  进入目录 ${Project}/isolation-consumer/target/
       
        java -jar isolation-consumer-1.0-SNAPSHOT.jar

* 测试

启动4个微服务后， 然后根据consumer定义的接口，通过界面访问观察返回结果，参考uri： http://localhost:8090/low-minimum-calls。 每个接口测试完建议重启consumer服务，以免有实例隔离影响测试结果。
