### script查询
https://segmentfault.com/a/1190000016869041

## 直接用文档的某个字段做计算price*2
```
curl -XGET "http://localhost:9200/tutorial_book/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "match_all": {}
  },
  "script_fields": {
    "test1": {
      "script": {
        "lang": "expression",
        "source": "doc[\"price\"].value *m+n",
        "params":{
           "m":2,
           "n":10
        }
      }
    }
  }
}'
```

## 获取文档中某个字段的值
```
curl -XGET "http://localhost:9200/tutorial_book/_search" -H 'Content-Type: application/json' -d'
{
  "query": {
    "match_all": {}
  },
  "script_fields": {
    "test1": {
     "script": "params[\"_source\"][\"bookName\"]"
    }
  }
}'
```