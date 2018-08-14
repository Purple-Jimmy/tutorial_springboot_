### match_phrase
检索desc中包含Spring Action的文档
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "query": { 
       "match_phrase": { 
           "title":"Spring Action"
       }
   },
   "_source":["id","title"]
}
'
```
结果:无数据

```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "query": { 
       "match_phrase": { 
           "title":"Spring In Action"
       }
   },
   "_source":["id","title"]
}
'
```
结果:
```
"hits" : {
    "total" : 1,
    "max_score" : 3.0679965,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "2",
        "_score" : 3.0679965,
        "_source" : {
          "id" : 2,
          "title" : "Spring In Action"
        }
      }
    ]
  }
```
#### 原理
> 索引中的position

hello world, java spark		doc1  
hi, spark java				doc2  

hello 		doc1(0)  		
world		doc1(1)  
java		doc1(2) doc2(2)  
spark		doc1(3) doc2(1)  

1. 首先找到每个term都在的那些doc,就是要求一个doc必须包含每个term,才能拿出来继续计算
2. position要一致


#### slop
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "query": { 
       "match_phrase": { 
           "title":{
              "query":"Spring Action",
              "slop": 1
           }
       }
   },
   "_source":["id","title"]
}
'
```
结果:与输入查询关键字Spring In Action时一致.
