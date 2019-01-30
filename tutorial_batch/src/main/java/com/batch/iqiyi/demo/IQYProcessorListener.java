package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.batch.core.ItemProcessListener;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYProcessorListener implements ItemProcessListener<IQYDomain, IQYWriterDomain> {
    @Override
    public void beforeProcess(IQYDomain iqyDomain) {
        log.info("processor after {}",iqyDomain.getId());
    }

    @Override
    public void afterProcess(IQYDomain iqyDomain, IQYWriterDomain iqyWriterDomain) {
        log.info("processor before {} after {}",iqyDomain.getId(),iqyWriterDomain != null?iqyWriterDomain.getId():"xxx");
    }

    @Override
    public void onProcessError(IQYDomain iqyDomain, Exception e) {
        log.info("processor error {}",iqyDomain.getId());
    }

}
