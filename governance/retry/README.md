# Description
This project providers sample to show working with retry Deploy. 

* retry-provider
A Microserivce using Spring Cloud with a REST interface.

* retry-provider-error
Same as retry-provider, but with version 0.0.2 and return with error code.

* retry-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls retry-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run retry-provider

  In ${Project}/retry-provider/target/
  
        java -jar retry-provider-1.0-SNAPSHOT.jar

* Run provider-two

  In ${Project}/retry-provider-error/target/
  
        java -jar retry-provider-error-1.0-SNAPSHOT.jar

* Run retry-consumer

  In ${Project}/retry-consumer/target/

        java -jar retry-consumer-1.0-SNAPSHOT.jar

* Testing

After the four microservices are started, access the page based on the API defined by the consumer and observe the returned result.
The URI is http://localhost:8090/retry. 

# 项目说明

这个项目提供了 Spring Cloud Huawei 灰度发布的例子。

* retry-provider
使用 Spring Cloud 开发一个 REST 接口。

* retry-provider-error
  retry-provider的0.0.2版本，返回错误结果服务。 

* retry-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 retry-provider 的接口。 

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 retry-provider

  进入目录 ${Project}/retry-provider/target/
         
        java -jar retry-provider-1.0-SNAPSHOT.jar

* 启动 retry-provider-error

  进入目录 ${Project}/retry-provider-error/target/
         
        java -jar retry-provider-error-1.0-SNAPSHOT.jar

* 启动 retry-consumer

  进入目录 ${Project}/retry-consumer/target/
       
        java -jar retry-consumer-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后， 然后根据consumer定义的接口，通过界面访问观察返回结果，参考uri： http://localhost:8090/retry。
