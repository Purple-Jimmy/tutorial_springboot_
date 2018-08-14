package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  14:51
 * http://blog.csdn.net/vinegar93/article/details/53223819
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class MappingTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 创建映射
     */
    @Test
    public void createMapping() throws IOException {
        String index = "news";
        String type = "article";
        String source = "{\"" + type + "\":{\"properties\":{"
                + "\"id\":{\"type\":\"integer\"}"
                + ",\"name\":{\"type\":\"text\"}"
                + ",\"birth\":{\"type\":\"date\"}"
                + "}}}";
        PutMapping putMapping = new PutMapping.Builder(index, type, source).build();
        JestResult jestResult = jestClient.execute(putMapping);
        System.out.println(jestResult.isSucceeded());
    }

    /**
     * 查看映射
     */
    @Test
    public void getMapping() throws IOException {
        String index = "news";
        String type = "article";
        GetMapping getMapping = new GetMapping.Builder().addIndex(index).addType(type).build();
        JestResult jestResult = jestClient.execute(getMapping);
        System.out.println(jestResult.getSourceAsString());
    }

}
