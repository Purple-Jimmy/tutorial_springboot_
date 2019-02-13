### 数据准备

## 数据结构
id,bookName,author,price,releaseDate,zone,pictures

## 索引
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

## 数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_book/book/1?pretty' -d ' 
{
  "id": "1",
  "bookName": "Java并发编程之美",
  "author": ["jimmy"],
  "price": 34.5,
  "releaseDate": "2001-6-8",
  "zone": {"country":"中国","city":"江苏"},
  "pictures": [{"id":"p1001","pictureName":"Java并发编程之美图1","desc":""}]
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_book/book/2?pretty' -d ' 
{
  "id": "2",
  "bookName": "深入浅出Elasticsearch",
  "author": ["jimmy","billy"],
  "price": 80.0,
  "releaseDate": "2011-10-12",
  "zone": {"country":"中国","city":"江苏"},
  "pictures": [{"id":"p1002","pictureName":"深入浅出Elasticsearch图2","desc":"深入浅出Elasticsearch图片"}]
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_book/book/3?pretty' -d ' 
{
  "id": "3",
  "bookName": "java编程思想",
  "author": ["史密斯"],
  "price": 100.5,
  "releaseDate": "1997-3-17",
  "zone": {"country":"美国","city":"纽约"},
  "pictures": [{"id":"p3001","pictureName":"java编程思想插图","desc":"java内容讲解"}]
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_book/book/4?pretty' -d ' 
{
  "id": "4",
  "bookName": "elasticsearch权威指南(上)",
  "author": ["jack"],
  "price": 76.8,
  "releaseDate": "2015-6-8",
  "zone": {"country":"中国","city":"上海"},
  "pictures": [{"id":"p7001","pictureName":"elasticsearch权威指南插图","desc":""}]
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial_book/book/5?pretty' -d ' 
{
  "id": "5",
  "bookName": "elasticsearch权威指南(下)",
  "author": ["jack"],
  "price": 90.8,
  "releaseDate": "2016-6-8",
  "zone": {"country":"中国","city":"北京"},
  "pictures": [{"id":"p8001","pictureName":"elasticsearch权威指南插图(下)","desc":""}]
}'
```