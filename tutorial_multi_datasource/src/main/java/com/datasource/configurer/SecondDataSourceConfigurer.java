package com.datasource.configurer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @author jimmy
 * @date 2018/12/322:29
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager",
        basePackages = {"com.datasource.data2.repository"})
public class SecondDataSourceConfigurer {
    @Resource
    @Qualifier("secondDataSource")
    private DataSource secondDataSource;

    @Resource
    private JpaProperties jpaProperties;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource)
                .packages("com.datasource.domain")
                .persistenceUnit("second")
                .build();
    }

    @Primary
    @Bean(name = "secondEntityManager")
    public EntityManager secondEntityManager(EntityManagerFactoryBuilder builder) {
        return secondEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondEntityManagerFactory(builder).getObject());
    }

    /*protected Map<String,Object> getVendorProperties(DataSource dataSource) {
        //return jpaProperties.getProperties(new HibernateSettings());

    }*/
}
