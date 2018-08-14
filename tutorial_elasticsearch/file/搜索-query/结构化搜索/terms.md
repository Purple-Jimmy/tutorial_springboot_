### terms query 多个精确值搜索

检索文档中包含多个词的文档

* 检索title="Elasticsearch Tutorial"或者"2018冬雪"的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "terms":{
           "title.keyword":["Elasticsearch Tutorial","2018冬雪"]
       }
  },
  "_source":["title"]
}
'
```
结果:
```
"hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "4",
        "_score" : 1.0,
        "_source" : {
          "title" : "2018冬雪"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```

* 检索tag含有java的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "terms":{
           "tag":["java"]
       }
  },
  "_source":["title","tag"]
}
'
```
结果:
```
"hits" : {
    "total" : 5,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "tag" : "java spring",
          "title" : "Springboot教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "tag" : "java spring",
          "title" : "爪哇学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "tag" : "java spring",
          "title" : "Spring In Action"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "tag" : "java",
          "title" : "Thinking In Java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "11",
        "_score" : 1.0,
        "_source" : {
          "tag" : "life java elasticsearch",
          "title" : "测试分页数据"
        }
      }
    ]
  }
```
* 检索结果优化:检索tag仅仅含有java的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
     "constant_score":{
        "filter":{
           "bool":{
              "must":[
                 {"terms":{"tag":["java"]}},
                 {"term":{"tagNum":1}}
              ]
           }
        }
     }
  },
  "_source":["title","tag"]
}
'
```
结果:
```
"hits" : {
    "total" : 1,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "tag" : "java",
          "title" : "Thinking In Java"
        }
      }
    ]
  }
```