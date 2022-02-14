# Spring Cloud Huawei 代码示例 [English](README.md) 

这个项目包含Spring Cloud Huawei使用的例子，详细内容请查看每个子项目的说明。

目录说明：

- basic：这个项目提供了 Spring Cloud Huawei 的简单例子 
  - provider 使用 Spring Cloud 开发一个 REST 接口。  
  - consumer 使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。  
  - gateway 使用 Spring Cloud Gateway 开发一个网关， 网关将所有请求转发到 consumer。 
- basic-zuul：提供spring cloud huawei使用zuul的例子
  - provider 使用 Spring Cloud 开发一个 REST 接口。  
  - consumer 使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。 
  - zuul 使用 Spring Cloud Netflix zuul 开发的一个网关， 网关将所有请求转发到 consumer。 

- canary：演示如何使用Canary Deploy的简单例子.

  - canary-provider 使用 Spring Cloud 开发一个 REST 接口。  
  - canary-provider-beta 跟 canary-provider一样, 但版本是 0.0.2
  - canary-consumer 使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。  

- migrate：演示dubbo、hsf、sofa、tsf项目及其向spring cloud微服务的改造

  - dubbo：包含Dubbo原生微服务 及 将其改造为 Spring Cloud 微服务两个例子
  - hsf：演示将 HSF 微服务改造为 Spring Cloud 微服务 
  - sofa：包含 SofaStack 原生微服务 及 将其改造为 Spring Cloud 微服务两个例子
  - tsf：包含tsf 原生微服务 及 将其改造为 Spring Cloud 微服务两个例子

- porter：5个使用pring Cloud Huawei/ServiceComb Java Chassis 的微服务例子。

  - website

    使用spring-boot-web的微服务，只有静态网页。

  - user-service

    使用Spring Cloud的微服务，提供REST接口。使用Spring Cloud Huawei对接ServiceCenter。

  - file-service

    使用ServiceComb Java Chassis的微服务和提供一个REST 接口。

  - spring-cloud-gateway

    使用Spring Cloud Gateway的搭建的微服务网关， 使用Spring Cloud Huawei对接ServiceCenter。

  - servicecomb-edge-service

    使用ServiceComb Edge Service的微服务。

- spring-cloud-eureka：使用eureka作为注册中心的Spring Cloud微服务，你可以通过跟basic例子对比了解如何如何将spring-cloud原生服务转化为spring-cloud-huawei服务。

  - eureka-provider 使用 eureka作为注册中心的Spring Cloud 微服务，提供一个 REST 接口 
  - eureka-consumer 用 eureka作为注册中心的Spring Cloud 微服务， 接口实现通过 RestTemplate 调用 eureka-provider 的接口。  

分支信息:

* master: Spring Cloud 2020.0.x
* Hoxton: Spring Cloud Hoxton
* Greenwich: Spring Cloud Greenwich
* Finchley: Spring Cloud Finchley


## 前提条件

运行这些例子之前，需要先准备CSE运行环境，有两种方式可供选择：

* 通过下载安装本地简化版 CSE 搭建本地开发环境，下载链接： [CSE 1.0](https://support.huaweicloud.com/devg-servicestage/ss-devg-0034.html) ， [CSE 2.0](https://support.huaweicloud.com/devg-cse/cse_devg_0036.html)  。
* 使用[华为云微服务引擎 CSE ](https://support.huaweicloud.com/qs-cse/cse_qs_0002.html) 

更多信息可以参考[开发指南](https://support.huaweicloud.com/devg-cse/cse_devg_0006.html) 

CSE运行环境准备好以后，编辑每个微服务的 `bootstrap.yml` 文件，配置正确的 CSE  服务信息，比如配置中心、注册中心的地址。

例子默认使用微服务引擎1.0版本，配置中心的类型为 config-center：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: config-center
        serverAddr:  http://127.0.0.1:30113
        fileSource: consumer.yaml
```

如果使用微服务引擎2.0， 配置中心类型需要修改为 kie：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: kie
        serverAddr:  http://127.0.0.1:30110
```

## ServiceStage

### 概述

应用管理与运维平台（ServiceStage）是面向企业的应用管理与运维平台，提供应用发布、部署、监控与运维等一站式解决方案。支持Java、Go、PHP、Node.js、Python、Docker、Tomcat等运行环境。支持Web应用，以及Apache ServiceComb、Spring Cloud、Dubbo、Mesher服务网格等微服务应用和通用应用，让企业应用上云更简单。

### 快速体验ServiceStage

[ServiceStage快速入门](https://support.huaweicloud.com/qs-servicestage/servicestage_qs_0025.html) 

## 微服务引擎 CSE

微服务引擎（Cloud Service Engine，CSE），是用于微服务应用的云中间件，为用户提供注册发现、服务治理、配置管理等高性能和高韧性的企业级云服务能力；CSE可无缝兼容Spring Cloud、ServiceComb等开源生态；用户也可结合其他云服务，快速构建云原生微服务体系，实现微服务应用的快速开发和高可用运维。 

CSE分为CSE 1.0、CSE 2.0两个版本。

CSE 2.0提供微服务引擎专享版。微服务引擎专享版是可支持大规模微服务应用管理的商用引擎。您可根据业务需要选择不同规格；引擎资源独享，性能不受其他租户影响。

相较于CSE 1.0，CSE 2.0版本底层架构、功能、安全及性能全面升级，提供了独立的服务注册发现中心和配置中心，支持基于用户业务场景的定义和治理。两个版本的特性比对请参见[表1](https://support.huaweicloud.com/productdesc-cse/cse_productdesc_0001.html#cse_productdesc_0001__table88531734172219)。

更多信息可以参考[CSE官方文档](https://support.huaweicloud.com/wtsnew-cse/index.html) 。


