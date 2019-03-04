## springboot使用redis做缓存
1. 启动类添加注解@EnableCaching开启缓存
2. redis配置类继承CachingConfigurerSupport
3. yml中配置spring.catch.type=redis