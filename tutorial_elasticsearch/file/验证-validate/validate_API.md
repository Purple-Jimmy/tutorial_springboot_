### validate
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_validate/query?rewrite=true&pretty" -d'
{
 "query": {
    "match": {
      "name.pinyin": "cct"
    }
  }
}
'
```
结果:
```
{
  "valid" : true,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "explanations" : [
    {
      "index" : "ysten_phrase_ngram",
      "valid" : true,
      "explanation" : "Synonym(name.pinyin:c name.pinyin:c name.pinyin:cc) name.pinyin:c"
    }
  ]
}
```

### explain参数
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_validate/query?explain&pretty" -d'
{
 "query": {
    "match": {
      "name.pinyin": "cct"
    }
  }
}
'
```
结果:
```
{
  "valid" : true,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "failed" : 0
  },
  "explanations" : [
    {
      "index" : "ysten_phrase_ngram",
      "valid" : true,
      "explanation" : "+(name.pinyin:c name.pinyin:c Synonym(name.pinyin:c name.pinyin:cc name.pinyin:cct name.pinyin:t)) #*:*"
    }
  ]
}
```

