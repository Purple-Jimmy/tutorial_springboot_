### canal安装

1. 下载 https://github.com/alibaba/canal/releases
2. 解压 tar -zxvf canal.deployer-1.0.25.tar.gz 
3. 配置 canal.properties,这个是系统根配置文件
```
canal.ip= 172.16.42.107  #canal服务所在IP地址
canal.port= 11111 
# 此模块为一个实例模块,canal支持一对多,所以这个实例的名称为:example #
canal.destinations= example
```
4. 配置 instance.properties,这个是instance级别的配置文件,每个instance一份  
   在配置canal之前,我们先连接mysql,查看master status,可以看到File:mysql-bin.000021 Position:4241 
```
mysql -uroot -p123456
show master status;
```
记住如上2个值
```
# binlog format/image check
#################################################
# mysql serverId
canal.instance.mysql.slaveId=234 # 不能与mysql配置文件中的slaveId重复
# position info
canal.instance.master.address=192.168.187.130:3306 # 为数据库地址 我的数据库在虚拟机中
canal.instance.master.journal.name=mysql-bin.000021 # 填入我们刚才查看的master status
canal.instance.master.position=4241
canal.instance.master.timestamp=
  
 
# table meta tsdb info
canal.instance.tsdb.enable=false # 为1.0.25的新功能 不是太了解 因为我们是快速配置 先关闭
canal.instance.tsdb.dir=${canal.file.data.dir:../conf}/${canal.instance.destination:}
canal.instance.tsdb.url=jdbc:h2:${canal.instance.tsdb.dir}/h2;CACHE_SIZE=1000;MODE=MYSQL;
#canal.instance.tsdb.url=jdbc:mysql://127.0.0.1:3306/canal_tsdb # 用不到一下三个配置 都注释掉
#canal.instance.tsdb.dbUsername=canal
#canal.instance.tsdb.dbPassword=canal
 
 
#canal.instance.standby.address =
#canal.instance.standby.journal.name =
#canal.instance.standby.position =
#canal.instance.standby.timestamp =
# username/password
canal.instance.dbUsername=canal # 自己数据库的信息 使用我们刚开权限的用户 canal
canal.instance.dbPassword=canal # 自己数据库的信息
canal.instance.defaultDatabaseName=mytest # 自己数据库的信息
canal.instance.connectionCharset=UTF-8 # 自己数据库的信息
# table regex
canal.instance.filter.regex=.*\\..*
```
5. 启动
```

```

#### 数据库配置
1. 配置mysql
```
[root@canal /]# vi etc/my.cnf

[mysqld]
log-bin=mysql-bin #添加这一行就ok
binlog-format=ROW #选择row模式
server_id=1 #配置mysql replaction需要定义，不能和canal的slaveId重复
```
2. mysql slave 相关权限
canal的原理是模拟自己为mysql slave,所以这里一定需要做为mysql slave的相关权限.
```
# 创建用户
mysql> CREATE USER canal IDENTIFIED BY 'canal';  
# 授权
mysql> GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ; # 授予所有权限
# 查看用户权限
mysql> show grants canal;
# 刷新权限
mysql> FLUSH PRIVILEGES;
```
3. 下面两个配置强烈建议配置,这样可以减小binlog的大小,忽略不需要关注的库的binlog,改完重启生效
```
/etc/my.cnf
binlog-do-db = oos_device   #配置需要同步的库
binlog-ignore-db = mysql    #配置不需要同步的库
service mysqld start
```
 
