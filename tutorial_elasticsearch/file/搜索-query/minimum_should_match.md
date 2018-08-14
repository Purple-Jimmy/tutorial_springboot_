### minimum_should_match 去长尾
* 长尾:例如搜索5个关键词,实际很多结果只匹配了1个关键词,这些结果就是长尾.
* 作用:控制搜索结果的精准度:指定检索关键字中必须至少匹配其中的多少个关键字才能作为结果返回.

* 检索tag含有java的文档
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "query": { 
       "match": { 
           "tag":{
               "query":"java"
           }
       }
   },
   "_source":["tag"]
}
'
```
结果:
```
"hits" : {
    "total" : 5,
    "max_score" : 0.8142733,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 0.8142733,
        "_source" : {
          "tag" : "java spring"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 0.6931472,
        "_source" : {
          "tag" : "java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "8",
        "_score" : 0.6099695,
        "_source" : {
          "tag" : "java spring"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 0.6099695,
        "_source" : {
          "tag" : "java spring"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "11",
        "_score" : 0.5754429,
        "_source" : {
          "tag" : "life java elasticsearch"
        }
      }
    ]
  }
```

* 检索tag中life java spring javascript elasticsearch至少包含3个tag的文档
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": { 
         "match": { 
             "tag":{
                 "query":"life java spring javascript elasticsearch",
                 "minimum_should_match":"60%"
             }
         }
     },
     "_source":["tag"]
}
'
```
结果:
```
"hits" : {
    "total" : 1,
    "max_score" : 1.7263287,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "11",
        "_score" : 1.7263287,
        "_source" : {
          "tag" : "life java elasticsearch"
        }
      }
    ]
  }
```
