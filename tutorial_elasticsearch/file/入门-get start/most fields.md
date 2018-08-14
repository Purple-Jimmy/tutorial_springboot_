### most fields
尽可能返回更多field匹配到某个关键词的doc,优先返回回来

```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/es/blog/_search?pretty' -d '
{
   "query": { 
       "multi_match": { 
           "query":  "Thinking Tutorial",
           "type":   "most_fields", 
           "fields": [ "title", "title.keyword" ]
       }
   },
   "_source":["id","title"]
}
'
```
结果:
```
  "hits" : {
    "total" : 3,
    "max_score" : 1.2048765,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.2048765,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0596459,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial"
        }
      }
    ]
  }
```


#### 与best_fields的区别
1. best_fields是对多个field进行搜索,挑选某个field匹配度最高的那个分数,同时在多个query最高分相同的情况下,在一定程度上考虑其他query的分数.
简单来说,你对多个field进行搜索,就想搜索到某一个field尽可能包含更多关键字的数据  

* 优点:通过best_fields策略,以及综合考虑其他field,还有minimum_should_match支持,可以尽可能精准地将匹配的结果推送到最前面
* 缺点:除了那些精准匹配的结果,其他差不多大的结果,排序结果不是太均匀,没有什么区分度了 

2. most_fields综合多个field一起进行搜索,尽可能多的让所有field的query参与到总分数的计算中来,
此时就会是个大杂烩,出现类似best_fields案例最开始的那个结果,结果不一定精准,某一个document的一个field包含更多的关键字,
但是因为其他document有更多field匹配到了,所以排在了前面.所以需要建立类似title.keyword这样的field,尽可能让某一个field精准匹配query string,
贡献更高的分数,将更精准匹配的数据排到前面  

* 优点:将尽可能匹配更多field的结果推送到最前面,整个排序结果是比较均匀的  
* 缺点:可能那些精准匹配的结果,无法推送到最前面  


#### 弊端
> cross-fields搜索,一个唯一标识,跨了多个field.比如一个人,标识是姓名.一个建筑,它的标识是地址.
> 姓名可以散落在多个field中,比如first_name和last_name中,地址可以散落在country,province,city中.  
> 跨多个field搜索一个标识,比如搜索一个人名,或者一个地址就是cross-fields搜索.  
> 初步来说,如果要实现,可能用most_fields比较合适.

1. 只是找到尽可能多的field匹配的doc,而不是某个field完全匹配的doc
2. most_fields,没办法用minimum_should_match去掉长尾数据,就是匹配的特别少的结果
3. TF/IDF算法


#### 解决方案
1. copy_to
2. cross_fields