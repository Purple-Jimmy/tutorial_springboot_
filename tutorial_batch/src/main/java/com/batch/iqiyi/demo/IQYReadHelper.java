package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYReadHelper{

    private FlatFileItemReader<IQYDomain> reader;

    public FlatFileItemReader<IQYDomain> syncReader() {
        try {
            reader = new FlatFileItemReader<IQYDomain>();
            reader.setEncoding("UTF-8");
            reader.setResource(new FileSystemResource(ResourceUtils.getFile("classpath:iqiyi3.txt")));
            reader.setLineMapper(new IQYDomainLineMapper());
        } catch (Exception e) {
           log.error("read file {} error.");
        }
        return reader;
    }
}
