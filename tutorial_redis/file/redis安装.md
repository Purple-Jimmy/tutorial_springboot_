### redis 安装 4.x
1. 下载,上传
2. 解压 tar -zxvf redis-4.0.1.tar.gz
3. 编译,安装
```
进入 cd redis-4.0.10
编译 make
安装 make install
```
4. 配置,修改redis.conf
```
daemonize yes 后台启动
bind127.0.0.1 注释掉使得可以远程访问
protected-mode no 设置为no
设置登录认证 redis.conf中的requirepass属性
```
5. 启动 redis-server ../redis.conf

6. 开启端口  
```
firewall-cmd --zone=public --add-port=6379/tcp --permanent
firewall-cmd --reload
```

