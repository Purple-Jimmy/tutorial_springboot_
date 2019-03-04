package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYWriteHelper {

    private FlatFileItemWriter<IQYWriterDomain> writer;

    public FlatFileItemWriter<IQYWriterDomain> syncWriter() {
        try {
            writer = new FlatFileItemWriter<IQYWriterDomain>();
            writer.setAppendAllowed(true);
            writer.setEncoding("UTF-8");
            writer.setResource(new FileSystemResource("F:\\tutorial_space\\tutorial_springboot_\\tutorial_batch\\file\\iqiyi_data.txt"));
            writer.setLineAggregator(new DelimitedLineAggregator<IQYWriterDomain>() {{
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<IQYWriterDomain>() {{
                    setNames(new String[]{"id","name"});
                }});
            }});
        } catch (Exception e) {
            log.error("writer file error.");
        }
        return writer;
    }


   /* @Override
    public void write(List<? extends IQYDomain> list) throws Exception {
        if(!CollectionUtils.isEmpty(list)){
            System.out.println("list长度:"+list.size());

            FlatFileItemWriter<IQYDomain> flatFileItemWriter = new FlatFileItemWriter<>();
            flatFileItemWriter.setAppendAllowed(true);
            flatFileItemWriter.setEncoding("UTF-8");
            flatFileItemWriter.setResource(new FileSystemResource("F:\\tutorial_space\\tutorial_springboot_\\tutorial_batch\\file\\hello.txt"));
            flatFileItemWriter.setLineAggregator(new DelimitedLineAggregator<IQYDomain>() {{
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<IQYDomain>() {{
                    setNames(new String[]{"id","name"});
                }});
            }});
        }
    }*/
}
