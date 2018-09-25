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
}'
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
}'
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
}'

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
}'

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
}'

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
}'

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
只有一条结果,第一条索引成功,第二条不索引
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
ignore_malformed
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/tutorial" -d ' 
{
  "mappings":{
	 "tutorial_mapping":{
	     "properties":{
			"id":{
				"type":"integer",
          		"ignore_malformed":true
			}
	     }
	 }
  }
}'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/tutorial/tutorial_mapping?pretty' -d ' 
{
   "id":"babsnd"
}'
可以索引成功,但是不能查询
curl -H "Content-Type:application/json" -X GET 'localhost:9200/tutorial/tutorial_mapping/_search?pretty' -d '
{
  "query": { 
        "match": {
        	"id":"babsnd"
        } 
   }
}'
{
  "error" : {
    "root_cause" : [
      {
        "type" : "query_shard_exception",
        "reason" : "failed to create query: {\n  \"match\" : {\n    \"id\" : {\n      \"query\" : \"babsnd\",\n      \"operator\" : \"OR\",\n      \"prefix_length\" : 0,\n      \"max_expansions\" : 50,\n      \"fuzzy_transpositions\" : true,\n      \"lenient\" : false,\n      \"zero_terms_query\" : \"NONE\",\n      \"auto_generate_synonyms_phrase_query\" : true,\n      \"boost\" : 1.0\n    }\n  }\n}",
        "index_uuid" : "b7AuPar2TOm2SnFlK-L8SQ",
        "index" : "tutorial"
      }
    ],
    "type" : "search_phase_execution_exception",
    "reason" : "all shards failed",
    "phase" : "query",
    "grouped" : true,
    "failed_shards" : [
      {
        "shard" : 0,
        "index" : "tutorial",
        "node" : "K6rUYPngQySfifTgqJXMWQ",
        "reason" : {
          "type" : "query_shard_exception",
          "reason" : "failed to create query: {\n  \"match\" : {\n    \"id\" : {\n      \"query\" : \"babsnd\",\n      \"operator\" : \"OR\",\n      \"prefix_length\" : 0,\n      \"max_expansions\" : 50,\n      \"fuzzy_transpositions\" : true,\n      \"lenient\" : false,\n      \"zero_terms_query\" : \"NONE\",\n      \"auto_generate_synonyms_phrase_query\" : true,\n      \"boost\" : 1.0\n    }\n  }\n}",
          "index_uuid" : "b7AuPar2TOm2SnFlK-L8SQ",
          "index" : "tutorial",
          "caused_by" : {
            "type" : "number_format_exception",
            "reason" : "For input string: \"babsnd\""
          }
        }
      }
    ]
  },
  "status" : 400
}
```