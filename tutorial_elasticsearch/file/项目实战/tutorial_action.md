## 问题:输入"熊出"检索不到数据

### 检索name字段
* 不带ngram,["熊出没","出没"]有数据,["熊","出","没","熊出"]无数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name": "熊出"
    }
  }
}
'
```

* 带ngram,["熊出没","出没","熊出","熊"]有数据,["没"]无数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name": "没"
    }
  }
}
'
```

### 检索name.pinyin字段  拼音
* 不带ngram,["xiongchumei","xiongchu","chumei"]有数据,["xcm","xc","cm","x","c","m","xchumei"]无数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "xiongchu"
    }
  }
}
'
```

* 带ngram,["xiongcm",x","c","m","xcm","xc","cm",xiongchumei","xiongchu","chumei","xchumei"]有数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "xiongcm"
    }
  }
}
'
```

### 检索name.pinyin字段  中文
* 不带ngram,["熊出没","熊出","出没","熊","出","没"]有数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "没"
    }
  }
}
'
```
* 带ngram,["熊出没","熊出","出没","熊","出","没"]有数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "熊"
    }
  }
}
'
```

### 检索name.pinyin字段  中英混合
* 不带ngram,["熊chumei"]有数据,["熊cm"]无数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "熊chumei"
    }
  }
}
'
```
* 带ngram,["熊chumei","熊cm"]有数据.
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "熊cm"
    }
  }
}
'
```

## 问题:"穿长筒靴的猫"没有拼音首字母拼音:cctxdm.
分析查看
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/_analyze?pretty" -d'
{
  "analyzer": "ik_pinyin_analyzer",
  "text":     "穿长筒靴的猫"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "c",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "ch",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "chu",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "chua",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "chuan",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "c",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "c",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "ch",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "cha",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "chan",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "chang",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "c",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "t",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "to",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "ton",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "tong",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "t",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "x",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "CN_CHAR",
      "position" : 3
    },
    {
      "token" : "xu",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "CN_CHAR",
      "position" : 3
    },
    {
      "token" : "xue",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "CN_CHAR",
      "position" : 3
    },
    {
      "token" : "x",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "CN_CHAR",
      "position" : 3
    },
    {
      "token" : "d",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "CN_CHAR",
      "position" : 4
    },
    {
      "token" : "de",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "CN_CHAR",
      "position" : 4
    },
    {
      "token" : "d",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "CN_CHAR",
      "position" : 4
    },
    {
      "token" : "m",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "CN_CHAR",
      "position" : 5
    },
    {
      "token" : "ma",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "CN_CHAR",
      "position" : 5
    },
    {
      "token" : "mao",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "CN_CHAR",
      "position" : 5
    },
    {
      "token" : "m",
      "start_offset" : 5,
      "end_offset" : 6,
      "type" : "CN_CHAR",
      "position" : 5
    }
  ]
}
```
检索
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_phrase_ngram/programPhrase/_search?pretty" -d'
{
 "query": {
    "match_phrase": {
      "name.pinyin": "cc"
    }
  }
}
'
```
但是如上可以检索到数据.["穿长筒靴的猫","穿长txdm","cctxdm","cc"]也可以.


## 问题:"cc"连"熊出没"也会搜出来??
