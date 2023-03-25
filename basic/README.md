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

* Build

        mvn clean package

* Run provider

  In ${Project}/provider/target/
  
        java -jar basic-provider-1.0-SNAPSHOT.jar

* Run consumer

  In ${Project}/consumer/target/

        java -jar basic-consumer-1.0-SNAPSHOT.jar
                
* Run gateway

  In ${Project}/gateway/target/

        java -jar basic-gateway-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:9090/consumer/sayHello?name=World or http://localhost:9090/consumer/sayHelloFeign?name=World
