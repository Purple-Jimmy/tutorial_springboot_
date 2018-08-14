package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.ClearCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  21:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class CacheTest {
    @Autowired
    private JestClient jestClient;

    /**
     * 清空缓存
     * @throws IOException
     */
    @Test
    public void clearCache() throws IOException {
        ClearCache clearIndex = new ClearCache.Builder().build();
        JestResult jestResult = jestClient.execute(clearIndex);
        System.out.println(jestResult.isSucceeded());
    }
}
