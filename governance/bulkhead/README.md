# Description
This project providers sample to show working with bulkhead Deploy. 

* bulkhead-provider
A Microserivce using Spring Cloud with a REST interface.

* bulkhead-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls bulkhead-provider with RestTemplate.

* normal-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls bulkhead-provider with RestTemplate.

* gateway
A Microserivce using Spring Cloud Gateway to forward requests to bulkhead-consumer/normal-consumer.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run bulkhead-provider

  In ${Project}/bulkhead-provider/target/
  
        java -jar bulkhead-provider-1.0-SNAPSHOT.jar

* Run bulkhead-consumer

  In ${Project}/bulkhead-consumer/target/
  
        java -jar bulkhead-consumer-1.0-SNAPSHOT.jar

* Run normal-consumer

  In ${Project}/normal-consumer/target/

        java -jar normal-consumer-1.0-SNAPSHOT.jar

* Run gateway

  In ${Project}/gateway/target/

        java -jar gateway-1.0-SNAPSHOT.jar

* Testing

Open in browser：  

http://localhost:9090/bulkhead-consumer/bulk-rate-limiting。 The test isolation compartment policy takes effect. 
The maxWaitDuration set to 0, and some requests are returned with error codes due to restrictions.

http://localhost:9090/bulkhead-consumer/bulk-no-rate-limiting。 The test isolation compartment policy takes effect. 
The maxWaitDuration set to 2 seconds, and the provider processes all requests normally.

http://localhost:9090/bulkhead-consumer/bulk-service-name。 Verify that the isolation compartment policy takes 
effect and the specified client is bulkhead-consumer, and that some requests are restricted and error responses are returned.

http://localhost:9090/normal-consumer/bulk-service-name。 The test client is specified as bulkhead-consumer. 
The bulkhead policy does not take effect, and the provider can properly process all requests.

# 项目说明

这个项目提供了 Spring Cloud Huawei 服务端隔离仓的例子。

* bulkhead-provider
使用 Spring Cloud 开发一个 REST 接口。

* bulkhead-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 load-balance-provider 的接口。

* normal-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 load-balance-provider 的接口。 

* gateway
使用 Spring Cloud Gateway 开发一个网关， 网关将所有请求转发到 bulkhead-consumer/normal-consumer。

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 bulkhead-provider

  进入目录 ${Project}/bulkhead-provider/target/
         
        java -jar bulkhead-provider-1.0-SNAPSHOT.jar

* 启动 bulkhead-consumer

  进入目录 ${Project}/bulkhead-consumer/target/
         
        java -jar bulkhead-consumer-1.0-SNAPSHOT.jar

* 启动 normal-consumer

  进入目录 ${Project}/normal-consumer/target/

        java -jar normal-consumer-1.0-SNAPSHOT.jar

* 启动 gateway

  进入目录 ${Project}/gateway/target/
       
        java -jar gateway-1.0-SNAPSHOT.jar

* 测试

启动4个微服务后， 然后通过界面访问：

http://localhost:9090/bulkhead-consumer/bulk-rate-limiting。 测试隔离仓策略生效，请求等待时间设置为0，部分请求被限制错误返回。
http://localhost:9090/bulkhead-consumer/bulk-no-rate-limiting。 测试隔离仓策略生效，请求等待时间设置为2秒，provider正常处理所有请求。
http://localhost:9090/bulkhead-consumer/bulk-service-name。 测试隔离仓策略生效，指定生效客户端为bulkhead-consumer，部分请求被限制错误返回。
http://localhost:9090/normal-consumer/bulk-service-name。 测试指定生效客户端为bulkhead-consumer，隔离仓策略不生效，provider正常处理所有请求。
