### boost 权重控制
默认情况下搜索条件的权重都是1.boost可以将某个搜索条件的权重加大,计算relevance score时匹配权重更大的搜索条件的document,relevance score会更高

* 检索title包含Java或In或Tutorial的文档,默认情况下
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "_source":["id","title"],
   "query": {
       "bool":{
          "should":[
              {"match":{
                   "title":{
                      "query":"Java"
                   }
                 }
              },
              {"match":{
                   "title":{
                      "query":"In"
                   }
                 }
              },
              {"match":{
                   "title":{
                      "query":"Tutorial"
                    }
                  }
              }
          ]
       }
   }
}
'
```
结果:
```
"hits" : {
    "total" : 4,
    "max_score" : 1.2814485,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.2814485,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 1.0226655,
        "_source" : {
          "id" : 2,
          "title" : "Spring In Action"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 0.7549128,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```
* Tutorial权重增加
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "_source":["id","title"],
   "query": {
       "bool":{
          "should":[
              {"match":{
                   "title":{
                      "query":"Java"
                   }
                 }
              },
              {"match":{
                   "title":{
                      "query":"In"
                   }
                 }
              },
              {"match":{
                   "title":{
                      "query":"Tutorial",
                      "boost":10
                    }
                  }
              }
          ]
       }
   }
}
'
```
结果:匹配Tutorial的文档优先返回
```
  "hits" : {
    "total" : 4,
    "max_score" : 8.71385,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 8.71385,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 7.5491276,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.2814485,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 1.0226655,
        "_source" : {
          "id" : 2,
          "title" : "Spring In Action"
        }
      }
    ]
  }
```
