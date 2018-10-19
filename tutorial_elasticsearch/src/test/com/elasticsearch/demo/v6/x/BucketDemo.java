package com.elasticsearch.demo.v6.x;

/**
 * @Author: jimmy
 * @Date: 2018/10/19
 */
public class BucketDemo {
// size(Integer.MAX_VALUE) 默认返回10条
    //计算bucket
   /* SearchSourceBuilder aggBuilder = new SearchSourceBuilder();
    AggregationBuilder aggregationBuilder = AggregationBuilders.terms("actionType_terms_agg").field("actionType").size(Integer.MAX_VALUE);
        aggBuilder.aggregation(aggregationBuilder);
    String queryAgg = aggBuilder.toString();
        log.info("Method:queryItemContentES queryAgg {}",queryAgg);
    Search searchAgg = new Search.Builder(queryAgg).addIndex(Constants.ITEM_CONTENT_INDEX).build();
        try {
        SearchResult resultAgg = jestClient.execute(searchAgg);
        if(resultAgg != null){
            TermsAggregation agg = resultAgg.getAggregations().getTermsAggregation("actionType_terms_agg");
            List<TermsAggregation.Entry> list = agg.getBuckets();
            Long sum = 0L;
            for(TermsAggregation.Entry entry:list){
                pageData.getAggMap().put(entry.getKey(),entry.getCount());
                sum += entry.getCount();
            }
            pageData.getAggMap().put("all",sum);
        }
    } catch (IOException e) {
        log.error("Method:queryItemContentES queryAgg error.",e);
    }*/
//-------------------------------------------------------------------------------
   /* SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    //查询
    QueryBuilder queryBuilder = QueryBuilders.boolQuery()
            .must(QueryBuilders.termQuery("programType", "电影"));
        searchSourceBuilder.query(queryBuilder);

    //聚合
    AggregationBuilder aggregationBuilder = AggregationBuilders.terms("actionType_terms_agg").field("programType").size(Integer.MAX_VALUE);
        searchSourceBuilder.aggregation(aggregationBuilder);
    //
    String query = searchSourceBuilder.toString();

    // Search search = new Search.Builder(query).addIndex("tutorial_index_v1").addType("movie").build();
    Search search = new Search.Builder(query).addIndex(Constant.PROGRAM_SERIES_INDEX).build();
    //
    SearchResult result = jestClient.execute(search);
        System.out.println(result.getSourceAsString());

    TermsAggregation agg = result.getAggregations().getTermsAggregation("actionType_terms_agg");
    List<TermsAggregation.Entry> list = agg.getBuckets();
        for(TermsAggregation.Entry entry:list){
        System.out.println(entry);
        System.out.println("key="+entry.getKeyAsString());
        System.out.println("num="+entry.getCount());
    }*/
}
