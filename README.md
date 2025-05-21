# Spring Cloud Huawei Samples [中文](README_CN.md) 

This project provide samples for Spring Cloud Huawei. Read each sub-project and [wiki](https://github.com/huaweicloud/spring-cloud-huawei-samples/wiki) for details about each sample. 

Directory description:

- basic：This project providers sample to show working with Spring Cloud Microservices. 
  
- basic-tomcat：This project provides a simple example of a Spring Boot application running on a standalone Tomcat

- canary：This project providers sample to show working with Canary Deploy. 

- migrate：Demonstrate the dubbo,eureka, hsf, sofa, nacos and tsf projects and their transformation to spring cloud microservices.

- porter：This project provides 5 microservices using Spring Cloud Huawei and ServiceComb Java Chassis. 


Branches:

* master: for Spring Cloud 2024.0.x and reqiures JDK 17
* 2023.0.x: for Spring Cloud 2023.0.x and reqiures JDK 17
* 2022.0.x: for Spring Cloud 2022.0.x and reqiures JDK 17
* 2021.0.x: for Spring Cloud 2021.0.x and reqiures JDK 8
* 2020.0.x: for Spring Cloud 2020.0.x and reqiures JDK 8
* Hoxton: for Spring Cloud Hoxton(EOL, not for pruducton use)
* Greenwich: for Spring Cloud Greenwich(Community maintenance is not recommended for production environment)
* Finchley: for Spring Cloud Finchley(Community maintenance is not recommended for production environment)


## Prerequisites

Running samples, must first [prepare CSE environment](/CSE-ENV.md)

[More information](https://support.huaweicloud.com/devg-cse/cse_devg_0006.html) can be found in Huawei Cloud. 

And edit `bootstrap.yml` for each microserivce, configure the correct CSE services, like config center and service center.

Samples using CSE 2.0 in default，the config service is kie

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: kie
        serverAddr:  http://127.0.0.1:30110
```
If using CSE 1.0，config service change to  config-center. 

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: config-center
        serverAddr:  http://127.0.0.1:30113
        fileSource: consumer.yaml
```


## Cloud Service Engine(CSE)

Cloud Service Engine (CSE) provides service registry, service governance, and configuration management. It allows you to quickly develop microservice applications and implement high-availability O&M. Furthermore, it supports multiple languages and runtime systems, and unified access and governance of intrusive frameworks such as Spring Cloud, Apache ServiceComb (Java Chassis/Go Chassis), and Dubbo, and non-intrusive service meshes. 

CSE has two versions: CSE 1.0 and CSE 2.0.

CSE 2.0 provides an exclusive edition of the microservice engine. The microservice engine exclusive edition is a commercial engine that supports large-scale microservice application management. You can select different specifications based on service requirements. Engine resources are exclusively used and the performance is not affected by other tenants.

Compared with CSE 1.0, CSE 2.0 completely upgrades the underlying architecture, functions, security, and performance. It provides independent service registration and discovery centers and configuration centers to support definition and governance based on user service scenarios. [Table 1](https://support.huaweicloud.com/productdesc-cse/cse_productdesc_0001.html#cse_productdesc_0001__table88531734172219) describes the feature comparison between the two versions.

More informationcan be found in [Official Documents](https://support.huaweicloud.com/cse/index.html)  .
