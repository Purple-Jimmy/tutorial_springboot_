### type query
检索具有指定类型的文档.

* 检索type=blog的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
       "type":{
           "value":"blog"
       }
  }
}
'
```
结果:默认返回10条数据

* 检索type=book的数据
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/book/_search?pretty' -d '
{
  "query": {
       "type":{
           "value":"book"
       }
  }
}
'
```
结果:没有type=book的数据