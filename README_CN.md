# Spring Cloud Huawei 代码示例 [English](README.md) 

这个项目包含Spring Cloud Huawei使用的例子，详细内容请查看每个子项目的说明和[wiki](https://github.com/huaweicloud/spring-cloud-huawei-samples/wiki) 。

目录说明：

- basic：这个项目提供了 Spring Cloud Huawei 的简单例子 
   
- basic-tomcat：这个项目提供了 spring boot 应用在独立的tomcat上运行的简单例子

- canary：这个项目提供了Canary Deploy的简单例子.  

- migrate：演示dubbo、eureka、hsf、nacos、sofa、tsf项目及其向spring cloud微服务的改造

- porter：[应用开发最佳实践: 开发一个数据库应用](https://github.com/huaweicloud/spring-cloud-huawei-samples/wiki/porter) 。

分支信息:

* master: Spring Cloud 2024.0.x, 依赖 JDK17
* 2023.0.x: Spring Cloud 2023.0.x, 依赖 JDK17
* 2022.0.x: Spring Cloud 2022.0.x, 依赖 JDK17
* 2021.0.x: Spring Cloud 2021.0.x, 依赖 JDK8
* 2020.0.x: Spring Cloud 2020.0.x, 依赖 JDK8
* Hoxton: Spring Cloud Hoxton(EOL，不用于生产环境使用)
* Greenwich: Spring Cloud Greenwich(社区停止维护，不建议生产环境使用)
* Finchley: Spring Cloud Finchley（社区停止维护，不建议生产环境使用）


## 前提条件

运行这些例子之前，需要先[准备CSE运行环境](/CSE-ENV_CN.md)

更多信息可以参考[开发指南](https://support.huaweicloud.com/devg-cse/cse_devg_0006.html) 

CSE运行环境准备好以后，编辑每个微服务的 bootstrap.yml 文件，配置正确的 CSE 服务信息，比如配置中心、注册中心的地址。

例子默认使用微服务引擎2.0版本， 配置中心类型为 kie：
 
```
 spring:
   cloud:
     servicecomb:
       config:
         serverType: kie
         serverAddr:  http://127.0.0.1:30110
```
如果使用微服务引擎1.0版本，配置中心的类型需修改为 config-center：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: config-center
        serverAddr:  http://127.0.0.1:30113
        fileSource: consumer.yaml
```
 


## 微服务引擎 CSE

微服务引擎（Cloud Service Engine，CSE），是用于微服务应用的云中间件，为用户提供注册发现、服务治理、配置管理等高性能和高韧性的企业级云服务能力；CSE可无缝兼容Spring Cloud、ServiceComb等开源生态；用户也可结合其他云服务，快速构建云原生微服务体系，实现微服务应用的快速开发和高可用运维。 

CSE分为CSE 1.0、CSE 2.0两个版本。

CSE 2.0提供微服务引擎专享版。微服务引擎专享版是可支持大规模微服务应用管理的商用引擎。您可根据业务需要选择不同规格；引擎资源独享，性能不受其他租户影响。

相较于CSE 1.0，CSE 2.0版本底层架构、功能、安全及性能全面升级，提供了独立的服务注册发现中心和配置中心，支持基于用户业务场景的定义和治理。两个版本的特性比对请参见[表1](https://support.huaweicloud.com/productdesc-cse/cse_productdesc_0001.html#cse_productdesc_0001__table88531734172219)。

更多信息可以参考[CSE官方文档](https://support.huaweicloud.com/cse/index.html) 。


