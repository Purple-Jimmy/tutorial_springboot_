### instance配置示例 v1.0.25
```
#################################################
## mysql serverId
canal.instance.mysql.slaveId=12345678
# position info
canal.instance.master.address=192.168.50.116:3306
canal.instance.master.journal.name=
canal.instance.master.position=
canal.instance.master.timestamp=


# table meta tsdb info
canal.instance.tsdb.enable=false
canal.instance.tsdb.dir=${canal.file.data.dir:../conf}/${canal.instance.destination:}
canal.instance.tsdb.url=jdbc:h2:${canal.instance.tsdb.dir}/h2;CACHE_SIZE=1000;MODE=MYSQL;
#canal.instance.tsdb.url=jdbc:mysql://127.0.0.1:3306/canal_tsdb
#canal.instance.tsdb.dbUsername=canal
#canal.instance.tsdb.dbPassword=canal


#canal.instance.standby.address =
#canal.instance.standby.journal.name =
#canal.instance.standby.position = 
#canal.instance.standby.timestamp = 
# username/password
canal.instance.dbUsername=canal
canal.instance.dbPassword=canal
canal.instance.defaultDatabaseName=test_s
canal.instance.connectionCharset=UTF-8
# table regex
canal.instance.filter.regex= .*\\..*
# table black regex
canal.instance.filter.black.regex=
#################################################
[root@DEV-14 search]# ls
h2.mv.db  instance.properties  meta.dat
[root@DEV-14 search]# vi instance.properties 
[root@DEV-14 search]# vi instance.properties 
[root@DEV-14 search]# vi instance.properties 
--------------------------------------------------------------------------------------------
#################################################
## mysql serverId
canal.instance.mysql.slaveId=12345678
# position info
canal.instance.master.address=192.168.50.116:3306
canal.instance.master.journal.name=
canal.instance.master.position=
canal.instance.master.timestamp=


# table meta tsdb info
canal.instance.tsdb.enable=false
canal.instance.tsdb.dir=${canal.file.data.dir:../conf}/${canal.instance.destination:}
canal.instance.tsdb.url=jdbc:h2:${canal.instance.tsdb.dir}/h2;CACHE_SIZE=1000;MODE=MYSQL;
#canal.instance.tsdb.url=jdbc:mysql://127.0.0.1:3306/canal_tsdb
#canal.instance.tsdb.dbUsername=canal
#canal.instance.tsdb.dbPassword=canal


#canal.instance.standby.address =
#canal.instance.standby.journal.name =
#canal.instance.standby.position =
#canal.instance.standby.timestamp =
# username/password
canal.instance.dbUsername=canal
canal.instance.dbPassword=canal
canal.instance.defaultDatabaseName=
canal.instance.connectionCharset=UTF-8
# table regex   canal.instance.filter.regex= test_s.book
canal.instance.filter.regex= .*\\..*
# table black regex
canal.instance.filter.black.regex= mysql\\..* 
```