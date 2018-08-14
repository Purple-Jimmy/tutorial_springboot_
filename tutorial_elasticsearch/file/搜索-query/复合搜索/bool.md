### bool
#### 可取值:
1. must:文档必须匹配must选项下的检索条件,相当于逻辑运算AND.
2. should:文档可以匹配也可以不匹配should选项下的检索条件,相当于逻辑运算OR.
3. must_not:与must相反.
4. filter:和must一样,匹配filter选项下的检索条件.但是filter不评分,只过滤.

* 检索releaseDate=2017-01-30或者title含有Tutorial,并且releaseDate不等于2017-10-16
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "_source":["id","title","releaseDate"],
  "query": {
      "constant_score":{
         "filter":{
            "bool":{
               "should":[
                  {"term":{"releaseDate":"2017-01-30"}},
                  {"match":{"title":"Tutorial"}}
               ],
               "must_not":{"term":{"releaseDate":"2017-10-16"}}}
            }
         }
      }
  }
}
'
```
结果:
```
"hits" : {
    "total" : 3,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 8,
          "title" : "Springboot教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 9,
          "title" : "爪哇学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```   

* 检索title=白云流水,或者weekClick=15并且releaseDate等于2017-01-30
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": {
     "constant_score":{
        "filter":{
           "bool":{
              "should":[
                 {"term":{"title.keyword":"白云流水"}},
                 {"bool":
                   {
                     "must":[
                        {"term":{"weekClick":15}},
                        {"term":{"releaseDate":"2017-01-30"}}
                     ]
                   }
                 }
              ]
           }
        }
     }
  },
  "_source":["id","title","weekClick","releaseDate"]
}
'
```
结果:
```
"hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "6",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 25,
          "releaseDate" : "2017-01-28",
          "id" : 6,
          "title" : "白云流水"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "weekClick" : 15,
          "releaseDate" : "2017-01-30",
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```

#### bool组合多个搜索条件如何计算relevance score
```
must和should搜索对应的分数加起来除以must和should的总数    
should是可以影响相关度分数的  
must是确保必须有这个关键字,同时会根据这个must的条件去计算出document对这个搜索条件的relevance score
在满足must的基础之上,should中的条件,不匹配也可以,但是如果匹配的更多,那么document的relevance score就会更高
```  
* 检索title必须含有Tutorial,或者title含有教程的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "_source":["id","title","releaseDate"],
  "query": {
      "bool":{
         "must":{"match":{"title":"Tutorial"}},
         "should":[
             {"match":{"title":"教程"}}
         ]
      }
  }
}
'
```
结果:
```
"hits" : {
    "total" : 2,
    "max_score" : 0.87138504,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "releaseDate" : "2017-10-16",
          "id" : 3,
          "title" : "JavaScript Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 0.7549128,
        "_source" : {
          "releaseDate" : "2017-01-30",
          "id" : 7,
          "title" : "Elasticsearch Tutorial"
        }
      }
    ]
  }
```
分析:  

**
默认情况下,should是可以不匹配任何一个的,比如上面的搜索中,this is java blog,就不匹配任何一个should条件
但是有个例外的情况,如果没有must的话,那么should中必须至少匹配一个才可以
比如下面的搜索,should中有4个条件,默认情况下,只要满足其中一个条件,就可以匹配作为结果返回
但是可以精准控制,should的4个条件中,至少匹配几个才能作为结果返回
GET /forum/article/_search
{
  "query": {
    "bool": {
      "should": [
        { "match": { "title": "java"}},
        { "match": { "title": "elasticsearch"}},
        { "match": { "title": "hadoop"}},
	    { "match": { "title": "spark"}}
      ],
      "minimum_should_match": 3 
    }
  }
}
**

