### ids query
检索指定id的文档.参数type可选,可接受组数,未指定时会检索索引中所有type.

* 检索id为1,3,5的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "ids":{
          "type":["blog"],
          "values":[1,3,5]
       }
  },
  "_source":["id","title"]
}
'
```
结果:
```
"hits" : {
    "total" : 3,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "5",
        "_score" : 1.0,
        "_source" : {
          "id" : 5,
          "title" : "心随云动"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 1.0,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial"
        }
      }
    ]
  }
```