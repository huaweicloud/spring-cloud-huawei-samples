
## dubbo-demo project：
   A simple micro service structure based on Dubbo framework and zookeeper as the registry center
## Usage:
1、Download zookeeper, download address https://www.apache.org/dyn/closer.lua/zookeeper/zookeeper-3.6.3/apache-zookeeper-3.6.3-bin.tar.gz
2、Start zkserver.cmd in the extracted zookeeper subfolder bin
3、Start DubboProviderApplication
4、Start DubboConsumerApplication
5、Visit http://localhost:8878/hello?name=World (The page displays "Hello world, I am from provider", indicating that the services are working normally)

