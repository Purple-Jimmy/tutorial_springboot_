package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  15:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class DocumentDemo {
    @Autowired
    private JestClient jestClient;

    /**
     * 索引文档
     */
    @Test
    public void indexDocument() throws IOException {
        /*Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("test");
        movie.setLeadRole(new String[]{"刘德华"});
        movie.setStyle("动作");
        movie.setGrade(7.8F);
        Zone zone = new Zone();
        zone.setCountry("中国");
        zone.setCity("香港");
        movie.setZone(zone);
        movie.setDesc("这是测试");
        movie.setReleaseDate("2018-09-07");
        Index index = new Index.Builder(movie).index("twitter").type("tweet").build();
        //
        jestClient.execute(index);*/
    }

}
