### best fields

### 问题引出
检索title或者desc中包含Java或者Tutorial的文档
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": { 
      "bool":{
         "should":[
             {"match":{"title":"Java Tutorial"}},
             {"match":{"desc":"Java Tutorial"}}
         ]
      }
  },
  "_source":["id","title","desc"]
}
'
```
计算每个document的relevance score:每个query的分数,乘以matched query数量,除以总query数量  
例如:
|Document | Title    | Desc
| :-: | :-: | :-: | 
doc1      | Java     | Tutorial
doc2      | Spring   | Java Tutorial

doc1:{"match":{"title":"Java Tutorial"}}这个query有分数假设1.0,{"match":{"desc":"Java Tutorial"}}这个query也有分数假设1.2,,
两个分数加起来1.0 + 1.2 = 2.2,matched query数量 = 2,总query数量 = 2,所以relevance score 2.2 * 2 / 2 = 2.2.  


doc2:{"match":{"title":"Java Tutorial"}}这个query有分数假设0.0,{"match":{"desc":"Java Tutorial"}}这个query也有分数假设1.0,,
两个分数加起来0.0 + 1.0 = 1.0,matched query数量 = 1,总query数量 = 2,所以relevance score 1.0 * 1 / 2 = 0.5.    

期望doc2排在前面
```
"hits" : {
    "total" : 7,
    "max_score" : 4.3569684,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "12",
        "_score" : 4.3569684,
        "_source" : {
          "id" : 12,
          "title" : "Java Language Is The Best",
          "desc" : "I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "13",
        "_score" : 1.6799116,
        "_source" : {
          "id" : 13,
          "title" : "Spark Language Is All So Best",
          "desc" : "But I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.4162004,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java",
          "desc" : "这是一本关于讲述java编程技术的书籍"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.2048765,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial",
          "desc" : "elasticsearch教程记录"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 0.93952733,
        "_source" : {
          "id" : 9,
          "title" : "爪哇学习教程",
          "desc" : "这是关于Java的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial",
          "desc" : "这是关于JavaScript的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "14",
        "_score" : 0.5753642,
        "_source" : {
          "id" : 14,
          "title" : "Java Started",
          "desc" : "This Tutorial is good."
        }
      }
    ]
  }
```
12,13,1,7,9,3,14

#### best fields策略
搜索到的结果,应该是某一个field中匹配到了尽可能多的关键词被排在前面,而不是尽可能多的field匹配到了少数的关键字被排在前面

#### dis_max
直接取多个query中分数最高的那个query的分数,完全不考虑其他query的分数
```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": { 
      "dis_max":{
          "queries":[
              { "match": { "title": "Java Tutorial" }},
              { "match": { "desc":  "Java Tutorial" }}
          ]
      }
  },
  "_source":["id","title","desc"]
}
'
```
结果:
```
"hits" : {
    "total" : 7,
    "max_score" : 2.970674,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "12",
        "_score" : 2.970674,
        "_source" : {
          "id" : 12,
          "title" : "Java Language Is The Best",
          "desc" : "I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "13",
        "_score" : 1.6799116,
        "_source" : {
          "id" : 13,
          "title" : "Spark Language Is All So Best",
          "desc" : "But I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.2048765,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial",
          "desc" : "elasticsearch教程记录"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.0596459,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java",
          "desc" : "这是一本关于讲述java编程技术的书籍"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 0.93952733,
        "_source" : {
          "id" : 9,
          "title" : "爪哇学习教程",
          "desc" : "这是关于Java的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial",
          "desc" : "这是关于JavaScript的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "14",
        "_score" : 0.2876821,
        "_source" : {
          "id" : 14,
          "title" : "Java Started",
          "desc" : "This Tutorial is good."
        }
      }
    ]
  }
```
12,13,7,1,9,3,14

#### tie_breaker
取最高query分数的同时,将其他query的分数乘以tie_breaker,然后综合最高分数query的分数一起进行计算.除了取最高分以外还会考虑其他的query的分数
tie_breaker的值在0~1之间,是个小数.

可能在实际场景中出现的一个情况是这样的:  
1. 某个帖子,doc1,title中包含java,content不包含java beginner任何一个关键词  
2. 某个帖子,doc2,content中包含beginner,title中不包含任何一个关键词  
3. 某个帖子,doc3,title中包含java,content中包含beginner  
4. 最终搜索,可能出来的结果是,doc1和doc2排在doc3的前面,而不是我们期望的doc3排在最前面  

```
curl -H "Content-Type:application/json" -X GET 'localhost:9200/es/blog/_search?pretty' -d '
{
  "query": { 
      "dis_max":{
          "queries":[
              { "match": { "title": "Java Tutorial" }},
              { "match": { "desc":  "Java Tutorial" }}
          ],
          "tie_breaker": 0.3
      }
  },
  "_source":["id","title","desc"]
}
'
```
结果:
```
"hits" : {
    "total" : 7,
    "max_score" : 3.3865623,
    "hits" : [
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "12",
        "_score" : 3.3865623,
        "_source" : {
          "id" : 12,
          "title" : "Java Language Is The Best",
          "desc" : "I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "13",
        "_score" : 1.6799116,
        "_source" : {
          "id" : 13,
          "title" : "Spark Language Is All So Best",
          "desc" : "But I Like This Java Tutorial"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "7",
        "_score" : 1.2048765,
        "_source" : {
          "id" : 7,
          "title" : "Elasticsearch Tutorial",
          "desc" : "elasticsearch教程记录"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "1",
        "_score" : 1.1666123,
        "_source" : {
          "id" : 1,
          "title" : "Thinking In Java",
          "desc" : "这是一本关于讲述java编程技术的书籍"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "9",
        "_score" : 0.93952733,
        "_source" : {
          "id" : 9,
          "title" : "爪哇学习教程",
          "desc" : "这是关于Java的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "3",
        "_score" : 0.87138504,
        "_source" : {
          "id" : 3,
          "title" : "JavaScript Tutorial",
          "desc" : "这是关于JavaScript的学习教程"
        }
      },
      {
        "_index" : "es",
        "_type" : "blog",
        "_id" : "14",
        "_score" : 0.37398672,
        "_source" : {
          "id" : 14,
          "title" : "Java Started",
          "desc" : "This Tutorial is good."
        }
      }
    ]
  }
```
12,13,7,1,9,3,14