package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYWriteHelper implements ItemWriter<IQYDomain> {
    @Override
    public void write(List<? extends IQYDomain> list) throws Exception {
        if(!CollectionUtils.isEmpty(list)){
            System.out.println("list长度:"+list.size());
        }
    }
}
