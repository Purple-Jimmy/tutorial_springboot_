### prefix query 前缀匹配  性能低
检索某个字段中以给到前缀开始的文档.

* 检索title中以"Spring"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "title.keyword":"Spring"
       }
  },
  "_source":["id","title"]
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
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "id" : 8,
          "title" : "Springboot教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "Spring In Action"
        }
      }
    ]
  }
```

* 检索title中以"Tutorial"为前缀的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "prefix":{
          "title.keyword":"Tutorial"
       }
  },
  "_source":["id","title"]
}
'
```
结果:无数据

