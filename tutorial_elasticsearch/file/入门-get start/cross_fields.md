### cross_fields
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": { 
      "multi_match": {
          "query": "Java Tutorial",
          "type": "cross_fields", 
          "operator": "and",
          "fields": ["title", "desc"]
      }
  },
  "_source":["id","title","desc"]
}
'
```
结果:
```
"hits" : {
    "total" : 3,
    "max_score" : 2.970674,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "12",
        "_score" : 2.970674,
        "_source" : {
          "id" : 12,
          "title" : "Java Language Is The Best",
          "desc" : "I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "13",
        "_score" : 1.6799116,
        "_source" : {
          "id" : 13,
          "title" : "Spark Language Is All So Best",
          "desc" : "But I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "14",
        "_score" : 0.5753642,
        "_source" : {
          "id" : 14,
          "title" : "Java Started",
          "desc" : "This Tutorial is good."
        }
      }
    ]
  }
```
解决most_field三大弊端:
1. and要求每个term都必须在任何一个field中出现.要求Java必须在title或desc中出现,要求Tutorial必须在title或desc中出现.
2. 既然每个term都要求出现,长尾肯定被去除掉了.java hadoop spark --> 这3个term都必须在任何一个field出现了,如果document只有一个field中包含一个java,那就被干掉了,作为长尾就没了.
3. 计算IDF的时候,将每个query在每个field中的IDF都取出来,取最小值,就不会出现极端情况下的极大值了.
