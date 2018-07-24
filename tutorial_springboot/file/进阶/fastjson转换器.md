### fastjson
1. 引入依赖
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.47</version>
</dependency>
```

2. 配置
```
@Configuration
public class CustomerWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * fastjson转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("-----------fast json-------------------");
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //  SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题,默认为false
                //  SerializerFeature.WriteNullBooleanAsFalse//Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteMapNullValue//是否输出值为null的字段,默认为false
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }
}
```