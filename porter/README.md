# About this Project

see [wiki](https://github.com/huaweicloud/spring-cloud-huawei-samples/wiki/porter)

## startup
  * [Setup CSE environment](../CSE-ENV.md)
  * Install Mysql, and create an account, e.g. user root and password root.
  * Run create_db_user.sql (In user-service resources/config folder)
  * Start up user-core, file-business, porter-app, porter-gateway and porter-gateway.
   
## Try it out

  1. Open http://localhost:9090/porter/login.html, login with admin or guest, password is test.
  2. Select a file and upload. If successful a file ID is printed.
  3. Delete for and input the file ID. Guest is not allowed to delete file and will fail. 
 
   
# 项目说明

查看 [wiki](https://github.com/huaweicloud/spring-cloud-huawei-samples/wiki/porter)

## 启动

  * [准备CSE运行环境](../CSE-ENV_CN.md)
  * 安装mysql数据库，设置用户名密码（假设为root/root）
  * 执行脚本create_db_user.sql （在 user-service 的 resources/config 目录）
  * 启动 user-core, file-business, porter-app, porter-gateway 和 porter-gateway。

## 使用

  1. 输入: http://localhost:9090/porter/login.html 使用admin或者guest登陆，密码为test。
  2. 选择一个文件上传，上传成功，上传成功后的文件会保存在file-service的当前目录， 文件名称是一个随机的数字，这个数字就是文件ID。
  3. 删除文件：输入上一步的文件ID，点击删除。 如果是admin用户，上传成功；如果是guest用户，上传失败。
