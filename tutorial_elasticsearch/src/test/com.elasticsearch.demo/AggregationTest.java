package com.elasticsearch.demo;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/** 聚合查询 https://blog.csdn.net/carlislelee/article/details/52598022
 * https://blog.csdn.net/baidu_39150148/article/details/81003536
 * @Author: jimmy
 * @Date: 2018/8/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class AggregationTest {
    @Autowired
    private JestClient jestClient;

    @Test
    public void aggTest() throws IOException {
        String query = "{\n" +
                "  \"size\":0,\n" +
                "  \"aggs\":{\n" +
                "     \"country_terms_aggs\":{\n" +
                "         \"terms\":{\n" +
                "             \"field\":\"actionType.keyword\"\n" +
                "         }\n" +
                "     }\n" +
                "  }\n" +
                "}";

        Search search = new Search.Builder(query)
                .addIndex("index")
                .addType("type")
                .build();

        SearchResult result = jestClient.execute(search);

        TermsAggregation agg = result.getAggregations().getTermsAggregation("country_terms_aggs");
        List<TermsAggregation.Entry> list = agg.getBuckets();
        for(TermsAggregation.Entry entry:list){
            System.out.println( entry.getKey()+"---"+ entry.getCount());
        }

       /* SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.wildcardQuery("title.keyword", "*刘德华*"));

        searchSourceBuilder.query(queryBuilder).from(0).size(10);

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("country_terms_aggs").field("actionType.keyword").size(Integer.MAX_VALUE);
        searchSourceBuilder.aggregation(aggregationBuilder);
        String query1 = searchSourceBuilder.toString();
        Search search = new Search.Builder(query1)
                .addIndex(Constants.ITEM_CONTENT_INDEX)
                .addType(Constants.ITEM_CONTENT_TYPE)
                .build();

        SearchResult result = jestClient.execute(search);

        TermsAggregation agg = result.getAggregations().getTermsAggregation("country_terms_aggs");
        List<TermsAggregation.Entry> list = agg.getBuckets();
        for(TermsAggregation.Entry entry:list){
            System.out.println( entry.getKey()+"---"+ entry.getCount());
        }*/
    }
}
