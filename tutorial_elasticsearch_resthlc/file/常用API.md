# index 索引
* 查看全部索引
```
curl 'localhost:9200/_cat/indices?v'
```
* 删除指定索引
```
curl -X DELETE 'localhost:9200/char_filter_index?pretty'
```

# document 文档
* 查询指定文档
```
curl -X GET 'localhost:9200/customer/user/1?pretty'
```

* 查询文档列表(默认查询返回10条数据)
```
curl -X GET 'localhost:9200/customer/user/_search?pretty'
```