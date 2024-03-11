# 项目说明

这个项目提供了 Spring Cloud Huawei 适配应用网关“认证鉴权”能力的简单例子，例子包括：

* auth-service
  使用 Spring Cloud 开发一个 REST 接口进行鉴权，接口中url设置为 /authentication/* 是为了适配网关对所有服务进行鉴权，如果设置为某个url时，其余需要鉴权的url会出现404异常。

* auth-business-service
  使用 Spring Cloud 开发一个 REST 业务接口。

* un-auth-service
  使用 Spring Cloud 开发一个 REST 业务接口。

## 项目编译及前提条件

* 项目编译
  mvn clean package

* 前提条件
  [准备应用部署ECS虚拟机](/ECS-ENV_CN.md)

  [准备应用网关运行环境](/GATEWAY-ENV_CN.md)

  [准备CSE(Nacos)运行环境](/NACOS-ENV_CN.md)

>>> 注意：1、ECS、应用网关、Nacos引擎的虚拟私有云VPC要相同；2、注册到Nacos的微服务名称只支持小写、‘-’、数字，不支持驼峰命名、特殊字符。

## 服务启动
  ECS节点启动服务时，通过环境变量注入Nacos引擎地址 -DCSE_NACOS_ADDRESS=华为云Nacos内网地址:8848

* 启动 auth-service
  
  进入虚拟机jar文件目录

      java -jar -DCSE_NACOS_ADDRESS=华为云Nacos内网地址:8848 auth-service-1.0-SNAPSHOT.jar

* 启动 auth-business-service

  进入虚拟机jar文件目录

      java -jar -DCSE_NACOS_ADDRESS=华为云Nacos内网地址:8848 auth-business-service-1.0-SNAPSHOT.jar

* 启动 un-auth-service

  进入虚拟机jar文件目录

      java -jar -DCSE_NACOS_ADDRESS=华为云Nacos内网地址:8848 un-auth-service-1.0-SNAPSHOT.jar

>>> 注意：验证查看微服务是否已经注册到Nacos引擎。

## 应用网关设置认证鉴权
  
* 路由管理
  域名管理: 使用网关默认的http请求协议；
  服务来源: 创建来源=》来源类型(选择Nacos引擎)、来源名称(自定义)、引擎实例(选择已创建Nacos引擎)、命名空间(选择微服务注册命名空间)。
  服务管理: 创建服务=》选择已创建的服务来源、服务列表(勾选引擎已注册服务)、服务名(定义微服务名称)；
  路由设置: 路由名称(自定义)、域名(选择http协议域名)、服务地址(设置业务url，支持前缀、精准匹配)、目标服务(选择业务服务)，如下图：
  ![](路由设置.png)

* 访问控制
  =》认证鉴权
  =》创建鉴权规则
     鉴权类型: ![](鉴权类型.png)
         =》自定义鉴权规则名称、选择鉴权服务、填写鉴权服务端口；
         =》请求中允许携带的请求头: 当请求需要透传header时，该项需要进行对应设置, 否则header无法透传, 例如demo中要使用header的x-token作为获取token来源，可设置为x-token
         =》鉴权接口: 接口鉴权url + 业务url = 鉴权服务url，匹配“条件规则”指定的url前提下，自动添加鉴权接口url进行身份鉴权，如页面设置鉴权接口 /authentication, 业务接口 /authBusiness, 
           那么鉴权服务定义接口为/authentication/authBusiness 或者 /authentication/*
     >>> 注意: 当鉴权服务仅设置 /authentication/authBusiness 鉴权接口时，如果网关设置为对所有服务进行鉴权，/authBusiness 以外的业务url都将出现404异常。
     
     条件规则: ![](条件规则.png)
         =》黑名单模式
         =》新增匹配规则
         =》选择http请求域名、请求方法全选；
         =》服务地址: 该项为指定鉴权url，如果配置为 *，则对所有url进行鉴权；如果配置为前缀 /authBusiness，那么鉴权只对前缀为 /authBusiness 的url进行鉴权。

## 结果验证

* 鉴权服务对所有请求生效
  鉴权页面设置:
    鉴权接口: /authentication
    条件规则-服务地址: *
  
  ECS节点访问网关: curl -kv -H "x-token:auth2024" http://xxx.xxx.xxx.xx:80/authBusiness?name=2222
  业务正常返回: Hello 2222; server port: 9094

  ECS节点访问网关: curl -kv -H "x-token:auth2023" http://xxx.xxx.xxx.xx:80/authBusiness?name=2222
  业务正常返回: 403 Forbidden
  
  ECS节点访问网关: curl -kv -H "x-token:auth2024" http://xxx.xxx.xxx.xx:80/unAuthBusiness?name=2222
  业务正常返回: Hello 2222; server port: 9096

  ECS节点访问网关: curl -kv -H "x-token:auth2023" http://xxx.xxx.xxx.xx:80/unAuthBusiness?name=2222
  业务正常返回: 403 Forbidden

* 鉴权服务对某个url生效
  鉴权页面设置:
    鉴权接口: /authentication
    条件规则-服务地址: 前缀 /authBusiness

  ECS节点访问网关: curl -kv -H "x-token:auth2024" http://xxx.xxx.xxx.xx:80/authBusiness?name=2222
  业务正常返回: Hello 2222; server port: 9094

  ECS节点访问网关: curl -kv -H "x-token:auth2023" http://xxx.xxx.xxx.xx:80/authBusiness?name=2222
  业务正常返回: 403 Forbidden

  ECS节点访问网关: curl -kv http://xxx.xxx.xxx.xx:80/unAuthBusiness?name=2222 或者 curl -kv -H "x-token:auth2023" http://xxx.xxx.xxx.xx:80/unAuthBusiness?name=2222
  业务正常返回: Hello 2222; server port: 9096