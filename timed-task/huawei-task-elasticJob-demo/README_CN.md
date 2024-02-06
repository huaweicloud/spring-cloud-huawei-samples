# Spring Cloud Huawei 整合 ElasticJob 代码示例

这个项目包含在Spring Cloud Huawei使用ElasticJob的示例。

目录说明：

- huawei-task-elasticJob-demo: spring cloud huawei整合CSE、ElasticJob简单代码示例

## 前提条件

运行该例子之前，需要先搭建zookeeper及CSE运行环境：

- zookeeper环境搭建：

  * 下载zookeeper，[下载地址](https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz) 
  
  * 在解压后的zookeeper子文件夹bin下启动zkServer.cmd

- CSE运行环境搭建：

  * 通过下载安装本地简化版 CSE 搭建本地开发环境，下载链接：[CSE 2.0](https://support.huaweicloud.com/devg-cse/cse_04_0046.html)
  
  * 使用[华为云微服务引擎 CSE ](https://www.huaweicloud.com/product/cse.html) 


### 补充

- 如需搭建任务管理平台或更多样化开发风格可参考[elasticJob开发指南](https://shardingsphere.apache.org/elasticjob/current/cn/user-manual/)

- ElasticJob具体实现原理[参考资料](https://shardingsphere.apache.org/elasticjob/legacy/lite-2.x/03-design/lite-design/)


  