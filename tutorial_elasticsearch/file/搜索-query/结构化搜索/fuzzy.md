### fuzzy query 模糊检索 性能低
模糊检索通过计算词项与文档的编辑距离检索数据.

* 搜索title="2018冬雪"但写错成了"2017冬雪"
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "fuzzy":{
          "title.keyword":"2017冬雪"
       }
  }
}
'
```
结果:
```
"hits" : {
    "total" : 1,
    "max_score" : 0.81735766,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "4",
        "_score" : 0.81735766,
        "_source" : {
          "id" : 4,
          "title" : "2018冬雪",
          "author" : [
            "Jimmy"
          ],
          "tag" : "life",
          "zone" : {
            "country" : "中国",
            "city" : ""
          },
          "weekClick" : 10,
          "monthClick" : 50,
          "desc" : "2018年的初雪,冷",
          "releaseDate" : "2018-01-25",
          "tagNum" : 1,
          "visible" : true
        }
      }
    ]
  }
```

#### 一般如下使用
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
     "match":{
         "title.keyword":{
            "query":"2017冬雪",
            "fuzziness":"AUTO",
            "operator":"and"
         }
     }
  }
}
'
```
