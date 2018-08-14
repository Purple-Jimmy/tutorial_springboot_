### term query 单个精确值搜索

* keyword 搜索title="Elasticsearch Tutorial"的文档,需指定"title.keyword"即不使用分词             
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "term":{
           "title.keyword":"Elasticsearch Tutorial"
       }
  },
  "_source":["title"]
}
'
```
结果:
```
"hits" : {
    "total" : 1,
    "max_score" : 0.6931472,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 0.6931472,
        "_source" : {
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```
#### dynamic mapping把title定义了两种类型:text和keyword.text用于全文检索,keyword用于精准检索.

* text 搜索title="Elasticsearch Tutorial"的文档,检索不到数据 
结果:
```
"hits" : {
    "total" : 0,
    "max_score" : null,
    "hits" : [ ]
}
``` 
原因:默认的standard分词器会分词成elasticsearch,tutorial.
```
{
  "tokens" : [
    {
      "token" : "elasticsearch",
      "start_offset" : 0,
      "end_offset" : 13,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "tutorial",
      "start_offset" : 14,
      "end_offset" : 22,
      "type" : "<ALPHANUM>",
      "position" : 1
    }
  ]
}
``` 

