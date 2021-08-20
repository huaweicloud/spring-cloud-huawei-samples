# Spring Cloud Huawei Samples [中文](README_CN.md) 

This project provide samples for Spring Cloud Huawei. Read each sub-project for details about each sample.

Branches:

* master: for Spring Cloud 2020.0.x
* Hoxton: for Spring Cloud Hoxton
* Greenwich: for Spring Cloud Greenwich
* Finchley: for Spring Cloud Finchley

## Prerequisites

Running samples, must first prepare CSE environment. 

* Download and install a [Local CSE](https://support.huaweicloud.com/devg-servicestage/ss-devg-0034.html)
* Using CSE in [Huawei Cloud](https://support.huaweicloud.com/devg-servicestage/ss-devg-0002.html)

[More information](https://support.huaweicloud.com/devg-servicestage/ss-devg-0006.html) can be found in Huawei Cloud. 

And edit `bootstrap.yml` for each microserivce, configure the correct CSE services, like config center and service center.

Samples using CSE 1.0 in default，the config service is config-center. If using  CSE 2.0， config service change to kie：

```
spring:
  cloud:
    servicecomb:
      config:
        serverType: kie
        serverAddr:  http://127.0.0.1:30110
```

