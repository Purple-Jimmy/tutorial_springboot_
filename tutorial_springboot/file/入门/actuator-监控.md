### actuator 监控
1. 引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
2. 访问 http://localhost:8081/actuator/info

3. 修改访问路径
```
management.endpoints.web.base-path=/application
```
4. 打开其他访问
```
management.endpoints.web.expose=*
```
