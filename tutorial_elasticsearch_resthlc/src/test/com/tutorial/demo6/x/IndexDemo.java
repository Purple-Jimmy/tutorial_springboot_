package com.tutorial.demo6.x;

import com.elasticsearch.ElasticsearchStart;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author jimmy
 * @date 2019-03-2516:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
@Slf4j
public class IndexDemo {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public static final String INDEX_NAME   = "city_index";
    public static final String MAPPING_NAME = "tutorial_mapping";


    /**
     * 判断索引是否存在
     * @throws IOException
     */
    @Test
    public void existTest() throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(INDEX_NAME);
        request.local(false);
        request.humanReadable(true);

        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }



}
