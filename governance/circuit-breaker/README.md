# Description
This project providers sample to show working with rate-limiting Deploy. 

* circuit-provider
A Microserivce using Spring Cloud with a REST interface.

* normal-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls limit-provider with RestTemplate.

* circuit-consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls limit-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

[Setup CSE(Nacos) environment](../NACOS-ENV.md)

* Build
  CSE(Servicecomb)

        mvn clean package -Pcse
  CSE(Nacos)

        mvn clean package -Pnacos

* Run circuit-provider

  In ${Project}/circuit-provider/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse circuit-provider-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos circuit-provider-1.0-SNAPSHOT.jar
  
* Run normal-consumer

  In ${Project}/normal-consumer/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse normal-consumer-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos normal-consumer-1.0-SNAPSHOT.jar
  
* Run circuit-consumer

  In ${Project}/circuit-consumer/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse circuit-consumer-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos circuit-consumer-1.0-SNAPSHOT.jar

* Testing

1、Triggering the server circuit breaker based on the client service name

Open in browser： http://localhost:8086/circuit/serviceName

Circuit breaker is triggered when more than two requests are sent within 60 seconds.

Open in browser： http://localhost:8090/circuit/serviceName

No circuit breaker is triggered when more than two requests are sent within 60 seconds.

2、Set error rate requirement to 100

Open in browser： http://localhost:8086/circuit/notOpen/errorRate100

The circuit breaker is not triggered when more than 10 requests are sent within 60 seconds. This is because the interface is configured to return every five requests.

3、Set error rate requirement to 50

Open in browser： http://localhost:8086/circuit/errorRate50

If more than 10 requests are sent within 60 seconds, the circuit breaker is triggered. The interface is configured to return a message once every two requests fail.

4、Error code 500 is added to the circuit breaker error code judgment. In the service, error code 500 triggers circuit breaker, and code 505 does not trigger circuit breaker.

Open in browser： http://localhost:8086/circuit/errorCode500?code=500

If more than two requests are sent within 60 seconds, the circuit breaker is triggered.

Open in browser： http://localhost:8088/circuit/errorCode500?code=505

If more than two requests are sent within 60 seconds, the circuit breaker is not triggered, and only error 500 is displayed. This is because the circuit breaker is not set for code 505. Note that the test takes effect only after the circuit breaker window ends.

5、The server interface returns a 200 message, and the X-HTTP-STATUS-CODE=502 key value in the header triggers circuit breaker.

Open in browser： http://localhost:8088/circuit/headerCode

If more than two requests are sent within 60 seconds, the circuit breaker is triggered.

# 项目说明

这个项目提供了 Spring Cloud Huawei 限流治理的例子。

* circuit-provider
使用 Spring Cloud 开发一个 REST 接口。

* normal-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 circuit-provider 的接口。

* circuit-consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 circuit-provider 的接口。

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

[准备CSE(Nacos)运行环境](../NACOS-ENV_CN.md)

* 编译
  CSE(Servicecomb)

        mvn clean package -Pcse
  CSE(Nacos)

        mvn clean package -Pnacos

* 启动 circuit-provider

  进入目录 ${Project}/circuit-provider/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse circuit-provider-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos circuit-provider-1.0-SNAPSHOT.jar

* 启动 normal-consumer

  进入目录 ${Project}/normal-consumer/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse normal-consumer-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos normal-consumer-1.0-SNAPSHOT.jar
         
* 启动 circuit-consumer

  进入目录 ${Project}/circuit-consumer/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse circuit-consumer-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos circuit-consumer-1.0-SNAPSHOT.jar

* 测试

1、根据客户端服务名触发服务端熔断

界面访问：http://localhost:8086/circuit/serviceName 60秒内超过2次请求则触发熔断

界面访问：http://localhost:8090/circuit/serviceName 60秒内超过2次请求不会触发熔断

2、将错误率要求设置为100

界面访问：http://localhost:8086/circuit/notOpen/errorRate100  60秒内超过10次请求不触发熔断，因为接口中设置5次请求正常返回一次

3、将错误率要求设置为50

界面访问：http://localhost:8086/circuit/errorRate50  60秒内超过10次请求则触发熔断，接口中设置2次请求错误返回一次

4、熔断错误编码判断中增加500错误码，业务中500异常触发熔断，505编码不触发熔断

界面访问：http://localhost:8086/circuit/errorCode500?code=500  60秒内超过2次请求则触发熔断

界面访问：http://localhost:8086/circuit/errorCode500?code=505  60秒内超过2次请求不触发熔断，仅提示500错误，因为505编码未设置熔断，注意要在熔断窗口结束后测试才有效果

5、服务端接口正常200返回结果，header中设置X-HTTP-STATUS-CODE=502键值触发熔断

界面访问：http://localhost:8086/circuit/headerCode  60秒内超过2次请求则触发熔断
