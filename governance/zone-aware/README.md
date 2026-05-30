# Description
This project providers sample to show working with zone-aware Deploy. 

* provider-az1
A Microserivce using Spring Cloud with REST interface.

* provider-az2
A Microserivce using Spring Cloud with REST interface.

* consumer-az1
A Microserivce using Spring Cloud with a REST interface. Consumer calls zone-aware with RestTemplate.

* consumer-az2
A Microserivce using Spring Cloud with a REST interface. Consumer calls zone-aware with RestTemplate.

# Build and Run

* Prerequisites
[Setup CSE environment](../CSE-ENV.md)

[Setup CSE(Nacos) environment](../NACOS-ENV.md)

* Build
  CSE(Servicecomb)

        mvn clean package -Pcse
  CSE(Nacos)

        mvn clean package -Pnacos

* Run provider-az1

  In ${Project}/provider-az1/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse provider-az1-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos provider-az1-1.0-SNAPSHOT.jar

* Run provider-az2

  In ${Project}/provider-az2/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse provider-az2-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos provider-az2-1.0-SNAPSHOT.jar
* 
* Run consumer-az1

  In ${Project}/consumer-az1/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse consumer-az1-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos consumer-az1-1.0-SNAPSHOT.jar
  
* Run consumer-az2

  In ${Project}/consumer-az2/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse consumer-az2-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos consumer-az2-1.0-SNAPSHOT.jar

* Testing

1、Start the providers of AZ1 and AZ2 and invoke the consumer of AZ1.

Open in browser：http://localhost:8086/zone/both2Az Only the server information of AZ1 is returned.

2、Start the providers of AZ1 and AZ2 and invoke the consumer of AZ2.

Open in browser：http://localhost:8088/zone/both2Az Only the server information of AZ2 is returned.

3、Only the provider in AZ2 is started, and the consumer in AZ1 is invoked.

Open in browser：http://localhost:8086/zone/singleAz The server information of AZ2 is returned.

4、Only the provider in AZ1 is started, and the consumer in AZ2 is invoked.

Open in browser：http://localhost:8088/zone/singleAz The server information of AZ1 is returned.

5、Start the providers of AZ1 and AZ2 and enable the forcible affinity configuration 
(enable the denyCrossZoneLoadBalancing configuration in the application.yaml file), and invoke the consumer of AZ1.

Open in browser：http://localhost:8086/zone/denyCross/bothAz Only the server information of AZ1 is returned.

6、Start the providers of AZ1 and AZ2 and enable the forcible affinity configuration 
(enable the denyCrossZoneLoadBalancing configuration in the application.yaml file), and invoke the consumer of AZ2.

Open in browser：http://localhost:8088/zone/denyCross/bothAz Only the server information of AZ2 is returned.

3、Start only the provider in AZ2 and enable the forcible affinity configuration 
(enable the denyCrossZoneLoadBalancing configuration in the application.yaml file), and invoke the consumer in AZ1.

Open in browser：http://localhost:8086/zone/denyCross Return error: No instances available.

4、Only start the provider in az1, and enable the forced affinity configuration 
(turn on the denyCrossZoneLoadBalancing configuration in application.yaml), and invoke the consumer in AZ2.

Open in browser：http://localhost:8088/zone/denyCross Return error: No instances available.

# 项目说明

这个项目提供了 Spring Cloud Huawei 数据亲和的例子。

* provider-az1
使用 Spring Cloud 开发 REST 接口。

* provider-az2
使用 Spring Cloud 开发 REST 接口。

* consumer-az1
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider-zone 的接口。

* consumer-az2
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider-zone 的接口。

## 使用

* 前提条件
[准备CSE运行环境](../CSE-ENV_CN.md)

[准备CSE(Nacos)运行环境](../NACOS-ENV_CN.md)

* 编译
  CSE(Servicecomb)

        mvn clean package -Pcse
  CSE(Nacos)

        mvn clean package -Pnacos

* 启动 provider-az1

  进入目录 ${Project}/provider-az1/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse provider-az1-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos provider-az1-1.0-SNAPSHOT.jar

* 启动 provider-az2

  进入目录 ${Project}/provider-az2/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse provider-az2-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos provider-az2-1.0-SNAPSHOT.jar

* 启动 consumer-az1

  进入目录 ${Project}/consumer-az1/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse consumer-az1-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos consumer-az1-1.0-SNAPSHOT.jar
         
* 启动 consumer-az2

  进入目录 ${Project}/consumer-az2/target/
  CSE(Servicecomb)

        java -jar -Dspring.profiles.active=cse consumer-az2-1.0-SNAPSHOT.jar
  CSE(Nacos)

        java -jar -Dspring.profiles.active=nacos consumer-az2-1.0-SNAPSHOT.jar

* 测试

1、同时启动az1、az2的provider，调用az1的consumer

界面访问：http://localhost:8086/zone/both2Az 仅返回az1服务端信息

2、同时启动az1、az2的provider，调用az2的consumer

界面访问：http://localhost:8088/zone/both2Az 仅返回az2服务端信息

3、仅启动az2的provider，调用az1的consumer

界面访问：http://localhost:8086/zone/singleAz 返回az2服务端信息

4、仅启动az1的provider，调用az2的consumer

界面访问：http://localhost:8088/zone/singleAz 仅返回az1服务端信息

5、同时启动az1、az2的provider，且开启强制亲和配置(打开application.yaml中的denyCrossZoneLoadBalancing配置)，调用az1的consumer

界面访问：http://localhost:8086/zone/denyCross/bothAz 仅返回az1服务端信息

6、同时启动az1、az2的provider，且开启强制亲和配置(打开application.yaml中的denyCrossZoneLoadBalancing配置)，调用az2的consumer

界面访问：http://localhost:8088/zone/denyCross/bothAz 仅返回az2服务端信息

3、仅启动az2的provider，且开启强制亲和配置(打开application.yaml中的denyCrossZoneLoadBalancing配置)，调用az1的consumer

界面访问：http://localhost:8086/zone/denyCross 返回无实例可用错误

4、仅启动az1的provider，且开启强制亲和配置(打开application.yaml中的denyCrossZoneLoadBalancing配置)，调用az2的consumer

界面访问：http://localhost:8088/zone/denyCross 返回无实例可用错误