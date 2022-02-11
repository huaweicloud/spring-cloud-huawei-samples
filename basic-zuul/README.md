# Description
This project providers sample to show working with Spring Cloud Microservices. 
Compare with basic project,using zuul to forward requests.

* provider
A Microserivce using Spring Cloud with a REST interface.

* consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.

* zuul
A Microserivce using Spring Cloud Netflix zuul to forward requests to consumer.

# Build and Run

* Prerequisites
[Setup CSE environment](../README.md)

* Build

        mvn clean package

* Run provider

  In ${Project}/basic-zuul-provider/target/
  
        java -jar basic-zuul-provider-1.0-SNAPSHOT.jar

* Run consumer

  In ${Project}/basic-zuul-consumer/target/

        java -jar basic-zuul-consumer-1.0-SNAPSHOT.jar

* Run zuul

  In ${Project}/basic-zuul/target/

        java -jar basic-zuul-zuul-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:9090/sayHello?name=World

# 项目说明

这个项目提供了 Spring Cloud Huawei 的简单例子，与basic工程相比，改用zuul做网关实现请求分发，例子包括：

* provider
使用 Spring Cloud 开发一个 REST 接口。

* consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。 

* zuul
使用 Spring Cloud netflix zuul 开发一个网关， 网关将所有请求转发到 consumer。 

## 使用

* 前提条件
[准备CSE运行环境](../README_CN.md)

* 编译

        mvn clean package

* 启动 provider

  进入目录 ${Project}/basic-zuul-provider/target/
  
        java -jar basic-zuul-provider-1.0-SNAPSHOT.jar

* 启动 consumer

  进入目录 ${Project}/basic-zuul-consumer/target/

        java -jar basic-zuul-consumer-1.0-SNAPSHOT.jar

* 启动 zuul

  进入目录 ${Project}/basic-zuul-zuul/target/

        java -jar basic-zuul-gateway-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后， 然后通过界面访问： http://localhost:9090/sayHello?name=World
