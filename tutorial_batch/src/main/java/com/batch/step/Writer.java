package com.batch.step;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author jimmy
 * @date 2018/11/1621:21
 * 字符串输出
 */
public class Writer implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String str:list){
            System.out.println(str);
        }
    }
}
