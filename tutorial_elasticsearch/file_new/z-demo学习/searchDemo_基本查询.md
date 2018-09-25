mapping 
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial_book" -d ' 
{
  "mappings":{
	 "book":{
	     "properties":{
			"id":{
				"type":"keyword"
			},
			"bookName":{
				"type":"text",
				"fields":{
					"keyword":{
						"type":"keyword"
					}
				}
			},
			"author":{
				"type":"text",
				"fields":{
					"keyword":{
						"type":"keyword"
					}
				}
			},
			"price":{
				"type":"float"
			},
			"releaseDate":{
				"type":"date",
				"format":"yyyy-MM-dd"
			},
			"zone":{
				"properties":{
					"country":{
						"type":"text",
						"fields":{
							"keyword":{
								"type":"keyword"
							}
						}
					},
					"city":{
						"type":"text",
						"fields":{
							"keyword":{
								"type":"keyword"
							}
						}
					}
				}
			},
			"pictures":{
				"properties":{
					"id":{
						"type":"keyword"
					},
					"pictureName":{
						"type":"text",
						"fields":{
							"keyword":{
								"type":"keyword"
							}
						}
					},
					"desc":{
						"type":"text",
						"fields":{
							"keyword":{
								"type":"keyword"
							}
						}
					}
				}
			}
	     }
	 }
  }
}'
```
分页查询
```
curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "from": 0,
  "size": 3,
  "query": {
    "match_all": {}
  }
}'
```
返回指定字段
```
curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "from": 0,
  "size": 3,
  "query": {
    "match_all": {}
  },
  "_source": [
    "id",
    "bookName"
  ]
}'
```
显示version
```
curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "from": 0,
  "size": 3,
  "version": true,
  "query": {
    "term": {
      "bookName": "java"
    }
  }
}'
```
评分过滤:查询>=min_score值的数据
```
curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "min_score": 0.5,
  "query": {
    "term": {
      "bookName": "java"
    }
  }
}'
```
高亮
```
curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "term": {
      "bookName": "java"
    }
  },
  "highlight": {
    "fields": {
      "bookName": {}
    }
  }
}'
```

curl -XGET "http://localhost:9200/tutorial_book/book/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "term": {
      "bookName": "编"
    }
  }
}'

curl  -H "Content-Type:application/json" -XGET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "standard",
  "text":     "java编程思想"
}
'