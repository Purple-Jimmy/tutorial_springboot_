package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author: jimmy
 * @Date: 2018/8/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class IndexDemo {
    @Autowired
    private JestClient jestClient;

    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndex.Builder index = new CreateIndex.Builder("news");
        jestClient.execute(index.build());
    }

    /**
     * 创建索引settings
     */
    @Test
    public void createIndexWithSettings() throws IOException {
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards",3);
        settingsBuilder.put("number_of_replicas",1);

        jestClient.execute(new CreateIndex.Builder("articles").settings(settingsBuilder.build()).build());
    }

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        // DeleteIndex index = new DeleteIndex.Builder("articles1").build();
        // jestClient.execute(index);
        DeleteIndex.Builder index = new DeleteIndex.Builder("tutorial1");
        jestClient.execute(index.build());
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

    /**
     * 索引是否存在
     * @throws IOException
     */
    @Test
    public void isExistIndex() throws IOException {
        //TODO
        /*IndicesExists indicesExists = new IndicesExists.Builder("tutorial").build();
        JestResult jestResult = jestClient.execute(indicesExists);
        System.out.println(jestResult.getJsonString());*/
    }
}
