# springboot docker 部署

## 打开tcp管理端口
1. 创建目录/etc/systemd/system/docker.service.d
```
mkdir /etc/systemd/system/docker.service.d
```
2. 在这个目录下创建tcp.conf文件并添加内容
```
cat > /etc/systemd/system/docker.service.d/tcp.conf <<EOF
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H unix:///var/run/docker.sock -H tcp://0.0.0.0:2375
EOF
```
3. 使配置生效并重启docker
```
systemctl daemon-reload
systemctl restart docker
```
4. 查看端口是否打开
```
ps aux |grep dockerd
或者
netstat -an | grep 2375
```
5. CentOS7还可以通过修改/etc/sysconfig/docker文件中的 OPTIONS来达到同样的目的
```
OPTIONS='--selinux-enabled -H unix:///var/run/docker.sock -H tcp://0.0.0.0:2375'
```
6. 关闭TCP管理端口
```
rm  -rf /etc/systemd/system/docker.service.d/tcp.conf
systemctl daemon-reload
systemctl restart docker
ps aux |grep dockerd
```





## 启动镜像
```
docker run -d -p 18080:8085 --name docker-resource 60c98a0d15bc
```
