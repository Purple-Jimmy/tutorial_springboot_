package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: jimmy
 * @Date: 2018/10/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class TestDemo {
    @Autowired
    private JestClient jestClient;
}
