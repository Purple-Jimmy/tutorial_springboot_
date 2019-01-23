package com.batch.iqiyi.demo;

import com.batch.step.Writer;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
public class IQYWriteListener implements ItemWriteListener<IQYDomain> {
    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends IQYDomain> list) {

    }

    @Override
    public void afterWrite(List<? extends IQYDomain> list) {

    }

    /**
     * chuck机制,所以write的listener传入参数是一个List,因为它是累积到一定的数量才一起写入
     * @param e
     * @param list
     */
    @Override
    public void onWriteError(Exception e, List<? extends IQYDomain> list) {
        //  errorWriter.write(format("%s%n", e.getMessage()));
        for (IQYDomain domain : list) {
            //errorWriter.write(format("Failed writing message id: %s", message.getObjectId()));
        }
    }
}
