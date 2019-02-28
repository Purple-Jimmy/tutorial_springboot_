### docker安装rabbitmq
1. 官网搜索rabbitmq,使用mangement版本(web管理页面)
2. 拉取镜像
```
docker pull rabbitmq:3.7.12-management
```
3. 启动容器
```
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -v `pwd`/data:/var/lib/rabbitmq --hostname myRabbit -e RABBITMQ_DEFAULT_VHOST=my_vhost  -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin 0d3d5400dcc0  
```
参数说明:
* -d 后台运行容器
* --name 指定容器名
* -p 指定服务运行的端口(5672:应用访问端口 15672:控制台Web端口号)
* -v 映射目录或文件
* --hostname  主机名(RabbitMQ的一个重要注意事项是它根据所谓的"节点名称"存储数据,默认为主机名)
* -e 指定环境变量(RABBITMQ_DEFAULT_VHOST:默认虚拟机名 RABBITMQ_DEFAULT_USER:默认的用户名 RABBITMQ_DEFAULT_PASS:默认用户名的密码）

4. 使用命令docker ps 查看正在运行容器