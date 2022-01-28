# Spring Cloud Huawei 代码示例 [English](README.md) 

这个项目包含Spring Cloud Huawei使用的例子，详细内容请查看每个子项目的说明。

目录说明：

- basic：演示如何使用spring-cloud-huawei

- canary：演示如何使用Canary Deploy.
- migrate：演示dubbo、hsf、sofa、tsf项目及其向spring cloud微服务的改造
- porter：5个使用pring Cloud Huawei/ServiceComb Java Chassis 的微服务例子。
- spring-cloud-eureka：演示如何将spring-cloud原生服务转化为spring-cloud-huawei服务。

分支信息:

* master: Spring Cloud 2020.0.x
* Hoxton: Spring Cloud Hoxton
* Greenwich: Spring Cloud Greenwich
* Finchley: Spring Cloud Finchley


## 前提条件

运行这些例子之前，需要先准备CSE运行环境，有两种方式可供选择：

* 1.通过下载安装本地简化版 CSE （链接见下文）， 搭建本地开发环境。
* 使用[华为云微服务引擎 CSE ](https://support.huaweicloud.com/devg-servicestage/ss-devg-0002.html) 。

[更多信息](https://support.huaweicloud.com/devg-servicestage/ss-devg-0006.html) 可以参考开发指南。

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

下载链接： [CSE 1.0下载链接](https://support.huaweicloud.com/devg-servicestage/ss-devg-0034.html) ， [CSE 2.0下载链接](https://support.huaweicloud.com/devg-cse/cse_devg_0036.html)  
