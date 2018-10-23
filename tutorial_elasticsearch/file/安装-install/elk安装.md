### centos7安装efk(elasticsearch+filebeat+kibana)
> 安装elasticsearch 
1. 上传rpm包到路径/usr/search
2. 解压 rpm -ivh elasticsearch-6.1.1.rpm 安装路径在/usr/share/elasticsearch
3. 启动服务 service elasticsearch start  
   或者命令  
   sudo chkconfig --add elasticsearch  
   sudo -i service elasticsearch start  
   sudo -i service elasticsearch stop   
   sudo -i service elasticsearch status 
4. 测试是否安装成功 curl http://localhost:9200/
5. 日志目录 /var/log/elasticsearch/


# groupadd es
# useradd es -g es -p es
# chown es:es ${elasticsearch_HOME}/
# sudo su es
# ./bin/elasticsearch

vim /usr/lib/systemd/system/elasticsearch.service
MAX_LOCKED_MEMORY=unlimited

sysctl -p


> 安装kibana
1. 上传rpm包到路径/usr/search
2. 解压 rpm -ivh kibana-6.1.1-x86_64.rpm  安装路径在/usr/share/kibana
3. 修改yml文件 server.host: "0.0.0.0"，可以远程访问,yml路径在/etc/kibana
4. 命令  
   sudo chkconfig --add kibana   # 设置自动启动    
   sudo -i service kibana start  
   sudo -i service kibana stop  
   sudo -i service kibana restart   
   或者    
   sudo /bin/systemctl daemon-reload  
   nohup ./bin/kibana & 后台启动  
5. 日志目录 /var/log/kibana
    
    
> 安装filebeat
1. 上传rpm包到路径/usr/search
2. 解压 rpm -ivh filebeat-6.1.1-x86_64.rpm  安装路径在/usr/share/filebeat
3. 修改yml文件,yml路径在/etc/filebeat
4. 命令    
   sudo /etc/init.d/filebeat start  
   sudo /etc/init.d/filebeat restart  
   sudo /etc/init.d/filebeat status  
5. 日志目录 /var/log/filebeat

> 安装logstash 
1. 上传rpm包到路径/usr/search  
2. 解压rpm -ivh logstash-6.1.1.rpm  
3. 修改yml文件,yml路径在/etc/logstash    
4. 修改/etc/logstash/startup.options中JAVACMD参数,JAVACMD=${JAVA_HOME}/bin/java    
5. 验证发送消息到elasticsearch  

```
在/etc/logstash/conf.d下面创建test.conf,内容如下:

input {
     stdin{}
 }

output {
     stdout{
          codec => rubydebug
      }
     elasticsearch {
          hosts => ["36.111.193.248:9200"]
          index => "logstash-test-%{+YYYY.MM.dd}"
      }
  }
  
执行:/usr/share/logstash/bin/logstash -t -f /etc/logstash/conf.d/test.conf 
其中-t表示测试配置文件但是并不启动,-f表示指定测试文件
/usr/share/logstash/bin/logstash -f /etc/logstash/conf.d/test.conf 
```

    
> 防火墙打开9200端口
1. 查看防火墙  systemctl status firewalld  
2. 开启防火墙  systemctl start firewalld  
3. 关闭防火墙  systemctl stop firewalld  
4. 开启端口    firewall-cmd --zone=public --add-port=9200/tcp --permanent      
5. 重启防火墙  firewall-cmd --reload  
6. 查看端口是否开放 firewall-cmd --query-port=9200/tcp


> netstat -tunlp 查看开放的端口和监听信息

***

> 配置文件参数配置-elasticsearch
```
cluster.name: demon # 设置集群名称,开启了自发现功能后,ES会按照此集群名称进行集群发现
node.name: elk-1 #设置节点名称
bootstrap.memory_lock: false #配置内存禁用交换分区
network.host: 0.0.0.0  # 设置 外网访问
discovery.zen.ping.unicast.hosts: ["0.0.0.0"] # 设置 集群节点发现列表
discovery.zen.minimum_master_nodes: 2   #集群可做master的最小节点数
http.cors.enabled: true   
http.cors.allow-origin: "*"
```


> 配置文件参数配置-filebeat
```
#=========================== Filebeat prospectors =============================
type: log
enabled: true
paths:
    - D:\data\logs\yst-search_service.log
document_type: service_log
    
#============================= Filebeat modules ===============================
#================================ Outputs =====================================
#-------------------------- Elasticsearch output ------------------------------
output.elasticsearch:
  hosts: ["36.111.193.248:9200"] #输出到elasticsearch地址
  #protocol: "https"
  #username: "elastic"
  #password: "changeme"

```

> 配置文件参数配置-logstash


> 配置文件参数配置-kibana


##### 安装遇到的问题
> logstash
1. 原因:在/usr/share/logstash/目录下没有找到config文件  
   解决:建立软连接 ln -sv /etc/logstash /usr/share/logstash/config
```
WARNING: Could not find logstash.yml which is typically located in $LS_HOME/config or /etc/logstash. You can specify the path using --path.settings. Continuing using the defaults
Could not find log4j2 configuration at path /usr/share/logstash/config/log4j2.properties. Using default config which logs errors to the console
```
