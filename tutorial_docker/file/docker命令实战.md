### docker命令实战

## 启动tomcat
```
指定端口,并指定容器名字为springboot_tutorial
docker run -it --name springboot_tutorial -p 8084:8080 tomcat

参数说明:
8888:docker的端口
8080:docker里面tomcat运行的端口
```
访问:http://ip:8088/


## 进入tomcat,删除
```
docker ps 查看当前正在运行的容器
docker exec -it dd98e89626eb /bin/bash   进入容器
rm -rf docs 删除webapp下面的docs
```


## commit不带docs的tomcat
```
docker commit -a="jimmy" -m="不带docs的tomcat" dd98e89626eb tutorial/tomcat01:1.0


[root@izuf64u1jmuzim8azzt99bz ~]# docker commit -a="jimmy" -m="不带docs的tomcat" dd98e89626eb tutorial/tomcat01:1.0
sha256:9046dbc43a4ca463ab70e025b0e86405f68113d8a4a71d17b6892ec0075c345f


[root@izuf64u1jmuzim8azzt99bz ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
tutorial/tomcat01   1.0                 9046dbc43a4c        31 seconds ago      463MB
tomcat              latest              168588387c68        3 days ago          463MB
```