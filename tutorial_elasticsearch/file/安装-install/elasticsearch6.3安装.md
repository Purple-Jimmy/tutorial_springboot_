### elasticsearch 6.3 安装
#### 卸载旧版
1. 查看是否存在 
```
rpm -qa | grep -i elasticsearch
```
2. 停止服务运行并删除 
```
rpm -e elasticsearch-6.3.0-1.noarch --nodeps
```
3. 删除/etc下的配置文件 

#### 安装elasticsearch
1. 上传并解压,安装路径 /usr/share/elasticsearch
```
rpm -ivh elasticsearch-6.3.0.rpm 
```
2. 启动服务
```
service elasticsearch start  
或者命令  
sudo chkconfig --add elasticsearch  
sudo -i service elasticsearch start  
sudo -i service elasticsearch stop   
sudo -i service elasticsearch status 
```
3. 测试是否安装成功 
```
curl http://localhost:9200/
```
4. 日志存放路径 /var/log/elasticsearch/


#### 安装kibana
1. 上传并解压,安装路径 /usr/share/kibana
```
rpm -ivh kibana-6.3.0-x86_64.rpm
```
2. 配置远程访问,修改yml文件,路径 /etc/kibana
```
server.host: "0.0.0.0"
```
3. 启动服务
```
sudo chkconfig --add kibana   # 设置自动启动    
sudo -i service kibana start  
sudo -i service kibana stop  
sudo -i service kibana restart   
或者    
sudo /bin/systemctl daemon-reload  
nohup ./bin/kibana & 后台启动  
```  
4. 访问 http://ip:5601
5. 日志存放路径 /var/log/kibana  

#### 配置x-pack
https://www.elastic.co/guide/en/elasticsearch/reference/current/xpack-commands.html
* 初始化密码 包含用户:elastic,kibana,logstash_system,beats_system
bin/elasticsearch-setup-passwords auto

* 查看用户
./elasticsearch-users list

* 添加用户
./elasticsearch-users useradd jimmy -p 123456 -r superuser













































#### 集群配置
./elasticsearch -d -Des.path.conf=/etc/elasticsearch/master -p /etc/elasticsearch/master.pid


./bin/elasticsearch -d Ecluster.name=cluster -Enode.name=node1
./bin/elasticsearch -d Ecluster.name=es -Enode.name=node-m
./bin/elasticsearch -d Ecluster.name=es -Enode.name=node-1
curl 'http://localhost/_cluster/health?pretty'
默认不允许单机开启多个node的，该参数限制了单机可以开启的es实例个数 表示本机最大可运行6个实例
node.max_local_storage_nodes: 2  
node.master: true
node.data: true
表示允许检查防止同一主机多个实例分配同一分片
cluster.routing.allocation.same_shard.host：true






# 1. 如果你想让节点从不选举为主节点,只用来存储数据,可作为数据节点 
# node.master: true 
# node.data: false
# node.ingest: true

# 2. 如果想让节点成为主节点,且不存储任何数据,并保有空闲资源,可作为协调器 
# node.master：true 
# node.data：false 
# node.ingest：false 

# 3. 如果想让节点既不称为主节点,又不成为数据节点,那么可将他作为摄取节点,从节点中获取数据,生成搜索结果等 
# node.master: false 
# node.data: false 
# node.ingest: true

# 4. 仅作为协调器 
# node.master: false 
# node.data: false

# node.ingest: false








#### 创建用户 http://www.cnblogs.com/phpper/p/9201838.html

第一步：liunx创建新用户  adduser XXX    然后给创建的用户加密码 passwd XXX    输入两次密码。

第二步：切换刚才创建的用户 su XXX  然后执行elasticsearch  会显示Permission denied 权限不足。

第三步：给新建的XXX赋权限，chmod 777 *  这个不行，因为这个用户本身就没有权限，肯定自己不能给自己付权限。所以要用root用户登录付权限。

第四步：root给XXX赋权限，chown -R XXX /你的elasticsearch安装目录。

#### 踩坑
* 需要修改jvm.options配置，不然es出问题是会在启动用户宿主目录下产生大量日志占满磁盘
```
vim elasticsearch-6.2.2/config/jvm.options
# -XX:+HeapDumpOnOutOfMemoryError  # 注释掉这一行
-XX:HeapDumpPath=/data/HeapDumpPath  # 修改heapDump日志路径到容量充裕的分区
```

* cluster.routing.allocation.same_shard.host
允许执行检查以防止在单个主机上根据主机名和主机地址分配同一分片的多个实例。默认为false，意味着默认情况下不执行检查。此设置仅适用于在同一台机器上启动多个节点的情况。