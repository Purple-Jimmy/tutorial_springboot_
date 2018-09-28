package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jimmy
 * @Date: 2018/9/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class IndexDemo {

    @Autowired
    private JestClient jestClient;

    /**
     * 查询索引是否存在
     */
    @Test
    public void existIndex() throws IOException {
        IndicesExists indicesExists = new IndicesExists.Builder("rabbitmq").build();
        JestResult jestResult = jestClient.execute(indicesExists);
        System.out.println(jestResult);
    }

    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndex.Builder index = new CreateIndex.Builder("news");
        JestResult jestResult = jestClient.execute(index.build());
        System.out.println(jestResult);
    }

    /**
     * 创建索引settings
     */
    @Test
    public void createIndexWithSettings() throws IOException {
        Map<String,Object> settingMap = new HashMap<>();
        settingMap.put("number_of_shards",3);
        settingMap.put("number_of_replicas",3);
        JestResult jestResult = jestClient.execute(new CreateIndex.Builder("articles").settings(settingMap).build());
        System.out.println(jestResult);
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        // DeleteIndex index = new DeleteIndex.Builder("articles1").build();
        // jestClient.execute(index);
        DeleteIndex.Builder index = new DeleteIndex.Builder("news");
        JestResult jestResult = jestClient.execute(index.build());
        System.out.println(jestResult);
    }

    /**
     * 优化索引
     * @throws IOException
     */
    @Test
    public void optimizeIndex() throws IOException {
        //TODO
      /*  Optimize optimize = new Optimize.Builder().build();
        JestResult jestResult = jestClient.execute(optimize);
        System.out.println(jestResult.getJsonString());*/
    }

}
