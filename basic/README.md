# Description [查看中文文档](README_CN.md)

This project providers sample to show working with Spring Cloud Microservices. 

* provider
A Microserivce using Spring Cloud with a REST interface.

* consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate and Feign.

* gateway
A Microserivce using Spring Cloud Gateway to forward requests to consumer.

# Build and Run
  There provide CSE(Servicecomb)/CSE(Nacos) registry and configuration use ability in demo, according to the following command to use CSE(Servicecomb)/CSE(Nacos).

* Prerequisites
[Setup CSE(Servicecomb) environment](../CSE-ENV.md)

[Setup CSE(Nacos) environment](../NACOS-ENV.md)

* Build
  CSE(Servicecomb)

        mvn clean package -Pcse
  CSE(Nacos)

        mvn clean package -Pnacos
* Run provider

  In ${Project}/provider/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse basic-provider-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos basic-provider-1.0-SNAPSHOT.jar

* Run consumer

  In ${Project}/consumer/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse basic-consumer-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos basic-consumer-1.0-SNAPSHOT.jar 
* Run gateway

  In ${Project}/gateway/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse basic-gateway-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos basic-gateway-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:9090/consumer/sayHello?name=World or http://localhost:9090/consumer/sayHelloFeign?name=World

* Testing dynamic config
When three service started, setting this config in configCenter:

      test:
        name: test

Open in browser： http://localhost:9090/consumer/testConfig, check config update.