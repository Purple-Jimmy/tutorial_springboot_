package com.batch.configurer;

import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jimmy
 * @date 2018/11/1809:19
 * 多job
 */
@Configuration
public class MultiJobConfigurer {
    /**
     * hello world
     * @return
     */
    @Bean
    public ApplicationContextFactory batchSampleJobContext() {
        return new GenericApplicationContextFactory(BatchSampleConfigurer.class);
    }

    /**
     * 数据迁移job
     * @return
     */
    @Bean
    public ApplicationContextFactory messageMigrationJobContext() {
        return new GenericApplicationContextFactory(MessageMigrationJobConfigurer.class);
    }

    @Bean
    public ApplicationContextFactory JpaSampleContext() {
        return new GenericApplicationContextFactory(JpaSampleConfigurer.class);
    }

}
