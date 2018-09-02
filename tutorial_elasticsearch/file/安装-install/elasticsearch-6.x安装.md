### elasticsearch 6.4 安装

#### rpm 安装
1. 上传rpm包到路径/usr/search
2. 解压 rpm -ivh elasticsearch-6.4.0.rpm  安装路径 /usr/share/elasticsearch 配置文件路径 /etc/elasticsearch/
3. 启动服务 service elasticsearch start  
   或者命令  
   sudo chkconfig --add elasticsearch  
   sudo -i service elasticsearch start  
   sudo -i service elasticsearch stop   
   sudo -i service elasticsearch status 
4. 测试是否安装成功 curl http://localhost:9200/

#### elasticsearch.yml 配置
```
# ======================== Elasticsearch Configuration =========================
#
# NOTE: Elasticsearch comes with reasonable defaults for most settings.
#       Before you set out to tweak and tune the configuration, make sure you
#       understand what are you trying to accomplish and the consequences.
#
# The primary way of configuring a node is via this file. This template lists
# the most important settings you may want to configure for a production cluster.
#
# Please consult the documentation for further information on configuration options:
# https://www.elastic.co/guide/en/elasticsearch/reference/index.html
#
# ---------------------------------- Cluster -----------------------------------
#
# Use a descriptive name for your cluster:
#
#cluster.name: my-application
#
# ------------------------------------ Node ------------------------------------
#
# Use a descriptive name for the node:
#
#node.name: node-1
#
# Add custom attributes to the node:
#
#node.attr.rack: r1
#
# ----------------------------------- Paths ------------------------------------
#
# Path to directory where to store the data (separate multiple locations by comma):
# 
path.data: /var/lib/elasticsearch
#
# Path to log files:
#
path.logs: /var/log/elasticsearch
#
# ----------------------------------- Memory -----------------------------------
#
# Lock the memory on startup:
#
bootstrap.memory_lock: true
#
# Make sure that the heap size is set to about half the memory available
# on the system and that the owner of the process is allowed to use this
# limit.
#
# Elasticsearch performs poorly when the system is swapping the memory.
#
# ---------------------------------- Network -----------------------------------
#
# Set the bind address to a specific IP (IPv4 or IPv6):
# 设置可以外网访问
network.host: 0.0.0.0
#
# Set a custom port for HTTP:
#
#http.port: 9200
#
# For more information, consult the network module documentation.
#
# --------------------------------- Discovery ----------------------------------
#
# Pass an initial list of hosts to perform discovery when new node is started:
# The default list of hosts is ["127.0.0.1", "[::1]"]
# 指定一组通信主机,在集群中的所有主机上为该属性设置相同的值,使用集群节点的名称来定义主机列表
#discovery.zen.ping.unicast.hosts: ["host1", "host2"]
#
# Prevent the "split brain" by configuring the majority of nodes (total number of master-eligible nodes / 2 + 1):
# 一个节点需要看到的具有master节点资格的最小数量,然后才能在集群中做操作.默认1.
#discovery.zen.minimum_master_nodes: 
#
# For more information, consult the zen discovery module documentation.
#
# ---------------------------------- Gateway -----------------------------------
#
# Block initial recovery after a full cluster restart until N nodes are started:
#
#gateway.recover_after_nodes: 3
#
# For more information, consult the gateway module documentation.
#
# ---------------------------------- Various -----------------------------------
#
# Require explicit names when deleting indices:
# true 禁用delete _all api
#action.destructive_requires_name
http.cors.enabled: true   
http.cors.allow-origin: "*"
```


> 防火墙打开9200端口
1. 查看防火墙  systemctl status firewalld  
2. 开启防火墙  systemctl start firewalld  
3. 关闭防火墙  systemctl stop firewalld  
4. 开启端口    firewall-cmd --zone=public --add-port=9200/tcp --permanent      
5. 重启防火墙  firewall-cmd --reload  
6. 查看端口是否开放 firewall-cmd --query-port=9200/tcp


> netstat -tunlp 查看开放的端口和监听信息