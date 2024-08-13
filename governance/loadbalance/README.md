# Description
This project providers sample to show working with load-balance Deploy. 

* provider-one
A Microserivce using Spring Cloud with a REST interface.

* provider-two
Same as provider-one, but with version 0.0.2

* provider-three
  Same as provider-one, but with version 0.0.3

* consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls load-balance-provider with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean package

* Run provider

  In ${Project}/provider-one/target/
  
        java -jar provider-one-1.0-SNAPSHOT.jar

* Run provider-two

  In ${Project}/provider-two/target/
  
        java -jar provider-two-1.0-SNAPSHOT.jar

* Run provider-three

  In ${Project}/provider-three/target/

        java -jar provider-three-1.0-SNAPSHOT.jar

* Run consumer

  In ${Project}/consumer/target/

        java -jar consumer-1.0-SNAPSHOT.jar

* Testing

Open in browser： http://localhost:8090/load-balance?id=111

All returned results are sorted by version number. 

# 项目说明

这个项目提供了 Spring Cloud Huawei 灰度发布的例子。

* provider-one
使用 Spring Cloud 开发一个 REST 接口。

* provider-two
load-balance-provider的0.0.2版本。 

* provider-three
  load-balance-provider的0.0.3版本。

* consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 load-balance-provider 的接口。 

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean package

* 启动 provider-one

  进入目录 ${Project}/provider-one/target/
         
        java -jar provider-one-1.0-SNAPSHOT.jar

* 启动 provider-two

  进入目录 ${Project}/provider-two/target/
         
        java -jar provider-two-1.0-SNAPSHOT.jar

* 启动 provider-three

  进入目录 ${Project}/provider-three/target/

        java -jar provider-three-1.0-SNAPSHOT.jar

* 启动 consumer

  进入目录 ${Project}/consumer/target/
       
        java -jar consumer-1.0-SNAPSHOT.jar

* 测试

启动4个微服务后， 然后通过界面访问： http://localhost:8090/load-balance?id=111。 返回结果按版本号有规律顺序的返回。
