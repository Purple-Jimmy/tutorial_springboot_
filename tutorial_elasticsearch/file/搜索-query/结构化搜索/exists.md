### exists query
检索字段中至少有一个非空值的文档.

> Elasticsearch中以下情况当做null处理
1. null
2. []
3. [null]

* 以下都可以检索到文档,因为title字段都有值
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "exists":{
          "field":"title"
       }
  }
}
'
```


* 以下情况检索不到数据,因为没有totalPrice字段
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "exists":{
          "field":"totalPrice"
       }
  }
}
'
```
结果:
```
"hits" : {
    "total" : 0,
    "max_score" : null,
    "hits" : [ ]
  }
```