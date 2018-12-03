package com.datasource.configurer;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * @author jimmy
 * @date 2018/12/321:58
 * 多数据原配置
 */
@Configuration
public class DataSourceConfigurer {

   /* @Primary
    @Bean(name = "primaryDataSource")
    *//**
     *  spring装配bean的唯一标识
     *//*
    @Qualifier(value = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.primary.datasource")
    public HikariDataSource primaryDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    @Bean(name = "secondDataSource")
    @Qualifier(value = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
*/

    @Bean
    @Primary
    @ConfigurationProperties("spring.primary.datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.primary.datasource.configuration")
    public HikariDataSource primaryDataSource() {
        return primaryDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }


    @Bean
    @ConfigurationProperties("spring.second.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.second.datasource.configuration")
    public HikariDataSource secondDataSource() {
        return secondDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }
}
