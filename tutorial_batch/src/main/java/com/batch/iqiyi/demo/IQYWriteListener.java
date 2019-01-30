package com.batch.iqiyi.demo;

import com.batch.step.Writer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYWriteListener implements ItemWriteListener<IQYWriterDomain> {
    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends IQYWriterDomain> list) {
        log.info("writer before list size {}",list.size());
    }

    @Override
    public void afterWrite(List<? extends IQYWriterDomain> list) {
        log.info("writer after list size {}",list.size());
    }

    /**
     * chuck机制,所以write的listener传入参数是一个List,因为它是累积到一定的数量才一起写入
     * @param e
     * @param list
     */
    @Override
    public void onWriteError(Exception e, List<? extends IQYWriterDomain> list) {
          //errorWriter.write(format("%s%n", e.getMessage()));
        for (IQYWriterDomain domain : list) {
            //errorWriter.write(format("Failed writing message id: %s", message.getObjectId()));
        }
    }
}
