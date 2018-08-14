### IK实战
输入拼音"liudehua"查询到刘德华
1. 创建索引并自定义分词器 curl -X GET 'localhost:9200/ik_tutorial/_mapping/star?pretty' 
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ik_tutorial" -d ' 
{
   "settings":{
      "analysis":{
         "char_filter":{},
         "tokenizer":{},
         "filter":{
            "my_pinyin":{
                "type": "pinyin",
                "first_letter":"prefix",
                "padding_char":" "
            }
         },
         "analyzer":{
            "ik_pinyin_analyzer":{
               "type":"custom",
               "tokenizer":"ik_max_word",
               "filter":["my_pinyin", "word_delimiter"]
            }
         }
      }
   },
   "mappings":{
      "star":{
         "properties":{
             "id":{
                "type":"keyword"
             },
             "name":{
                "type":"keyword",
                "fields":{
                   "pinyin":{
                      "type": "text",
                      "store": "true",
                      "term_vector": "with_positions_offsets",
                      "analyzer": "ik_pinyin_analyzer",
                      "boost": 10
                   }
                }
             }
         }
      }
   }
}
'
```
> 参数说明:term_vector 存储词语的偏移位置,在结果高亮的时候有用

#### 分词测试  
输入刘德华
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ik_tutorial/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":     "刘德华"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "liu",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "ldh",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "de",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "hua",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 2
    }
  ]
}
```
输入liu
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ik_tutorial/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":     "liu"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "liu",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "ENGLISH",
      "position" : 0
    }
  ]
}
```
2. 测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ik_tutorial/star/1?pretty' -d ' 
{
  "id": 1,
  "name": "刘德华"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ik_tutorial/star/2?pretty' -d ' 
{
  "id": 2,
  "name": "刘青云"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ik_tutorial/star/3?pretty' -d ' 
{
  "id": 3,
  "name": "黎明"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/ik_tutorial/star/4?pretty' -d ' 
{
  "id": 4,
  "name": "刘锡明"
}'

```

3. 检索
输入liu
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/ik_tutorial/star/_search?pretty' -d '
{
  "query":{ 
     "match":{ "name.pinyin": "liu" } 
  },
  "highlight":{
     "fields":{
        "name.pinyin": {}
     }
  }
}
'
```
结果:
```
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 3,
    "max_score" : 3.2045598,
    "hits" : [
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "1",
        "_score" : 3.2045598,
        "_source" : {
          "id" : 1,
          "name" : "刘德华"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘德华</em>"
          ]
        }
      },
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "2",
        "_score" : 2.0309238,
        "_source" : {
          "id" : 2,
          "name" : "刘青云"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘青云</em>"
          ]
        }
      },
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "4",
        "_score" : 2.0309238,
        "_source" : {
          "id" : 4,
          "name" : "刘锡明"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘锡明</em>"
          ]
        }
      }
    ]
  }
}
```
输入刘
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/ik_tutorial/star/_search?pretty' -d '
{
  "query": { 
        "match": { "name.pinyin": "刘" } 
  },
  "highlight":{
    "fields":{
      "name.pinyin": {}
    }
  }
}
'
```
结果:
```
{
  "took" : 4,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 3,
    "max_score" : 0.320456,
    "hits" : [
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "1",
        "_score" : 0.320456,
        "_source" : {
          "id" : 1,
          "name" : "刘德华"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘德华</em>"
          ]
        }
      },
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "2",
        "_score" : 0.20309238,
        "_source" : {
          "id" : 2,
          "name" : "刘青云"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘青云</em>"
          ]
        }
      },
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "4",
        "_score" : 0.20309238,
        "_source" : {
          "id" : 4,
          "name" : "刘锡明"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘锡明</em>"
          ]
        }
      }
    ]
  }
}
```
输入ldh
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/ik_tutorial/star/_search?pretty' -d '
{
  "query": { 
        "match": { "name.pinyin": "ldh" } 
  },
  "highlight":{
    "fields":{
      "name.pinyin": {}
    }
  }
}
'
```
结果:
```
{
  "took" : 6,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 0.320456,
    "hits" : [
      {
        "_index" : "ik_tutorial",
        "_type" : "star",
        "_id" : "1",
        "_score" : 0.320456,
        "_source" : {
          "id" : 1,
          "name" : "刘德华"
        },
        "highlight" : {
          "name.pinyin" : [
            "<em>刘德华</em>"
          ]
        }
      }
    ]
  }
}
```