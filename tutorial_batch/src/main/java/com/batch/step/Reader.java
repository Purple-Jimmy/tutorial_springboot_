package com.batch.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author jimmy
 * @date 2018/11/1621:13
 * 从数组中读取3个字符串
 */
public class Reader implements ItemReader<String> {

    private String[] messages = { "javainuse.com",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example" };

    private int count = 0;


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }
}
