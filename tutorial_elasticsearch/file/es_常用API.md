### 常用API

> 索引列表 curl 'localhost:9200/_cat/indices?v'

> 创建索引 curl -X PUT 'localhost:9200/es?pretty'

> 删除索引 curl -X DELETE 'localhost:9200/ik_pinyin_tutorial?pretty'

> 查看映射 curl -X GET 'localhost:9200/es/_mapping/blog?pretty'

> ID查询  curl -X GET 'localhost:9200/es/blog/1?pretty'

> 查看分词
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "standard",
  "text":     "心随云动"
}
'
```
