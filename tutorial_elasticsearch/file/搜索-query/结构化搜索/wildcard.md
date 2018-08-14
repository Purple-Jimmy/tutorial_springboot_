### wildcard query 通配符检索  性能低
支持单字通配符和多字通配符
1. ?:匹配一个任意字符
2. *:匹配零或多个字符

* 查询title中包含"随云"的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "wildcard":{
          "title.keyword":"*随云*"
       }
  },
  "_source":["id","title"]
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
        "_id" : "5",
        "_score" : 1.0,
        "_source" : {
          "id" : 5,
          "title" : "心随云动"
        }
      }
    ]
  }
```
