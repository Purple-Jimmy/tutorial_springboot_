package com.tutorial.demo6.x;

import com.elasticsearch.ElasticsearchStart;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author: jimmy
 * @Date: 2019/3/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
@Slf4j
public class SearchDemo {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public static final String INDEX_NAME   = "city_index";
    public static final String MAPPING_NAME = "tutorial_mapping";

    @Test
    public void test(){

    }

    /**
     * match_all + 分页
     */
    @Test
    public void matchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME).types(MAPPING_NAME);
        searchRequest.preference("_local");
       // searchRequest.routing("routing");
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().totalHits);
    }
}
