package com.batch.configurer;

import com.batch.domain.Message;
import com.batch.listener.MessageItemReadListener;
import com.batch.listener.MessageWriteListener;
import com.batch.step.MessageLineMapper;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;

/**
 * @author jimmy
 * @date 2018/11/1809:30
 */
@Component
public class MessageMigrationJobConfigurer {
    @Autowired
    private EntityManagerFactory entityManager;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private int CHUNK_SIZE = 1;
    private int SKIP_LIMIT = 2;
    private String MESSAGE_FILE = "classpath:message.txt";

    @Bean
    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
        return jobBuilderFactory.get("messageMigrationJob")
                .start(messageMigrationStep)
                .build();
    }

    @Bean
    public Step messageMigrationStep(@Qualifier("messageReader") FlatFileItemReader<Message> jsonMessageReader,
                                     @Qualifier("messageItemWriter") JpaItemWriter<Message> messageItemWriter
                                   ) {
        return stepBuilderFactory.get("messageMigrationStep")
                .<Message, Message>chunk(CHUNK_SIZE)
                .reader(jsonMessageReader).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageItemReadListener())
                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageWriteListener())
                .build();
    }

    @Bean
    @Qualifier("messageReader")
    public FlatFileItemReader<Message> reader() throws FileNotFoundException {
        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(ResourceUtils.getFile(MESSAGE_FILE)));
        // 方式1
        reader.setLineMapper(new MessageLineMapper());
        // 方式2
       /* reader.setLineMapper(new DefaultLineMapper<Message>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("objectId", "content");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Message>() {{
                setTargetType(Message.class);
            }});
        }});*/
        return reader;
    }

    @Bean
    @Qualifier
    public JpaItemWriter<Message> messageItemWriter() {
        JpaItemWriter<Message> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return writer;
    }

}
