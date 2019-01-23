package com.batch.iqiyi.demo;

import com.batch.step.Writer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Slf4j
public class IQYReadListener implements ItemReadListener<IQYDomain> {
    private Writer errorWriter;

    public IQYReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void beforeRead() {
        log.info("IQYReadListener before start");
    }

    @Override
    public void afterRead(IQYDomain iqyDomain) {
        log.info("IQYReadListener afterRead end");
    }

    @Override
    public void onReadError(Exception e) {
        try {
            List<String> list = new ArrayList<>();
            list.add(e.getMessage());
            errorWriter.write(list);
            log.info("IQYReadListener error {}",list);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
