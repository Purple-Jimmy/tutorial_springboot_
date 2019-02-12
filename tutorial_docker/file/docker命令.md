### docker命令

## 帮助命令
```
docker version
docker info
docker --help

启动docker
systemctl start docker

重启docker
systemctl restart docker
```


## 镜像命令

* 查看镜像(本地主机)
```
docker images
参数说明:
1. -a   列出本地所有的镜像(镜像是分层的)
2. -q   只显示镜像id
3. --digests   显示镜像的摘要信息
4. --no-trunc  显示镜像的完整信息
```


* 搜索镜像(docker hub)
```
docker search tomcat
参数说明:
1. -s     docker search -s 30 tomcat    star超过30的镜像
2.--no-trunc    显示完整的镜像描述
3.--automated   只列出自动构建的镜像
```


* 下载镜像
```
docker pull tomcat
拉取指定版本的镜像
docker pull tomcat:8.0
```


* 删除镜像
```
docker rmi tomcat
删除单个: docker rmi -f 镜像id
删除多个: docker rmi -f tomcat nginx
删除全部: docker rmi -f $(docker images -qa)
```


## 容器命令

* 列出所有正在运行的容器
```
docker ps
参数说明:
1. -a:列出所有当前正在的运行容器和历史上运行过的
2. -l:显示最近创建的容器
3. -n:显示最近创建的n个容器
4. -q:静默显示,只显示容器编号
5. --no-trunc:不截断输出



```

* 启动容器
```
docker run 
参数说明:
1. --name:指定容器名字
2. -d:后台运行容器并返回容器id
3. -i:以交互模式运行容器,通常与-t一起使用
4. -t:为容器重新分配一个伪输入终端
5. -P:随机端口映射 
6. -p:指定端口映射

交互式启动  docker run -it tomcat --name tomcat-springboot
守护式启动  docker run -d  tomcat --name tomcat-springboot
```
重要:docker容器后台运行,必须有一个前台进程,容器运行的命令如果不是那些一直挂起的命令(tail,top),就会自动退出



* 退出容器
```
方式1:exit      容器停止退出
方式2:ctrl+p+q  容器不停止退出
```

* 启动容器
```
docker start 容器id或名字
```

* 重启容器
```
docker restart 容器id或名字
```

* 停止容器
```
docker stop 容器id或名字
```

* 强制停止容器
```
docker kill 容器id或名字
```

* 删除已停止的容器
```
docker rm 容器id或名字
删除多个: docker rm -f $(docker ps -a -q)
```

* 查看容器日志
```
docker logs -f -t --tail 容器id
参数说明:
1. -t 时间戳
2. -f 跟随最新的日志打印
3. --tail 100 显示最后100条
```

* 查看容器内部运行的进程
```
docker top 容器id
```


* 查看容器内部细节
```
docker inspect 容器id
```


* 进入正在运行的容器并以命令行交互
```
docker attach 容器id
直接进入容器启动命令的终端,不会启动新的进程

docker exec -it 容器id bashshell
在容器中打开新的终端,并且可以启动新的进程
```


* 从容器内拷贝文件到主机上
```
docker cp 容器id:容器内路径 目的主机路径
```