### IK拼音实战
1. 设置mapping
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ysten_program_phrase" -d ' 
{
  "settings":{
       "analysis":{
          "filter":{
             "ngram_filter": { 
                 "type": "edge_ngram",
                 "min_gram": 1,
                 "max_gram": 5
             }
          },
		  "tokenizer":{
		    "custom_pinyin":{
			     "type" : "pinyin",
				 "keep_first_letter" : true,
				 "keep_full_pinyin" : true,
				 "keep_none_chinese" : true,
				 "keep_original" : true,
				 "limit_first_letter_length" : 16,
				 "lowercase" : true,
				 "trim_whitespace" : true,
				 "keep_none_chinese_in_first_letter" : false,
				 "keep_joined_full_pinyin":true
			}
		  },
          "analyzer":{
             "ik_pinyin_analyzer":{
                "type":"custom",
                "tokenizer":"custom_pinyin"
             },
             "chinese_analyzer":{
                 "type":"custom",
                 "tokenizer":"ik_max_word",
                 "filter":["ngram_filter"]
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
                 "analyzer": "chinese_analyzer",
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
			     "type": "integer"
			  },
			  "tag":{
                 "type" : "text",
                 "fields" : {
                    "keyword" : {
                       "type" : "keyword",
                       "ignore_above" : 256
                    }
                  }
              }
          }
       }
    }
 }
'
```

测试分词
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_program_phrase/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":     "七色光(4k)"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "qi",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "七色光(4k)",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qiseguang",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qsg",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "se",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 1
    },
    {
      "token" : "guang",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 2
    },
    {
      "token" : "4",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 3
    },
    {
      "token" : "k",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 4
    }
  ]
}
```


* 只索引拼音首字母和全拼
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ysten_program_phrase" -d ' 
{
  "settings":{
       "analysis":{
          "filter":{
             "ngram_filter": { 
                 "type": "edge_ngram",
                 "min_gram": 1,
                 "max_gram": 5
             }
          },
		  "tokenizer":{
		    "custom_pinyin":{
			     "type" : "pinyin",
				 "keep_first_letter" : true,
				 "keep_full_pinyin" : false,
				 "keep_none_chinese" : true,
				 "keep_original" : true,
				 "limit_first_letter_length" : 16,
				 "lowercase" : true,
				 "trim_whitespace" : true,
				 "keep_none_chinese_in_first_letter" : false,
				 "keep_joined_full_pinyin":true
			}
		  },
          "analyzer":{
             "ik_pinyin_analyzer":{
                "type":"custom",
                "tokenizer":"custom_pinyin"
             },
             "chinese_analyzer":{
                 "type":"custom",
                 "tokenizer":"ik_max_word",
                 "filter":["ngram_filter"]
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
                 "analyzer": "chinese_analyzer",
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
			     "type": "integer"
			  },
			  "tag":{
                 "type" : "text",
                 "fields" : {
                    "keyword" : {
                       "type" : "keyword",
                       "ignore_above" : 256
                    }
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
  "tokens" : [
    {
      "token" : "七色光(4k)",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qiseguang",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qsg",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "4",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 3
    },
    {
      "token" : "k",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 4
    }
  ]
}
```

* 使用ngram
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ysten_program_phrase" -d ' 
{
  "settings":{
       "analysis":{
          "filter":{
             "ngram_filter": { 
                 "type": "edge_ngram",
                 "min_gram": 1,
                 "max_gram": 5
             }
          },
		  "tokenizer":{
		    "custom_pinyin":{
			     "type" : "pinyin",
				 "keep_first_letter" : true,
				 "keep_full_pinyin" : false,
				 "keep_none_chinese" : true,
				 "keep_original" : true,
				 "limit_first_letter_length" : 16,
				 "lowercase" : true,
				 "trim_whitespace" : true,
				 "keep_none_chinese_in_first_letter" : false,
				 "keep_joined_full_pinyin":true
			}
		  },
          "analyzer":{
             "ik_pinyin_analyzer":{
                "type":"custom",
                "tokenizer":"custom_pinyin",
                "filter":["ngram_filter"]
             },
             "chinese_analyzer":{
                 "type":"custom",
                 "tokenizer":"ik_max_word",
                 "filter":["ngram_filter"]
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
                 "analyzer": "chinese_analyzer",
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
			     "type": "integer"
			  },
			  "tag":{
                 "type" : "text",
                 "fields" : {
                    "keyword" : {
                       "type" : "keyword",
                       "ignore_above" : 256
                    }
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
  "tokens" : [
    {
      "token" : "七",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "七色",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "七色光",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "七色光(",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "七色光(4",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "q",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qi",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qis",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qise",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qiseg",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "q",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qs",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "qsg",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 0
    },
    {
      "token" : "4",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 3
    },
    {
      "token" : "k",
      "start_offset" : 0,
      "end_offset" : 0,
      "type" : "word",
      "position" : 4
    }
  ]
}
```