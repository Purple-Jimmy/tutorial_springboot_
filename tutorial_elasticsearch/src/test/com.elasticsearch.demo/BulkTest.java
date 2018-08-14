package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import com.elasticsearch.domain.Movie;
import com.elasticsearch.domain.Zone;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Jimmy. 2018/1/29  21:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class BulkTest {
    @Autowired
    private JestClient jestClient;

    @Test
    public void saveObject() throws IOException {
        String indexName = "twitter";
        String type = "tweet";
        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(type);
        /*for (Object obj : objs) {
            Index index = new Index.Builder(obj).build();
            bulk.addAction(index);
        }*/
        for(Long i=2L;i<100L;i++){
            Movie movie = new Movie();
            movie.setId(i);
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
            //
            Index index = new Index.Builder(movie).build();
            bulk.addAction(index);
        }
            BulkResult bulkResult = jestClient.execute(bulk.build());
            System.out.println(bulkResult.isSucceeded());
    }
}
