# Spring Cloud Huawei 代码示例 [English](README.md) 

这个项目包含Spring Cloud Huawei使用的例子，详细内容请查看每个子项目的说明。

分支信息:

* master: Spring Cloud 2020.0.x
* Hoxton: Spring Cloud Hoxton
* Greenwich: Spring Cloud Greenwich
* Finchley: Spring Cloud Finchley


## 前提条件

运行这些例子之前，需要先准备CSE运行环境。

* 通过下载安装 [本地简化版 CSE](https://support.huaweicloud.com/devg-servicestage/ss-devg-0034.html) ， 搭建本地开发环境。
* 使用[华为云云服务](https://support.huaweicloud.com/devg-servicestage/ss-devg-0002.html) 。

[更多信息](https://support.huaweicloud.com/devg-servicestage/ss-devg-0006.html) 可以参考开发指南。

CSE运行环境准备好以后，编辑每个微服务的 `bootstrap.yml` 文件，配置正确的 CSE  服务信息，比如配置中心、注册中心的地址。

例子默认使用微服务引擎1.0版本，配置中心的类型为 config-center，如果使用微服务引擎2.0， 配置中心类型需要修改为 kie：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: kie
        serverAddr:  http://127.0.0.1:30110
```

