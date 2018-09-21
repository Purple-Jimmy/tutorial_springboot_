mapping模板
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"id":{
				"type":"keyword"
			}
	     }
	 }
  }
}
'
```

doc_values
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"id":{
				"type":"keyword"
			},
			"name":{
				"type":"keyword",
				"doc_values": false
			}
	     }
	 }
  }
}
'
```
dynamic
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	 	 "dynamic":"strict",
	     "properties":{
			"id":{
				"type":"keyword"
			}
	     }
	 }
  }
}
'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "id": "1"
}'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "id": "1",
   "name":"jimmy"
}'

{
  "error" : {
    "root_cause" : [
      {
        "type" : "strict_dynamic_mapping_exception",
        "reason" : "mapping set to strict, dynamic introduction of [name] within [tutorial_mapping] is not allowed"
      }
    ],
    "type" : "strict_dynamic_mapping_exception",
    "reason" : "mapping set to strict, dynamic introduction of [name] within [tutorial_mapping] is not allowed"
  },
  "status" : 400
}
```
enabled
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"name":{
				"enabled":false
			}
	     }
	 }
  }
}
'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "id": "1",
   "name":"jimmy"
}'

curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/tutorial_mapping/_search?pretty' -d '
{
  "query": { 
        "match": { 
        	"name": "jimmy" 
        } 
   }
}'
```

format
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"releaseDate":{
				"type":"date",
          		"format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis" 
			}
	     }
	 }
  }
}
'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "releaseDate":"2018-9-21 18:56:00"
}'
```

ignoreAbove
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"desc":{
				"type":"keyword",
          		"ignore_above":20
			}
	     }
	 }
  }
}
'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "desc":"Syntax error"
}'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "desc":"Syntax error with some long stacktrace"
}'

curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/tutorial_mapping/_search?pretty' -d '
{
  "query": { 
        "match": { 
       	 	"desc": "Syntax error"
        } 
   }
}'
只有一条结果
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/tutorial_mapping/_search?pretty' -d '
{
  "query": { 
        "match": { 
       	 	"desc": "Syntax error with"
        } 
   }
}'
没有结果
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/tutorial_mapping/_search?pretty' -d '
{
  "query": { 
        "match_all": {
        } 
   }
}'
查询到2条
```