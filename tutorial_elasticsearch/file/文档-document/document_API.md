### Document API

> 创建文档 PUT
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/customer/user/1?pretty' -d ' 
{
   "name": "jimmy"
}'
```
结果:
```
{
  "_index" : "customer",
  "_type" : "user",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
```
> 创建文档 POST 
```
curl -H "Content-Type:application/json" -X POST 'localhost:9200/customer/user?pretty' -d ' 
{
   "name": "jack"
}'
```  
结果:
```
{
  "_index" : "customer",
  "_type" : "user",
  "_id" : "STxIKGEBc2wHNjbjXbhw",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
```
*注:不指定id的时候使用POST,elasticsearch会自动生成一个ID*  
#### 说明:_shards分片

> 查询文档
```
curl -X GET 'localhost:9200/customer/user/1?pretty'
```

> 查询文档列表(默认查询返回10条数据)
```
curl -X GET 'localhost:9200/customer/user/_search?pretty'
``` 
结果:
```
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "customer",
        "_type" : "user",
        "_id" : "STxIKGEBc2wHNjbjXbhw",
        "_score" : 1.0,
        "_source" : {
          "name" : "jack"
        }
      },
      {
        "_index" : "customer",
        "_type" : "user",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "name" : "jimmy"
        }
      }
    ]
  }
}
```
#### 说明:
* took:查询花费的时间,毫秒单位
* timed_out:标识查询是否超时
* _shards:描述了查询分片的信息,查询了多少个分片,成功的分片数量,失败的分片数量
* hits:搜索的结果,total是全部的满足的文档数目,hits是返回的实际数目(默认是10)
* _score:文档的分数信息,与排名相关度有关

> 替换文档 PUT
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/customer/user/1?pretty' -d ' 
{
   "name": "billy"
}'
```
结果:_version变为2,result为updated
```
{
  "_index" : "customer",
  "_type" : "user",
  "_id" : "1",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}
```
#### 缺点:必须带上全部的field.如果某一个field没有带上,则此field的值会更新为null.也可以新增一个field

> 更新文档 POST _update doc
```
curl -H "Content-Type:application/json" -X POST 'localhost:9200/customer/user/1/_update?pretty' -d ' 
{
   "doc": {
       "name": "J",
       "age":18
     }
}'
```
结果:_version变为3,result为updated
```
{
  "_index" : "customer",
  "_type" : "user",
  "_id" : "1",
  "_version" : 3,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 2,
  "_primary_term" : 1
}
```
#### 说明:首先找到文档,删除旧的文档内容,然后执行更新再索引新的文档.也可以新增一个field

> 删除文档 
```
curl -H "Content-Type:application/json" -X DELETE 'http://localhost:9200/es/blog/121?pretty'
```

> 查询删除:根据查询条件检索出所有需要删除的数据 _delete_by_query
```
curl -H "Content-Type:application/json" -X POST 'http://localhost:9200/customer/_delete_by_query?pretty' -d '
{
  "query":{
     "term":{
        "name":"jack"
     }
  }
}
'
```
> 删除type下的所有文档 
```
curl -H "Content-Type:application/json" -X POST 'http://localhost:9200/customer/user/_delete_by_query?pretty' -d '
{
  "query":{
     "match_all":{}
  }
}
'
```

> 查询集群中的所有文档数量 _count
```
curl -H "Content-Type:application/json" -X GET 'http://localhost:9200/_count?pretty' -d '
{
    "query": {
        "match_all": {}
    }
}
'
```