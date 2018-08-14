### range query

检索匹配在某一范围内的数值、日期、字符串文档.**range查询只能用于一个字段,不能作用于多个字段**

1. gt:大于
2. gte:大于等于
3. lt:小于
4. lte:小于等于

* 检索monthClick在50——100之间的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "range":{
           "monthClick":{
               "gt":50,
               "lt":100
           }
       }
  },
  "_source":["id","title","monthClick"]
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
        "_id" : "5",
        "_score" : 1.0,
        "_source" : {
          "monthClick" : 60,
          "id" : 5,
          "title" : "心随云动"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "monthClick" : 60,
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
          "monthClick" : 65,
          "id" : 2,
          "title" : "Spring In Action"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "6",
        "_score" : 1.0,
        "_source" : {
          "monthClick" : 70,
          "id" : 6,
          "title" : "白云流水"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "monthClick" : 60,
          "id" : 1,
          "title" : "Thinking In Java"
        }
      }
    ]
  }
```
* 检索releaseDate在1990-01-01——2015-12-31之间的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "range":{
           "releaseDate":{
               "gte":"1990-01-01",
               "lte":"2015-12-31",
               "format":"yyyy-MM-dd"
           }
       }
  },
  "_source":["id","releaseDate"]
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
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2013-06-01",
          "id" : 2
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2007-06-01",
          "id" : 1
        }
      }
    ]
  }
```
* 检索最近30天的文档 "gt": "now-30d",最近一月-1m,最近一年-1y
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "range":{
           "releaseDate":{
               "gt" : "now-30d"
           }
       }
  },
  "_source":["id","releaseDate"]
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
        "_id" : "10",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2018-02-01",
          "id" : 10
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "4",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2018-01-25",
          "id" : 4
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "11",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2018-02-01",
          "id" : 11
        }
      }
    ]
  }
```
