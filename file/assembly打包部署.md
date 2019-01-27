## spring-boot-assembly打包部署
1. 在spring boot项目中使用maven profiles和maven assembly插件根据不同环境打包成tar.gz或者zip
2. 将spring boot项目中的配置文件提取到外部config目录中
3. 将spring boot项目中的启动jar包移动到boot目录中
4. 将spring boot项目中的第三方依赖jar包移动到外部lib目录中
5. bin目录中是启动，停止，重启服务命令
6. 打包后的目录结构类似于tomcat/maven目录结构