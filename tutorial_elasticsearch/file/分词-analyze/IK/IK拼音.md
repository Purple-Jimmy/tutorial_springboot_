### IK 拼音
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/ik_pinyin_tutorial" -d ' 
{
   "settings":{
      "analysis":{
         "char_filter":{},
         "tokenizer":{},
         "filter":{
            "custom_pinyin":{
                "type": "pinyin",
                "keep_none_chinese":false,
                "keep_none_chinese_together":false
            }
         },
         "analyzer":{
            "ik_pinyin_analyzer":{
               "type":"custom",
               "tokenizer":"ik_smart",
               "filter":["custom_pinyin"]
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

#### 测试
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ik_pinyin_tutorial/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":"烈风传"
}
'
```

#### 参数说明
* keep_first_letter: ldh (默认true)
* keep_separate_first_letter:分割first_letter,ldh会变成 l,d,h (默认false)
* limit_first_letter_length:设置first_letter的长度(默认16)
* keep_full_pinyin:liu,de,hua (默认true)
* keep_joined_full_pinyin:liudehua (默认false)
* keep_none_chinese: 剔除中文和数字(默认true)
* keep_none_chinese_together: keep_none_chinese必须先设置false (默认true)
```
"text":"DJ音乐家"
"keep_none_chinese":false,
"keep_none_chinese_together":false
[dj,yin,yue,jia,yyj]
```
* keep_original: (默认false)
```
"text":"DJ音乐家"
"keep_original":true
[d,dj,j,yin,yue,jia,音乐家,yyj]
```

* lowercase: (默认true)
```
"text":"DJ音乐家"
"lowercase":false
[d,dj,j,yin,yue,jia,yyj]
```

* trim_whitespace: (默认true)

* remove_duplicated_term: (默认false)
```
"text":"de的"
"remove_duplicated_term":true
[d,dj,j,yin,yue,jia,yyj]
```


