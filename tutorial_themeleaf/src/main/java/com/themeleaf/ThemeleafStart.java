package com.themeleaf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @Author: jimmy
 * @Date: 2018/7/27
 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ThemeleafStart {

    public static void main(String[] args) {
        SpringApplication.run(ThemeleafStart.class,args);
    }
}
