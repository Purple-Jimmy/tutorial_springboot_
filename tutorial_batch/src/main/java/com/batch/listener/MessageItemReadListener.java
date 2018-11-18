package com.batch.listener;

import com.batch.domain.Message;
import com.batch.step.Writer;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @author jimmy
 * @date 2018/11/1809:37
 */
public class MessageItemReadListener implements ItemReadListener<Message> {

    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Message message) {

    }

    @Override
    public void onReadError(Exception e) {
        try {
            errorWriter.write(new ArrayList<>());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
