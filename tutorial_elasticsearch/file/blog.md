### blog
> 数据结构设计

|Field      | Type    | Description
| :-: | :-: | :-: | 
id          | long    | id
title       | text    | 标题
author      | text    | 作者
tag         | text    | 标签:life java spring javascript elasticsearch
zone        | text    | 中国香港 大陆 欧美
weekClick   | double  | 周访问量
monthClick  | double  | 月访问量
desc        | text    | 简介
releaseDate | date    | 发布日期
tagNum      | double  | 标签数
visible     | boolean | 是否可见

> dynamic mapping
```
{
  "es" : {
    "mappings" : {
      "blog" : {
        "properties" : {
          "author" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "desc" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "id" : {
            "type" : "long"
          },
          "monthClick" : {
            "type" : "long"
          },
          "releaseDate" : {
            "type" : "date"
          },
          "tag" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "tagNum" : {
            "type" : "long"
          },
          "title" : {
            "type" : "text",
            "fields" : {
              "keyword" : {
                "type" : "keyword",
                "ignore_above" : 256
              }
            }
          },
          "visible" : {
            "type" : "boolean"
          },
          "weekClick" : {
            "type" : "long"
          },
          "zone" : {
            "properties" : {
              "city" : {
                "type" : "text",
                "fields" : {
                  "keyword" : {
                    "type" : "keyword",
                    "ignore_above" : 256
                  }
                }
              },
              "country" : {
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
    }
  }
}
```

> 测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/1?pretty' -d ' 
{
  "id": 1,
  "title": "Thinking In Java",
  "author" : ["Bruce Eckel"],
  "tag": "java",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 60,
  "desc": "这是一本关于讲述java编程技术的书籍",
  "releaseDate": "2007-06-01",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/2?pretty' -d ' 
{
  "id": 2,
  "title": "Spring In Action",
  "author" : ["Craig Walls"],
  "tag": "java spring",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "weekClick": 15,
  "monthClick": 65,
  "desc": "介绍了使用Spring框架进行开发必须掌握的核心概念",
  "releaseDate": "2013-06-01",
  "tagNum":2,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/3?pretty' -d ' 
{
  "id": 3,
  "title": "JavaScript Tutorial",
  "author" : ["Jack","Lucy"],
  "tag": "javascript",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "weekClick": 0,
  "monthClick": 0,
  "desc": "这是关于JavaScript的学习教程",
  "releaseDate": "2017-10-16",
  "tagNum":1,
  "visible":false
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/4?pretty' -d ' 
{
  "id": 4,
  "title": "2018冬雪",
  "author" : ["Jimmy"],
  "tag": "life",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 50,
  "desc": "2018年的初雪,冷",
  "releaseDate": "2018-01-25",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/5?pretty' -d ' 
{
  "id": 5,
  "title": "心随云动",
  "author" : ["Jimmy"],
  "tag": "life",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 25,
  "monthClick": 60,
  "desc": "心情随笔,一点点生活的记录",
  "releaseDate": "2017-01-25",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/6?pretty' -d ' 
{
  "id": 6,
  "title": "白云流水",
  "author" : ["Jimmy"],
  "tag": "life",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 25,
  "monthClick": 70,
  "desc": "心情随笔,一点点生活的记录.黎明前的黑暗,总会被朝阳摧散.",
  "releaseDate": "2017-01-28",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/7?pretty' -d ' 
{
  "id": 7,
  "title": "Elasticsearch Tutorial",
  "author" : ["刘德华","黎明"],
  "tag": "elasticsearch",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 15,
  "monthClick": 45,
  "desc": "elasticsearch教程记录",
  "releaseDate": "2017-01-30",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/8?pretty' -d ' 
{
  "id": 8,
  "title": "Springboot教程",
  "author" : ["刘德华","郑伊健"],
  "tag": "java spring",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 30,
  "monthClick": 60,
  "desc": "这是关于Springboot学习的教程",
  "releaseDate": "2017-01-30",
  "tagNum":2,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/9?pretty' -d ' 
{
  "id": 9,
  "title": "爪哇学习教程",
  "author" : ["Jimmy"],
  "tag": "java spring",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 20,
  "monthClick": 50,
  "desc": "这是关于Java的学习教程",
  "releaseDate": "2017-01-30",
  "tagNum":2,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/10?pretty' -d ' 
{
  "id": 10,
  "title": "关于未来的憧憬",
  "author" : ["Jimmy"],
  "tag": "life",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 0,
  "monthClick": 0,
  "desc": "内心最真实的声音,在最好也最坏的年代,对未来的憧憬.",
  "releaseDate": "2018-02-01",
  "tagNum":1,
  "visible":false
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/11?pretty' -d ' 
{
  "id": 11,
  "title": "测试分页数据",
  "author" : ["Lily"],
  "tag": "life java elasticsearch",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 100,
  "desc": "模拟数据,测试elasticsearch分页数据,默认分页10条数据.",
  "releaseDate": "2018-02-01",
  "tagNum":3,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/12?pretty' -d ' 
{
  "id": 12,
  "title": "Java Language Is The Best",
  "author" : ["Bruce"],
  "tag": "java",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 100,
  "desc": "I Like This Java Tutorial",
  "releaseDate": "2018-02-01",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/13?pretty' -d ' 
{
  "id": 13,
  "title": "Spark Language Is All So Best",
  "author" : ["Bruce"],
  "tag": "java",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 100,
  "desc": "But I Like This Java Tutorial",
  "releaseDate": "2018-02-01",
  "tagNum":1,
  "visible":true
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/es/blog/14?pretty' -d ' 
{
  "id": 14,
  "title": "Java Started",
  "author" : ["Bruce"],
  "tag": "java",
  "zone": {
      "country": "中国",
      "city": ""
  },
  "weekClick": 10,
  "monthClick": 100,
  "desc": "This Tutorial is good.",
  "releaseDate": "2018-02-01",
  "tagNum":1,
  "visible":true
}'
```
