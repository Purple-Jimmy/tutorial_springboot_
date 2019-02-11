### centos7上安装

1. 查看内核版本:uname -r
```
[root@ibz ~]# uname -r
3.10.0-862.14.4.el7.x86_64
```

2. 安装gcc
```
yum -y install gcc
yum -y install gcc-c++

查看gcc版本
gcc -v
```

3. 卸载旧版本
```
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

4. 安装stable镜像仓库
```
sudo yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
  
  
sudo yum-config-manager \
  --add-repo \
  http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
  
查看文件:etc/yum.repos.d/docker-ce.repo
[root@izuf64u1jmuzim8azzt99bz ~]# yum-config-manager \
>   --add-repo \
>   http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
Loaded plugins: fastestmirror
adding repo from: http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
grabbing file http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo to /etc/yum.repos.d/docker-ce.repo
repo saved to /etc/yum.repos.d/docker-ce.repo
```


5. 更新yum软件包索引
```
sudo yum makecache fast
```


6. 安装docker ce
```
sudo yum install docker-ce docker-ce-cli containerd.io
```

7. 启动
```
sudo systemctl start docker
```


8. 测试
```
查看版本号:docker version
永远的hello world:docker run hello-world
```


9. 配置加速器
```
1. 创建级联目录
mkdir -p /etc/docker
2. 修改配置
vim /etc/docker/daemon.json
3. 使配置生效
systemctl daemon-reload
4. 重启docker
systemctl restart docker
5. 测试
ps -ef|grep docker
docker pull busybox



sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://qqnlfhyp.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

10. 卸载
```
systemctl stop docker
yum -y remove docker-ce
rm -rf /var/lib/docker

```