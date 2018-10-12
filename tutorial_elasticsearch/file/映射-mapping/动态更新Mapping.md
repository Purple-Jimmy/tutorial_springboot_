## 不停服务动态更改索引

### 别名方式

1. 创建一个索引tutorial_index_v1,建议带上版本号
```
curl -X PUT 'localhost:9200/tutorial_index_v1?pretty'

curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial_index_v1" -d ' 
{
  "mappings":{
     "movie":{
        "properties":{
           "name":{
              "type":"text"
           }
        }
    }
  }
}'
```

2. 创建一个指向tutorial_index_v1索引的别名
```
curl -H 'Content-Type: application/json' -XPOST "localhost:9200/_aliases" -d ' 
{
  "actions": [{ 
     "add": {
        "alias": "tutorial_index",
        "index": "tutorial_index_v1"
     }
  }]
}'
```

3. 然后可以通过访问tutorial_index来访问tutorial_index_v1
```
删除索引
```

4. 创建一个新mapping结构的index,新建索引名tutorial_index_v2
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial_index_v2" -d ' 
{
  "mappings":{
     "movie":{
        "properties":{
           "name":{
              "type":"text",
              "analyzer": "ik_max_word",
              "fields":{
                "keyword":{
                  "type":"keyword"
                }
              }
           }
        }
    }
  }
}'
```

5. 从旧的index复制数据到新的index
```
curl -XGET "http://localhost:9200/_reindex" -H 'Content-Type: application/json' -d'
{
  "source": {
    "index": "tutorial_index_v1",
    "type":"movie"
  },
  "dest": {
    "index": "tutorial_index_v2",
    "op_type": "create"
  }
}'
```


6. 修改同义词
```
curl -H 'Content-Type: application/json' -XPOST "localhost:9200/_aliases" -d ' 
{
  "actions": [{ 
     "remove": {
        "alias": "tutorial_index",
        "index": "tutorial_index_v1"
     }  
  },
  { 
    "add": {
    "alias": "tutorial_index",
    "index": "tutorial_index_v2"
   }
  }]
}'
```


7. 实战 操作别名tutorial_index往tutorial_index_v2中插入数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_index/movie/1?pretty' -d ' 
{
  "name": "刘德华"
}'
```

### multi_field方式
```
curl -XPUT localhost:9200/my_index/my_type/_mapping -d '
{
    "my_type": {
        "properties": {
            "created": {
                "type":   "multi_field",
                "fields": {
                    "created": { "type": "string" },
                    "date":    { "type": "date"   }
                }
            }
        }
    }
}
```



