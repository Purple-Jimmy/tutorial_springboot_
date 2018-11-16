package com.batch.step;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author jimmy
 * @date 2018/11/1621:18
 * 字符串转为大写
 */
public class Processor implements ItemProcessor<String,String> {

    @Override
    public String process(String s) throws Exception {
        return s.toUpperCase();
    }
}
