package com.datasource.configurer;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * @author jimmy
 * @date 2018/12/321:58
 * 多数据原配置
 */
@Configuration
public class DataSourceConfigurer {

    @Primary
    @Bean(name = "primaryDataSource")
    /**
     *  spring装配bean的唯一标识
     */
    @Qualifier(value = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.primary.datasource")
    public DataSource primaryDataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader()).type(HikariDataSource.class).build();
    }


    @Bean(name = "secondDataSource")
    @Qualifier(value = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSource secondDataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader()).type(HikariDataSource.class).build();
    }



}
