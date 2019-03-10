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


## pom配置
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <!-- Docker maven plugin -->
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <!--<version>1.0.0</version>-->
            <!--将插件绑定在某个phase执行-->
          <!--  <executions>
                <execution>
                    <id>build-image</id>
                    &lt;!&ndash;将插件绑定在package这个phase上。也就是说，用户只需执行mvn package ，就会自动执行mvn docker:build&ndash;&gt;
                    <phase>package</phase>
                    <goals>
                        <goal>build</goal>
                    </goals>
                </execution>
            </executions>-->
            <configuration>
                <!--指定生成的镜像名-->
                <imageName>${docker.image.prefix}/${project.artifactId}</imageName>
                <!--指定标签-->
               <!-- <imageTags>
                    <imageTag>latest</imageTag>
                </imageTags>-->
                <!-- 指定Dockerfile路径${project.basedir}项目根路径下-->
                <dockerDirectory>${project.basedir}/src/main/docker</dockerDirectory>
                <!--指定远程 docker api地址-->
                <!--<dockerHost>http://47.101.149.94:2375</dockerHost>-->
                <!-- 复制 jar 包到 docker 容器指定目录配置 -->
                <resources>
                    <resource>
                        <targetPath>/</targetPath>
                        <!--jar包所在的路径,此处配置对应的target目录-->
                        <directory>${project.build.directory}</directory>
                        <!-- 需要包含的jar包,这里对应的是 Dockerfile中添加的文件名　-->
                        <include>${project.build.finalName}.jar</include>
                    </resource>
                </resources>
                <!-- 以下两行是为了docker push到DockerHub使用的。 -->
               <!-- <serverId>docker-hub</serverId>
                <registryUrl>https://index.docker.io/v1</registryUrl>-->
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Dockerfile
```
FROM java:8
VOLUME /tmp
ADD tutorial_docker-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
```



## 启动镜像
```
docker run -d -p 18080:8085 --name docker-resource 60c98a0d15bc
```

## 访问
```
http://ip:18080/docker
```
