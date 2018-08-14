### 设置mapping
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ysten_phrase" -d ' 
{
  "settings":{
       "analysis":{
          "filter":{
             "custom_pinyin":{
                 "type": "pinyin",
                 "keep_joined_full_pinyin": true,
				 "limit_first_letter_length" : 50
             }
          },
          "analyzer":{
             "ik_pinyin_analyzer":{
                "type":"custom",
                "tokenizer":"ik_max_word",
                "filter":["custom_pinyin"]
             }
          }
       }
    },
    "mappings":{
       "programPhrase":{
          "properties":{
              "id":{
                 "type" : "keyword"
              },
              "name":{
                 "type" : "text",
                 "analyzer": "ik_max_word",
                 "fields":{
                    "keyword":{
                       "type": "keyword"
                    },
                    "pinyin":{
                       "type": "text",
                       "store": "true",
                       "term_vector": "with_positions_offsets",
                       "analyzer": "ik_pinyin_analyzer"
                    }
                 }
              },
              "repeatCount":{
                 "type" : "integer"
              },
              "weight":{
                 "type" : "integer"
              },
              "weekClickRate":{
                 "type" : "integer"
              },
              "monthClickRate":{
                 "type" : "integer"
              },
              "phraseType":{
                "type" : "keyword"
             }
          }
       }
    }
 }
'
```
### 测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/1?pretty' -d ' 
{
  "id": 1,
  "name": "熊出没",
  "repeatCount": 20,
  "weight": 5,
  "weekClickRate": 20,
  "monthClickRate": 70,
  "phraseType":"film"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/2?pretty' -d ' 
{
  "id": 2,
  "name": "熊出没之过年",
  "repeatCount": 20,
  "weight": 5,
  "weekClickRate": 15,
  "monthClickRate": 70,
  "phraseType":"film"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/3?pretty' -d ' 
{
  "id": 3,
  "name": "有熊出没",
  "repeatCount": 10,
  "weight": 1,
  "weekClickRate": 10,
  "monthClickRate": 10,
  "phraseType":"film"
}'


curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/4?pretty' -d ' 
{
  "id": 4,
  "name": "穿长筒靴的猫",
  "repeatCount": 30,
  "weight": 30,
  "weekClickRate": 10,
  "monthClickRate": 50,
  "phraseType":"film"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/5?pretty' -d ' 
{
  "id": 5,
  "name": "刘德华",
  "repeatCount": 50,
  "weight": 30,
  "weekClickRate": 100,
  "monthClickRate": 500,
  "phraseType":"star"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ysten_phrase/programPhrase/6?pretty' -d ' 
{
  "id": 6,
  "name": "今天-刘德华",
  "repeatCount": 45,
  "weight": 18,
  "weekClickRate": 100,
  "monthClickRate": 500,
  "phraseType":"star"
}'

```
### 查看分词
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":     "刘德华"
}
'
```

### 删除索引
```
curl -X DELETE 'localhost:9200/ysten_phrase?pretty'
```

### 删除文档
```
curl -H "Content-Type:application/json" -X POST 'http://localhost:9200/ysten_program_series/programSeries/_delete_by_query?pretty' -d '
{
  "query":{
     "match_all":{}
  }
}
'
```