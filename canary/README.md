# Description
This project providers sample to show working with Canary Deploy. 

* canary-provider
A Microserivce using Spring Cloud with a REST interface.

* canary-provider-beta
Same as canary-provider, but with version 0.0.2

* canary-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../README.md)

* Build

        mvn clean package

* Run provider

  In ${Project}/canary-provider/target/
  
        java -jar canary-provider-1.0-SNAPSHOT.jar

* Run provider

  In ${Project}/canary-provider-beta/target/
  
        java -jar canary-provider-beta-1.0-SNAPSHOT.jar
        
* Run consumer

  In ${Project}/canary-consumer/target/

        java -jar canary-consumer-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:8091/canary?id=2

About 20% result output by provider-beta, and 80% result output by provider. 

# 项目说明

这个项目提供了 Spring Cloud Huawei 灰度发布的例子。

* canary-provider
使用 Spring Cloud 开发一个 REST 接口。

* canary-provider-beta
canary-provider的0.0.2版本。 

* canary-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。 

## 使用

* 前提条件
[准备CSE运行环境](../README_CN.md)

* 编译

        mvn clean pacakge

* 启动 provider

  进入目录 ${Project}/canary-provider/target/
         
        java -jar canary-provider-1.0-SNAPSHOT.jar

* 启动 provider

  进入目录 ${Project}/canary-provider-beta/target/
         
        java -jar canary-provider-beta-1.0-SNAPSHOT.jar
        
* 启动 consumer

  进入目录 ${Project}/canary-consumer/target/
       
        java -jar canary-consumer-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后， 然后通过界面访问： http://localhost:8091/canary?id=2。 provider-beta产生20%的响应，
provider-beta产生80%的响应。
