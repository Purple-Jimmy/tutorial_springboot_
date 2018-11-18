package com.batch.step;

import com.batch.domain.Message;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.LineMapper;

/**
 * @author jimmy
 * @date 2018/11/1809:43
 */
@Slf4j
public class MessageLineMapper implements LineMapper<Message> {
    private MappingJsonFactory factory = new MappingJsonFactory();
    Gson gson = new Gson();
    @Override
    public Message mapLine(String line, int lineNumber) throws Exception {
        log.info("lineNumber {}",lineNumber);
        JsonParser parser = factory.createParser(line);
       // Map<String, Object> map = (Map) parser.readValueAs(Map.class);
        Message message =  gson.fromJson(line,Message.class);

        return message;
    }
}
