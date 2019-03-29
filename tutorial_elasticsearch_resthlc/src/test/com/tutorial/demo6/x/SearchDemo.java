package com.tutorial.demo6.x;

import com.elasticsearch.ElasticsearchStart;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.profile.ProfileShardResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
       // SearchRequest searchRequest = new SearchRequest("ysten_tips").types("tips_mapping");
       // searchRequest.preference("_local");
       // searchRequest.routing("routing");
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().totalHits);
        System.out.println(searchResponse.getClusters().toString());
        RestStatus status = searchResponse.status();
        TimeValue took = searchResponse.getTook();
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        boolean timedOut = searchResponse.isTimedOut();
        System.out.println("status=="+status);
        System.out.println("took=="+took);
        System.out.println("terminatedEarly=="+terminatedEarly);
        System.out.println("timedOut=="+timedOut);
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
            // failures should be handled here
        }
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for(SearchHit hit : searchHits){
            System.out.println("index=="+hit.getIndex());
            System.out.println("id=="+hit.getId());
            System.out.println("score=="+hit.getScore());
            System.out.println(hit);
           // City city = hit.
            String sourceAsString = hit.getSourceAsString();
            System.out.println("sourceAsString="+sourceAsString);
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println("sourceAsMap="+sourceAsMap);
        }

        Map<String, ProfileShardResult> profilingResults = searchResponse.getProfileResults();
        for (Map.Entry<String, ProfileShardResult> profilingResult : profilingResults.entrySet()) {
            String key = profilingResult.getKey();
            ProfileShardResult profileShardResult = profilingResult.getValue();
            System.out.println("key="+key);
            System.out.println("profileShardResult="+profileShardResult);
        }
    }
}
