package com.batch.listener;

import com.batch.domain.Message;
import com.batch.step.Writer;
import org.springframework.batch.core.ItemReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jimmy
 * @date 2018/11/1809:37
 */
public class MessageItemReadListener implements ItemReadListener<Message> {

    private Writer errorWriter;

    public MessageItemReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Message message) {

    }

    @Override
    public void onReadError(Exception e) {
        try {
            List<String> list = new ArrayList<>();
            list.add(e.getMessage());
            errorWriter.write(list);
            System.out.println("write error");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
