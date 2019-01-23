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
public class IQYProcessor implements ItemProcessor<IQYDomain, IQYDomain> {
    @Override
    public IQYDomain process(IQYDomain iqyDomain) throws Exception {
        //TODO
        return iqyDomain;
    }
}
