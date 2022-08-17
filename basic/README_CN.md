# 项目说明  [English document](README.md)

这个项目提供了 Spring Cloud Huawei 的简单例子，例子包括：

* provider
  使用 Spring Cloud 开发一个 REST 接口。

* consumer
  使用 Spring Cloud 开发一个 REST 接口， 接口实现分別通过 RestTemplate和Feign 调用 provider 的接口。

* gateway
  使用 Spring Cloud Gateway 开发一个网关， 网关将所有请求转发到 consumer。

## 使用

* 前提条件
  [准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 provider

  进入目录 ${Project}/provider/target/

        java -jar basic-provider-1.0-SNAPSHOT.jar

* 启动 consumer

  进入目录 ${Project}/consumer/target/

        java -jar basic-consumer-1.0-SNAPSHOT.jar

* 启动 gateway

  进入目录 ${Project}/gateway/target/

        java -jar basic-gateway-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后， 然后通过界面访问： http://localhost:9090/sayHello?name=World 或者 http://localhost:9090/sayHelloFeign?name=World
