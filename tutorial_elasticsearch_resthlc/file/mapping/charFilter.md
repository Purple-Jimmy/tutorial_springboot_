# 需求:把一个字符转换为另一个字符,如'& => and'        
```
curl -X PUT "localhost:9200/char_filter_index" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "analysis": {
      "analyzer": {
        "my_analyzer": {
          "tokenizer": "keyword",
          "char_filter": [
            "my_char_filter"
          ]
        }
      },
      "char_filter": {
        "my_char_filter": {
          "type": "mapping",
          "mappings": [
            "٠ => 0",
            "١ => 1",
            "٢ => 2",
            "٣ => 3",
            "٤ => 4",
            "٥ => 5",
            "٦ => 6",
            "٧ => 7",
            "٨ => 8",
            "٩ => 9"
          ]
        }
      }
    }
  }
}
'
curl -X POST "localhost:9200/char_filter_index/_analyze" -H 'Content-Type: application/json' -d'
{
  "analyzer": "my_analyzer",
  "text": "My license plate is ٢٥٠١٥"
}
'

结果:
My license plate is 25015

```

## 实战
1. mapping
```
curl -X PUT "localhost:9200/char_filter_index" -H 'Content-Type: application/json' -d'
{
	"settings": {
		"analysis": {
			"analyzer": {
				"char_filter_analyzer": {
					"tokenizer": "standard",
					"char_filter": [
						"my_char_filter"
					]
				}
			},
			"char_filter": {
				"my_char_filter": {
					"type": "mapping",
					"mappings": [
						"٠ => 0",
						"١ => 1",
						"٢ => 2",
						"٣ => 3",
						"٤ => 4",
						"٥ => 5",
						"٦ => 6",
						"٧ => 7",
						"٨ => 8",
						"٩ => 9",
						"Ⅱ => 2"
					]
				}
			}
		}
	},
	"mappings": {
		"tutorial_mapping": {
			"properties": {
				"id": {
					"type": "keyword"
				},
				"name": {
					"type": "text",
					"analyzer": "char_filter_analyzer",
					"fields": {
						"keyword": {
							"type": "keyword"
						}
					}
				}
			}
		}
	}
}
'
```
2. data
```
curl -X PUT "localhost:9200/char_filter_index/tutorial_mapping/1?pretty" -H 'Content-Type: application/json' -d'
{
  "id": 1,
  "name": "风云1"
}'

curl -X PUT "localhost:9200/char_filter_index/tutorial_mapping/2?pretty" -H 'Content-Type: application/json' -d'
{
  "id": 2,
  "name": "风云Ⅱ"
}'


curl -X GET 'localhost:9200/char_filter_index/tutorial_mapping/_search?pretty'
```
3. 验证
```
curl -X POST "localhost:9200/char_filter_index/_analyze?pretty" -H 'Content-Type: application/json' -d'
{
  "analyzer": "char_filter_analyzer",
  "text": "My license plate is ٢٥٠١٥  Ⅱ"
}
'
```

4. 查询
```
curl -X GET "localhost:9200/char_filter_index/tutorial_mapping/_search?pretty" -H 'Content-Type: application/json' -d'
{
	"query": {
		"match_phrase": {
			"name": "风云2"
		}
	},
	"highlight": {
		"fields": {
			"name": {}
		}
	}
}
'
结果:
"highlight" : {
  "name" : [
    "<em>风</em><em>云</em><em>Ⅱ</em>"
  ]
}





curl -X GET "localhost:9200/char_filter_index/tutorial_mapping/_search?pretty" -H 'Content-Type: application/json' -d'
{
	"query": {
		"term": {
			"name.keyword": "风云2"
		}
	},
	"highlight": {
		"fields": {
			"name": {}
		}
	}
}
'
结果:
"hits" : [ ]


curl -X GET "localhost:9200/char_filter_index/tutorial_mapping/_search?pretty" -H 'Content-Type: application/json' -d'
{
	"query": {
		"term": {
			"name": "2"
		}
	},
	"highlight": {
		"fields": {
			"name": {}
		}
	}
}
'
结果:
"highlight" : {
  "name" : [
    "风云<em>Ⅱ</em>"
  ]
}


```
