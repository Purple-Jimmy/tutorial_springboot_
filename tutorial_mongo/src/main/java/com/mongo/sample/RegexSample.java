package com.mongo.sample;

/**
 * @author jimmy
 * @date 2018/11/1522:12
 */
public class RegexSample {

   /* List<String> idList = Lists.newArrayList();
    Criteria criteriaTotal = null;
        for(int i =0;i<idList.size();i++){
        Criteria cr =  new Criteria("epgGroupId").regex(".*?" + idList.get(i) + ".*");
        if(criteria== null){
            criteriaTotal = cr;
        }else{
            criteriaTotal.orOperator(cr);
        }
    }*/


    /*Query query = new Query();
    //过滤掉没有挂栏目且自动入栏的节目集
        query.addCriteria(Criteria.where("categoryId").ne(null).and("isTvsouAuto").ne(1));


    BasicDBObject obj= BasicDBObject.parse("{categoryId: {$ne:null}, isTvsouAuto: {$ne:1}}");

    DistinctIterable<Long> distinct = mongoTemplate.getCollection("epgGroupPs").distinct("programsetId",obj,Long.class);
    MongoCursor<Long> iterator = distinct.iterator();

    List<Long> myList = new ArrayList<>();
        while(iterator.hasNext()){
        myList.add(iterator.next());
    }
        System.out.println(myList.size());


    Aggregation agg = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("categoryId").ne(null).and("isTvsouAuto").ne(1)),  //查询条件
            Aggregation.group("programsetId"),
            // Aggregation.sort(sort),
            Aggregation.skip(0),//跳到第几个开始
            Aggregation.limit(10)//查出多少个数据
    );

    AggregationResults<BasicDBObject> outputType=mongoTemplate.aggregate(agg,"epgGroupPs", BasicDBObject.class);
        System.out.println(outputType);
*/


}
