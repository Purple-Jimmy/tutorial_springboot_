package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYProcessor implements ItemProcessor<IQYDomain, IQYWriterDomain> {
    @Override
    public IQYWriterDomain process(IQYDomain iqyDomain) throws Exception {
        log.info("process is{}",iqyDomain.getId());
        IQYWriterDomain iqyWriterDomain = null;
        if(iqyDomain.getId().equals("90009401")){
            return iqyWriterDomain;
        }else{
            iqyWriterDomain = new IQYWriterDomain();
            iqyWriterDomain.setId(iqyDomain.getId());
            iqyWriterDomain.setName(iqyDomain.getName());
            return iqyWriterDomain;
        }
    }
}
