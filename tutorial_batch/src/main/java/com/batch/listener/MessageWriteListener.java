package com.batch.listener;

import com.batch.domain.Message;
import com.batch.step.Writer;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jimmy
 * @date 2018/11/1809:45
 */
public class MessageWriteListener implements ItemWriteListener<Message> {
    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends Message> list) {

    }

    @Override
    public void afterWrite(List<? extends Message> list) {

    }

    /**
     * chuck机制，所以write的listener传入参数是一个List，因为它是累积到一定的数量才一起写入。
     * @param e
     * @param list
     */
    @Override
    public void onWriteError(Exception e, List<? extends Message> list) {
      //  errorWriter.write(format("%s%n", e.getMessage()));
        for (Message message : list) {
            //errorWriter.write(format("Failed writing message id: %s", message.getObjectId()));
        }
    }
}
