### copy_to
将多个field组合成一个field并建立倒排索引
```
curl -H 'Content-Type: application/json' -XPUT "localhost:9200/es" -d ' 
{
 "mappings": {
    "blog": {
       "properties" : {
          "new_tag":{
              "type":"text",
              "copy_to":"full_tag"
          },
          "full_tag":{
              "type" : "text"
          }
       }
    }
 }
}
'
```