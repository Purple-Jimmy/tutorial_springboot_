### dockerFile 解析

## dockerFile 是什么
类比java  
Hello.java    ----->  Hello.class  
docker images ----->  dockerFile  
是构建镜像的脚本文件

## 构建3步骤
1. 构建dockerFile文件
2. docker build
3. docker run


## dockerFile体系结构(保留字指令)
* FROM
```
当前构建的镜像基于哪个基础镜像
scratch:一切镜像的基础镜像
```

* MAINTAINER
```
镜像作者和邮箱信息
```

* RUN
```
容器构建时需要运行的命令
```

* EXPOSE
```
当前容器对外暴露的端口号
```

* WORKDIR
```
容器创建后,终端登录进来默认的工作目录.不指定的情况下默认是根目录
```

* ENV
```
构建过程中设置环境变量
```

* ADD
```
将宿主机目录下的文件copy进镜像并会自动处理URL和解压tar压缩包
```

* COPY
```
copy文件和目录到镜像中
COPY src dest
COPY ["src","dest"]
```

* VOLUME
```
容器数据卷,用于数据保存和持久化
```

* CMD
```
指定一个容器启动时要运行的命令
可以有多个CMD命令但只有最后一个才会生效
CMD会被docker run之后的参数替换
```

* ENTRYPOINT
```
指定一个容器启动时要运行的命令
```

* ONBUILD
```
当构建一个被继承的dockerFile时运行命令,父镜像在被子继承后父镜像的onbuild被触发
```


