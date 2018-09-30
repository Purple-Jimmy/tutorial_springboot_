package com.tutorial.configurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.tutorial.interceptor.JwtTokenInterceptor;
import com.tutorial.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy. 2018/3/30  16:00
 * WebMvcConfigurer 配置 拦截器 过滤器
 */
@Configuration
public class CustomerWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    /**
     * 注册拦截器
     */
    // 方式1
    @Override
    public void addInterceptors(InterceptorRegistry registry){
       // System.out.println("===========拦截器==============");
       // registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        //jwt token 拦截器
        //registry.addInterceptor(jwtTokenInterceptor).addPathPatterns("/api/**");
    }

    /**
     * fastjson转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
   //     System.out.println("-----------fast json-------------------");
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //  SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.DisableCircularReferenceDetect,//消除对同一对象循环引用的问题,默认为false
                //  SerializerFeature.WriteNullBooleanAsFalse//Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteMapNullValue//是否输出值为null的字段,默认为false
        );
        //编码格式
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * view controller
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        System.out.println("===========view controller==============");
       // registry.addViewController("/login").setViewName("login");
    }
}
