### devtools 热部署

1. 添加依赖
```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <version>2.0.5.RELEASE</version>
  <optional>true</optional>
</dependency>
```

2. idea设置
当修改Java类后,IDEA默认是不自动编译的,而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用,
所以需要设置IDEA的自动编译
```
1. File-Settings-Compiler-Build Project automatically
2. ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running
```

3. 项目设置
```
#热部署生效
spring.devtools.restart.enabled: true
#设置重启的目录
#spring.devtools.restart.additional-paths: src/main/java
#classpath目录下的WEB-INF文件夹内容修改不重启
spring.devtools.restart.exclude: WEB-INF/**

在SprintApplication.run之前调用
System.setProperty("spring.devtools.restart.enabled", "false");
可以完全关闭重启支持
```

4. 测试
```
1. 修改类–>保存:应用会重启
2. 修改配置文件–>保存:应用会重启
3. 修改页面–>保存:应用不会重启但会重新加载,页面会刷新(原理是将spring.thymeleaf.cache设为false)
```

5. 其他
默认情况下,/META-INF/maven,/META-INF/resources,/resources,/static,/templates,/public这些文件夹下的文件修改不会使应用重启,
但是会重新加载(devtools内嵌了一个LiveReload Server,当资源发生改变时,浏览器刷新)
