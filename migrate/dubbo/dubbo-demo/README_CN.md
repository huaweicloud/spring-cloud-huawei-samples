
dubbo-demo项目：
   基于Dubbo框架，zooKeeper为注册中心的简单微服务结构
使用：
1、下载zookeeper，下载地址 https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz
2、在解压后的zookeeper子文件夹bin下启动zkServer.cmd
3、启动 DubboProviderApplication
4、启动 DubboConsumerApplication
5、访问 http://localhost:8878/hello?name=World (页面显示“Hello World, I am from provider”,表示服务均正常工作)
