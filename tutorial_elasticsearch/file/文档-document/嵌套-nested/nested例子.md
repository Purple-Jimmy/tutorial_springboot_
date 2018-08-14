### company
> 数据结构设计

|Field      | Type    | Description
| :-: | :-: | :-: | 
id          | long    | id
title       | text    | 标题
comment     | text    | 评论
readerName  | text    | 读者名字
star        | int     | 读者评论给星

comment字段设置为nested
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial_nested" -d ' 
{
 "mappings": {
    "blog": {
       "properties" : {
          "id" : {
            "type" : "keyword"
          },
		  "title" : {
            "type" : "text",
			"fields" : {
                "keyword" : {
                    "type" : "keyword",
                    "ignore_above" : 256
                 }
            }
          },
          "comment" : {
		    "type":"nested",
            "properties" : {
              "readerName" : {
                "type" : "text",
                "fields" : {
                  "keyword" : {
                    "type" : "keyword",
                    "ignore_above" : 256
                  }
                }
              },
              "star" : {
                "type" : "integer"
              }
            }
          }
        }
    }
 }
}
'
```
查看mapping
```
curl -X GET 'localhost:9200/tutorial_nested/_mapping/blog?pretty'
```

测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_nested/blog/1?pretty' -d ' 
{
  "id": "1",
  "title": "Java多线程",
  "comment": [
     {"reader":"jimmy","star":10}
  ]
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_nested/blog/2?pretty' -d ' 
{
  "id": "2",
  "title": "Java集合",
  "comment": [
     {"reader":"jimmy","star":10},
     {"reader":"lucy","star":10},
     {"reader":"billy","star":5}
  ]
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_nested/blog/3?pretty' -d ' 
{
  "id": "3",
  "title": "Springboot学习",
  "comment": [
     {"reader":"zhangsan","star":7},
     {"reader":"jack","star":5}
  ]
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_nested/blog/4?pretty' -d ' 
{
  "id": "4",
  "title": "SpringCloud教程",
  "comment": [
     {"reader":"jimmy","star":10},
     {"reader":"lucy","star":5},
     {"reader":"billy","star":5},
     {"reader":"zhangsan","star":5},
     {"reader":"lisi","star":10}
  ]
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_nested/blog/5?pretty' -d ' 
{
  "id": "5",
  "title": "Java数组",
  "comment": [
     {"reader":"jimmy","star":10}
  ]
}'
```


#### 检索
由于嵌套对象被索引在独立隐藏的文档中,无法直接查询它们,必须使用 nested 查询.
* 检索reader为billy且star为5的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial_nested/blog/_search?pretty' -d '
{
  "query": {
    "nested":{
	  "path":"comment",
	  "query":{
	    "bool":{
	      "must":[
		    {"match":{"comment.reader":"billy"}},
		    {"match":{"comment.star":5}}
		  ]
	    }
	  }
	}
  }
}
'
```

* 检索reader为billy且star为5的文档,指定score策略
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial_nested/blog/_search?pretty' -d '
{
  "query": {
    "nested":{
	  "path":"comment",
	  "score_mode": "max", 
	  "query":{
	    "bool":{
	      "must":[
		    {"match":{"comment.reader":"billy"}},
		    {"match":{"comment.star":5}}
		  ]
	    }
	  }
	}
  }
}
'
```

* star desc 排序 https://www.elastic.co/guide/en/elasticsearch/reference/6.2/search-request-sort.html
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial_nested/blog/_search?pretty' -d '
{
  "query": {
    "nested":{
	  "path":"comment",
	  "score_mode": "max", 
	  "query":{
	    "match_all":{}
	  }
	}
  },
    "sort":{
    "comment.star":{
	  "order": "asc",
	  "mode":  "min",   
      "nested_path": "comment"
	}
  }
}
'
```


* nested 聚合:star 聚合
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial_nested/blog/_search?pretty' -d '
{
  "size":0,
  "aggs": {
    "comment_aggs":{
	  "nested":{  
        "path":"comment"
	  },
	  "aggs":{
	    "star_aggs":{
		   "terms":{
		      "field":"comment.star"
		   }
		}
	  }
	}
  }
}
'
```
结果:
```
{
  "aggregations" : {
    "comment_aggs" : {
      "doc_count" : 12,
      "star_aggs" : {
        "doc_count_error_upper_bound" : 0,
        "sum_other_doc_count" : 0,
        "buckets" : [
          {
            "key" : 10,
            "doc_count" : 6
          },
          {
            "key" : 5,
            "doc_count" : 5
          },
          {
            "key" : 7,
            "doc_count" : 1
          }
        ]
      }
    }
  }
}
```