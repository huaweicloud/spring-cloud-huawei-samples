# Description
The purpose of the project is to demonstrate springBoot apply alone tomcat,Compared with jar,this following additional things need to be done:
1.Startup class add extends SpringBootServletInitializer,override configure method;
2.Add class NacosListener implements ApplicationListener，monitor ApplicationReadyEvent event；
3.Povider server apply tomcat need set root address in server.xml;

This project providers sample to show working with Spring Cloud Microservices. 

* provider
A Microserivce using Spring Cloud with a REST interface.

* consumer
A Microserivce using Spring Cloud with a REST interface. Consumer calls provider with RestTemplate.

# Build and Run

* Prerequisites
  * [Setup CSE environment](../CSE-ENV.md)

* Build

        mvn clean install

* Run provider

  copy project war put in tomcat/webapps,then start-up tomcat server

* Run consumer

  copy project war put in tomcat/webapps,then start-up tomcat server
  
above each project has owner tomcat server

* Testing

Open in browser： http://localhost:8082/sayHello?name=World

# 项目说明
该项目的目的是演示spring boot应用在独立tomcat运行。 和jar包运行相比，需要额外做如下事情：
1、启动类增加SpringBootServletInitializer继承，重写configure方法；
2、增加NacosListener类实现ApplicationListener接口，监听ApplicationReadyEvent事件；
3、服务provider对应部署的tomcat在server.xml中设置访问根路劲

* provider
使用 Spring Cloud 开发一个 REST 接口。

* consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider 的接口。

## 使用

* 前提条件
  * [准备CSE运行环境](../CSE-ENV_CN.md)

* 编译

        mvn clean install

* 启动 provider

  将项目打包好的war包部署到tomcat安装文件的webapps文件夹中，startup.bat启动服务

* 启动 consumer

  将项目打包好的war包部署到tomcat安装文件的webapps文件夹中，startup.bat启动服务
  
以上每个服务都单独部署一个tomcat服务

* 测试

启动2个微服务后， 然后通过界面访问： http://localhost:8082/sayHello?name=World
