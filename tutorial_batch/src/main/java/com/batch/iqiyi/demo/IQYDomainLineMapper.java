package com.batch.iqiyi.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.LineMapper;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Slf4j
public class IQYDomainLineMapper implements LineMapper<IQYDomain> {
    public IQYDomainLineMapper() {
    }

    @Override
    public IQYDomain mapLine(String line, int lineNumber) {
        IQYDomain bean = new IQYDomain();
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create();
            bean = gson.fromJson(line, IQYDomain.class);
        } catch (Exception e) {
            log.error("mapLine is error.line:{},lineNumber:{}", line, lineNumber);
        }
        return bean;
       /* Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        IQYDomain bean = gson.fromJson(line, IQYDomain.class);
        return bean;*/

    }
}
