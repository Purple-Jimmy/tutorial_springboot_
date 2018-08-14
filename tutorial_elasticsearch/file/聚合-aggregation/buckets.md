### buckets 桶
满足特定条件的文档的集合.随着聚合被执行,每份文档中的值会被计算来决定它们是否匹配了桶的条件,如果匹配成功,那么该文档会被置入该桶中,
同时聚合会继续执行.比如日期2014-10-28属于十月份这个桶.

* terms 
根据zone.country.keyword查询各country有多少文档
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "country_terms_aggs":{
         "terms":{
             "field":"zone.country.keyword",
             "order" : { "_key" : "asc" }
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 4,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "country_terms_aggs" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "中国",
          "doc_count" : 12
        },
        {
          "key" : "美国",
          "doc_count" : 2
        }
      ]
    }
  }
}
```
**注:size只获取聚合结果而不要执行聚合的原始数据**

* range 
查询weekClick在12分以下,12-27,27以上的文档
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_range_aggs":{
         "range":{
             "field":"weekClick",
             "ranges":[
                {
                  "to":12
                },
                {
                  "from":12,
                  "to":27
                },
                {
                  "from":27
                }
             ]
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 9,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "weekClick_range_aggs" : {
      "buckets" : [
        {
          "key" : "*-12.0",
          "to" : 12.0,
          "doc_count" : 8
        },
        {
          "key" : "12.0-27.0",
          "from" : 12.0,
          "to" : 27.0,
          "doc_count" : 5
        },
        {
          "key" : "27.0-*",
          "from" : 27.0,
          "doc_count" : 1
        }
      ]
    }
  }
}
```
自定义key的名字
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_range_aggs":{
         "range":{
             "field":"weekClick",
             "keyed":true,
             "ranges":[
                {
                  "key" : "小于12",
                  "to":12
                },
                {
                  "key" : "12-27之间",
                  "from":12,
                  "to":27
                },
                {
                  "key" : "27以上",
                  "from":27
                }
             ]
         }
     }
  }
}
'
```

* date range 
1. now+10y:表示从现在开始的第10年
2. now+10M:表示从现在开始的第10个月
3. 1990-01-01||+20y:表示从1990-01-01开始后的第20年,即2010-01-01
4. now/y:表示在年位上做舍入运算

```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "releaseDate_date_range_aggs":{
         "date_range":{
             "field":"releaseDate",
             "format": "yyyy-MM-dd",
             "time_zone": "GMT",
             "keyed":true,
             "ranges":[
                {
                  "from":"2017-10-01",
                  "to":"now"
                }
             ]
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "releaseDate_date_range_aggs" : {
      "buckets" : {
        "2017-10-01-2018-02-28" : {
          "from" : 1.506816E12,
          "from_as_string" : "2017-10-01",
          "to" : 1.519786613712E12,
          "to_as_string" : "2018-02-28",
          "doc_count" : 7
        }
      }
    }
  }
}
```

* histogram 直方图聚合,将某个number类型字段等分成n份,统计落在每一个区间内的记录数
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_histogram_aggs":{
         "histogram":{
             "field":"weekClick",
             "interval": 5,
             "min_doc_count": 2,
             "keyed" : true
         }
     }
  }
}
'
```
结果: 
```
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "weekClick_histogram_aggs" : {
      "buckets" : {
        "0.0" : {
          "key" : 0.0,
          "doc_count" : 2
        },
        "10.0" : {
          "key" : 10.0,
          "doc_count" : 6
        },
        "15.0" : {
          "key" : 15.0,
          "doc_count" : 2
        },
        "25.0" : {
          "key" : 25.0,
          "doc_count" : 2
        }
      }
    }
  }
}
```

* date histogram 时间直方图聚合
统计同一年的blog
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "releaseDate_date_histogram_aggs":{
         "date_histogram":{
             "field":"releaseDate",
             "interval": "year",
             "format": "yyyy",
             "keyed" : true
         }
     }
  }
}
'
```
interval取值:year, quarter, month, week, day, hour, minute, second
结果:
```
{
  "took" : 10,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 14,
    "max_score" : 0.0,
    "hits" : [ ]
  },
  "aggregations" : {
    "releaseDate_date_histogram_aggs" : {
      "buckets" : {
        "2007" : {
          "key_as_string" : "2007",
          "key" : 1167609600000,
          "doc_count" : 1
        },
        "2008" : {
          "key_as_string" : "2008",
          "key" : 1199145600000,
          "doc_count" : 0
        },
        "2009" : {
          "key_as_string" : "2009",
          "key" : 1230768000000,
          "doc_count" : 0
        },
        "2010" : {
          "key_as_string" : "2010",
          "key" : 1262304000000,
          "doc_count" : 0
        },
        "2011" : {
          "key_as_string" : "2011",
          "key" : 1293840000000,
          "doc_count" : 0
        },
        "2012" : {
          "key_as_string" : "2012",
          "key" : 1325376000000,
          "doc_count" : 0
        },
        "2013" : {
          "key_as_string" : "2013",
          "key" : 1356998400000,
          "doc_count" : 1
        },
        "2014" : {
          "key_as_string" : "2014",
          "key" : 1388534400000,
          "doc_count" : 0
        },
        "2015" : {
          "key_as_string" : "2015",
          "key" : 1420070400000,
          "doc_count" : 0
        },
        "2016" : {
          "key_as_string" : "2016",
          "key" : 1451606400000,
          "doc_count" : 0
        },
        "2017" : {
          "key_as_string" : "2017",
          "key" : 1483228800000,
          "doc_count" : 6
        },
        "2018" : {
          "key_as_string" : "2018",
          "key" : 1514764800000,
          "doc_count" : 6
        }
      }
    }
  }
}
```

* missing 值缺损聚合,是一类单桶聚合即最终只会产生一个"桶"
统计releaseDate缺失的blog

