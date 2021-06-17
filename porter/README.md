# About this Project
This project provides 5 microservices using Spring Cloud Huawei and ServiceComb Java Chassis.

* website
  
  a microservice using spring-boot-web, only has static web pages.
  
* user-service
  
  a microservice using Spring Cloud and provides a REST interface.  Using Spring Cloud Huawei 
  to connect to service center.

* file-service

  a microservice using ServiceComb Java Chassis and provides a REST interface. 

* spring-cloud-gateway

  a microservice using Spring Cloud Gateway. Using Spring Cloud Huawei to connect to service center.

* servicecomb-edge-service
  
   a microservice using ServiceComb Edge Service.  


## startup
  * [Setup CSE environment](../README.md)
  * Install Mysql, and create an account, e.g. user root and password root.
  * Run create_db_user.sql (In user-service resources/config folder)
  * Start up user-service, file-service, website and either of spring-cloud-gateway 
   servicecomb-edge-service.
   
## Try it out

  1. Open http://localhost:9090/ui/login.html, login with admin or guest, password is test.
  2. Select a file and upload. If successful a file ID is printed.
  3. Delete for and input the file ID. Guest is not allowed to delete file and will fail. 
 
   
# 项目说明

这个项目基于 [servicecomb porter](https://docs.servicecomb.io/java-chassis/zh_CN/featured-topics/application-porter/)
进行了改造， 用于演示 spring cloud huawei 的基本功能，以及与 servicecomb java chassis 的互操作性。 包括下面
几个子项目：

* website
  
  使用 spring-boot-web 开发的一个静态页面服务，只托管静态页面。
  
* user-service
  
  用户管理服务。 基于 spring cloud 开发框架开发的 REST 服务。 采用 spring cloud huawei 接入服务中心。
  
* file-service

  文件管理服务。基于 java chassis 开发框架开发的 REST 服务。 
  
* spring-cloud-gateway

  基于 spring cloud gateway 开发的应用网关。 采用 spring cloud huawei 接入服务中心。
  
* servicecomb-edge-service

  基于 java chassis 开发的应用网关。 

## 启动

  * [准备CSE运行环境](../README_CN.md)
  * 安装mysql数据库，设置用户名密码（假设为root/root）
  * 执行脚本create_db_user.sql （在 user-service 的 resources/config 目录）
  * 启动 user-service, file-service, website 和 spring-cloud-gateway 
   （或者servicecomb-edge-service， 二选一）。

## 使用

  1. 输入: http://localhost:9090/ui/login.html 使用admin或者guest登陆，密码为test。
  2. 选择一个文件上传，上传成功，上传成功后的文件会保存在file-service的当前目录， 文件名称是一个随机的数字，这个数字就是文件ID。
  3. 删除文件：输入上一步的文件ID，点击删除。 如果是admin用户，上传成功；如果是guest用户，上传失败。
