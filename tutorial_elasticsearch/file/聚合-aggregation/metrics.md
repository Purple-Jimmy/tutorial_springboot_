### metrics 指标
对桶内的文档进行聚合分析的操作.桶能让我们划分文档到有意义的集合,但是最终我们需要的是对这些桶内的文档进行一些指标的计算.
分桶是一种达到目的地的手段:它提供了一种给文档分组的方法来让我们可以计算感兴趣的指标.

* avg 平均值
查询weekClick的平均值
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_avg_aggs":{
         "avg":{
             "field":"weekClick"
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
    "weekClick_avg_aggs" : {
      "value" : 13.571428571428571
    }
  }
}
```
missing参数

* min 最小值
查询weekClick的最小值
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_min_aggs":{
         "min":{
             "field":"weekClick"
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 5,
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
    "weekClick_min_aggs" : {
      "value" : 0.0
    }
  }
}
```

* sum 求和
* stats 最大、最小、和、平均值一起求出来
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_stats_aggs":{
         "stats":{
             "field":"weekClick"
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 5,
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
    "weekClick_stats_aggs" : {
      "count" : 14,
      "min" : 0.0,
      "max" : 30.0,
      "avg" : 13.571428571428571,
      "sum" : 190.0
    }
  }
}
```

* extended stats 字段的其他属性(最大最小,方差等)
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_extended_stats_aggs":{
         "extended_stats":{
             "field":"weekClick"
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
    "weekClick_stats_aggs" : {
      "count" : 14,
      "min" : 0.0,
      "max" : 30.0,
      "avg" : 13.571428571428571,
      "sum" : 190.0,
      "sum_of_squares" : 3600.0,
      "variance" : 72.9591836734694,
      "std_deviation" : 8.541614816501,
      "std_deviation_bounds" : {
        "upper" : 30.654658204430568,
        "lower" : -3.5118010615734274
      }
    }
  }
}
```

* cardinality 字段唯一值,相当于sql中的distinct
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_cardinality_aggs":{
         "cardinality":{
             "field":"weekClick"
         }
     }
  }
}
'
```
结果:weekClick有6种不一样的取值
```
{
  "took" : 24,
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
    "weekClick_cardinality_aggs" : {
      "value" : 6
    }
  }
}
```

* percentiles 百分比统计
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_percentiles_aggs":{
         "percentiles":{
             "field":"weekClick"
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 12,
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
    "weekClick_percentiles_aggs" : {
      "values" : {
        "1.0" : 0.0,
        "5.0" : 0.0,
        "25.0" : 10.0,
        "50.0" : 10.0,
        "75.0" : 18.75,
        "95.0" : 26.749999999999996,
        "99.0" : 29.349999999999994
      }
    }
  }
}
```

*  percentile ranks 
查询weekClick在15-60之间的分布
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_percentile_ranks_aggs":{
         "percentile_ranks":{
             "field":"weekClick",
             "values" : [15, 60]
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
    "weekClick_percentile_ranks_aggs" : {
      "values" : {
        "15.0" : 64.28571428571429,
        "60.0" : 100.0
      }
    }
  }
}
```

* top hits取符合条件的前n条数据记录
查询weekClick排在前面的2个数据
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_top_hits_aggs":{
         "top_hits":{
             "from": 0,
             "size": 2,
             "sort": [
                 {
                   "weekClick": {           
                       "order": "desc"
                   }
                 }
             ],
             "_source":{
                 "include": [       
                     "id",
                     "title",
                     "weekClick"
                 ]
             }
         }
     }
  }
}
'
```
结果:
```
{
  "took" : 5,
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
    "weekClick_top_hits_aggs" : {
      "hits" : {
        "total" : 14,
        "max_score" : null,
        "hits" : [
          {
            "_index" : "es",
            "_type" : "blog",
            "_id" : "8",
            "_score" : null,
            "_source" : {
              "weekClick" : 30,
              "id" : 8,
              "title" : "Springboot教程"
            },
            "sort" : [
              30
            ]
          },
          {
            "_index" : "es",
            "_type" : "blog",
            "_id" : "5",
            "_score" : null,
            "_source" : {
              "weekClick" : 25,
              "id" : 5,
              "title" : "心随云动"
            },
            "sort" : [
              25
            ]
          }
        ]
      }
    }
  }
}
```

* value count 数量统计,看看这个字段一共有多少个不一样的数值
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/es/blog/_search?pretty" -d'
{
  "size":0,
  "aggs":{
     "weekClick_value_count_aggs":{
         "value_count":{
             "field":"weekClick"
         }
     }
  }
}
'
```
结果:一共14条数据,对比cardinality聚合
```
{
  "took" : 6,
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
    "weekClick_value_count_aggs" : {
      "value" : 14
    }
  }
}
```


* geo bounds 计算出所有的地理坐标将会落在一个矩形区域
  
* geo centroid 计算出所有文档的大概的中心点
  
  
 
