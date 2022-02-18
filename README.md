# Spring Cloud Huawei Samples [中文](README_CN.md) 

This project provide samples for Spring Cloud Huawei. Read each sub-project for details about each sample. 

Directory description:

- basic：This project providers sample to show working with Spring Cloud Microservices. 
  - provider A Microserivce using Spring Cloud with a REST interface.
  - consumer A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.
  - gateway A Microserivce using Spring Cloud Gateway to forward requests to consumer.
- basic-zuul：This project providers sample to show working with Spring Cloud Microservices. Compare with basic project,using zuul to forward requests. 
  - provider A Microserivce using Spring Cloud with a REST interface.
  - consumer A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.
  - zuul A Microserivce using Spring Cloud Netflix zuul to forward requests to consumer.

- canary：This project providers sample to show working with Canary Deploy. 

  - canary-provider A Microserivce using Spring Cloud with a REST interface.
  - canary-provider-beta Same as canary-provider, but with version 0.0.2
  - canary-consumer A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.

- migrate：Demonstrate the dubbo, hsf, sofa, and tsf projects and their transformation to spring cloud microservices.

  - dubbo：The two examples here are Dubbo native micro service and its transformation into spring cloud micro service.
  - hsf：The demo shows how to change HSF microservices to Spring Cloud microservices. 
  - sofa：he two examples here are SofaStack native micro service and its transformation into spring cloud micro service.
  - tsf：The two examples here are tsf native micro service and its transformation into spring cloud micro service.

- porter：This project provides 5 microservices using Spring Cloud Huawei and ServiceComb Java Chassis. 

  - website

    a microservice using spring-boot-web, only has static web pages.

  - user-service

    a microservice using Spring Cloud and provides a REST interface. Using Spring Cloud Huawei to connect to service center.

  - file-service

    a microservice using ServiceComb Java Chassis and provides a REST interface.

  - spring-cloud-gateway

    a microservice using Spring Cloud Gateway. Using Spring Cloud Huawei to connect to service center.

  - servicecomb-edge-service

    a microservice using ServiceComb Edge Service.

- spring-cloud-eureka：Use the Spring Cloud microservice with Eureka as the registration center. You can compare this microservice with the basic example to learn how to convert the native Spring Cloud service to the Spring Cloud-huawei service.

  - eureka-consumer: A Spring Cloud microservices that use Eureka as the registration center provide a REST interface. Consumer calls provider with RestTemplate.
  - eureka-provider: A Spring Cloud microservices that use Eureka as the registration center provide a REST interface.

Branches:

* master: for Spring Cloud 2020.0.x
* Hoxton: for Spring Cloud Hoxton
* Greenwich: for Spring Cloud Greenwich
* Finchley: for Spring Cloud Finchley

## Prerequisites

Running samples, must first prepare CSE environment. There are two options for you to choose from:

* Download and install a Local CSE,  [CSE 1.0](https://support.huaweicloud.com/devg-servicestage/ss-devg-0034.html) ， [CSE 2.0](https://support.huaweicloud.com/devg-cse/cse_devg_0036.html)  
* Using CSE in [Huawei Cloud ](https://support.huaweicloud.com/qs-cse/cse_qs_0002.html)

[More information](https://support.huaweicloud.com/devg-cse/cse_devg_0006.html) can be found in Huawei Cloud. 

And edit `bootstrap.yml` for each microserivce, configure the correct CSE services, like config center and service center.

Samples using CSE 1.0 in default，the config service is config-center. 

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: config-center
        serverAddr:  http://127.0.0.1:30113
        fileSource: consumer.yaml
```

If using  CSE 2.0， config service change to kie：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: kie
        serverAddr:  http://127.0.0.1:30110
```

## ServiceStage

### Product Introduction

ServiceStage is an application management and O&M platform that lets you deploy, roll out, monitor, and maintain applications all in one place. Java, Go, PHP, Node.js, Docker, and Tomcat are supported. Web applications, microservice applications such as Apache ServiceComb, Spring Cloud, Dubbo, and service mesh, and common applications make it easier to migrate enterprise applications to the cloud.

### Quick Experience

The new application model of ServiceStage provides the environment management function. Basic resources (such as CCE clusters and ECSs) in the same VPC and optional resources (such as ELB, RDS, and DCS) are combined into an environment. When the environment is selected during application deployment, its included resources are automatically loaded.

This example describes how to quickly create a microservice application based on the ServiceComb (SpringMVC) framework to experience the functions of the new ServiceStage application model.

[Quick Experience](https://support.huaweicloud.com/intl/en-us/qs-servicestage/servicestage_qs_0025.html)

## Cloud Service Engine(CSE)

Cloud Service Engine (CSE) provides service registry, service governance, and configuration management. It allows you to quickly develop microservice applications and implement high-availability O&M. Furthermore, it supports multiple languages and runtime systems, and unified access and governance of intrusive frameworks such as Spring Cloud, Apache ServiceComb (Java Chassis/Go Chassis), and Dubbo, and non-intrusive service meshes. 

CSE has two versions: CSE 1.0 and CSE 2.0.

CSE 2.0 provides an exclusive edition of the microservice engine. The microservice engine exclusive edition is a commercial engine that supports large-scale microservice application management. You can select different specifications based on service requirements. Engine resources are exclusively used and the performance is not affected by other tenants.

Compared with CSE 1.0, CSE 2.0 completely upgrades the underlying architecture, functions, security, and performance. It provides independent service registration and discovery centers and configuration centers to support definition and governance based on user service scenarios. [Table 1](https://support.huaweicloud.com/productdesc-cse/cse_productdesc_0001.html#cse_productdesc_0001__table88531734172219) describes the feature comparison between the two versions.

More informationcan be found in [Official Documents](https://support.huaweicloud.com/wtsnew-cse/index.html)  .
