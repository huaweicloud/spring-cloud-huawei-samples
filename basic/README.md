# Description [查看中文文档](README_CN.md)

This project providers sample to show working with Spring Cloud Microservices. 

* provider
A Microserivce using Spring Cloud with a REST interface.

* consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate and Feign.

* gateway
A Microserivce using Spring Cloud Gateway to forward requests to consumer.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

[Setup Nacos environment](../NACOS-ENV.md)

* CSE Build

        mvn clean package -Pcse
* Nacos Build

        mvn clean package -Pnacos
* Run provider

  In ${Project}/provider/target/
  CSE

        java -jar -Dspring.profiles.active=cse basic-provider-1.0-SNAPSHOT.jar
  Nacos

        java -jar -Dspring.profiles.active=nacos basic-provider-1.0-SNAPSHOT.jar

* Run consumer

  In ${Project}/consumer/target/
  CSE

        java -jar -Dspring.profiles.active=cse basic-consumer-1.0-SNAPSHOT.jar
  Nacos

        java -jar -Dspring.profiles.active=nacos basic-consumer-1.0-SNAPSHOT.jar 
* Run gateway

  In ${Project}/gateway/target/
  CSE

        java -jar -Dspring.profiles.active=cse basic-gateway-1.0-SNAPSHOT.jar
  Nacos

        java -jar -Dspring.profiles.active=nacos basic-gateway-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:9090/consumer/sayHello?name=World or http://localhost:9090/consumer/sayHelloFeign?name=World

* Testing dynamic config

Open in browser： http://localhost:9090/consumer/testConfig