# Description
This project providers sample to show working with instance-bulkhead Deploy. 

* bulkhead-provider
A Microserivce using Spring Cloud with a REST interface.

* normal-provider
  A Microserivce using Spring Cloud with a REST interface.

* bulkhead-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls bulkhead-provider/normal-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run bulkhead-provider

  In ${Project}/bulkhead-provider/target/
  
        java -jar bulkhead-provider-1.0-SNAPSHOT.jar

* Run normal-provider

  In ${Project}/normal-provider/target/

        java -jar normal-provider-1.0-SNAPSHOT.jar

* Run bulkhead-consumer

  In ${Project}/bulkhead-consumer/target/
  
        java -jar bulkhead-consumer-1.0-SNAPSHOT.jar

* Testing

Open in browser：  

http://localhost:9090/bulkhead-consumer/bulk-rate-limiting。 Test maxWaitDuration set to 0, the isolation zone policy 
takes effect, and some requests are restricted and return an error.

http://localhost:9090/bulkhead-consumer/bulk-no-rate-limiting。 Test maxWaitDuration set to 2 seconds. The isolation 
zone policy takes effect, but all requests can obtain resources within the waiting time, and the provider processes all requests normally.

http://localhost:9090/bulkhead-consumer/bulk-service-name。 The test is performed to verify that when the server specified 
in the bulkhead policy is bulkhead-provider, the bulkhead policy takes effect and some requests are restricted and error 
responses are returned. When the server specified in the bulkhead policy is normal-provider, the bulkhead policy does not 
take effect and all requests are normal.

# 项目说明

这个项目提供了 Spring Cloud Huawei 客户端隔离仓的例子。

* bulkhead-provider
使用 Spring Cloud 开发一个 REST 接口。

* normal-provider
使用 Spring Cloud 开发一个 REST 接口。

* bulkhead-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 load-balance-provider 的接口。

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 bulkhead-provider

  进入目录 ${Project}/bulkhead-provider/target/
         
        java -jar bulkhead-provider-1.0-SNAPSHOT.jar

* 启动 normal-provider

  进入目录 ${Project}/normal-provider/target/

        java -jar normal-provider-1.0-SNAPSHOT.jar

* 启动 bulkhead-consumer

  进入目录 ${Project}/bulkhead-consumer/target/
         
        java -jar bulkhead-consumer-1.0-SNAPSHOT.jar

* 测试

启动3个微服务后， 然后通过界面访问：

http://localhost:9090/bulkhead-consumer/bulk-rate-limiting。 测试请求等待时间设置为0，隔离仓策略生效，部分请求被限制错误返回。

http://localhost:9090/bulkhead-consumer/bulk-no-rate-limiting。 测试隔离仓策略请求等待时间设置为2秒，隔离仓策略生效，但所有的请求都能
在等待时间内获取到资源，provider正常处理所有请求。

http://localhost:9090/bulkhead-consumer/bulk-service-name。 测试隔离仓策略指定生效服务端为bulkhead-provider，请求bulkhead-provider时，
隔离仓策略生效，部分请求被限制错误返回；请求normal-provider时，隔离仓策略不生效，所有请求正常。
