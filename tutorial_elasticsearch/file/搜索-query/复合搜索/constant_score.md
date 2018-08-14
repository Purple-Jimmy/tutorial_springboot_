### constant_score query
constant_score:查询以非评分模式来执行term查询并以1作为统一评分
* 检索weekClick=0的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
      "constant_score":{
          "filter":{
               "term":{
                   "weekClick":0
               }
          }
      }
  },
  "_source":["id","weekClick"]
}
'
```
结果:max_score和_score都是1.0
```
 "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "10",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 0,
          "id" : 10
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 0,
          "id" : 3
        }
      }
    ]
  }
```

* 检索weekClick=0的电影,指定`boost参数`
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
    "constant_score":{
        "filter":{
             "term":{
                 "weekClick":0
             }
        },
        "boost":1.2
    }
  },
  "_source":["id","weekClick"]
}
'
```
结果:max_score和_score都是1.2
```
"hits" : {
    "total" : 2,
    "max_score" : 1.2,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "10",
        "_score" : 1.2,
        "_source" : {
          "weekClick" : 0,
          "id" : 10
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 1.2,
        "_source" : {
          "weekClick" : 0,
          "id" : 3
        }
      }
    ]
  }
```
