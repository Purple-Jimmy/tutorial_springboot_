### explain

```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/ysten_program_series/programSeries/_search?pretty" -d'
{    "explain": true, 
     "from" : 0, 
     "size" : 10,
     "track_scores":true,
     "query": {
      "bool":{
             "must":[{
			     "match":{
				     "seriesPinYin":{
					     "query":"dedtt",
						 "fuzziness":"AUTO"
					    } 
					 }
				}
			 ]
      }
  },
     "sort":{
	     "_score": { "order": "desc" }
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
    "total" : 2,
    "max_score" : 3.5402682,
    "hits" : [
      {
        "_shard" : "[ysten_program_series][3]",
        "_node" : "TTcu93ieRCeQ5WF4U_9Q-A",
        "_index" : "ysten_program_series",
        "_type" : "programSeries",
        "_id" : "360",
        "_score" : 3.5402682,
        "_source" : {
          "id" : 360,
          "name" : "大耳朵图图Ⅳ",
          "imgUrl" : "http://images.center.bcs.ottcn.com:8080/images/ysten/images/lanmudianbo/se/dedtt420140918.jpg",
          "bitRate" : "SD",
          "grade" : 9.0,
          "playCounts" : 0,
          "searchType" : "watchtv",
          "years" : "2011",
          "zone" : "",
          "tag" : [
            "动画",
            "2011",
            "中国大陆",
            "亲子",
            "益智"
          ],
          "language" : [
            "其他"
          ],
          "epgGroup" : [
            "1000169",
            "1000167"
          ],
          "cpCode" : "watchtv",
          "verticalPosterAdd" : "http://images.center.bcs.ottcn.com:8080/images/ysten/images/ysten/TV//KDSBHBsedhp045.jpg",
          "middlePosterAdd" : "",
          "programTotalCount" : 1,
          "isTidbits" : 0,
          "lastModifyDate" : "2016-09-12T20:03:23+0800",
          "weekClickRate" : 14787,
          "monthClickRate" : 55271,
          "syncTime" : "2018-04-24T16:33:45+0800",
          "modifyTime" : "2018-04-24T16:33:45+0800",
          "seriesPinYin" : [
            "dedtt",
            "daerduotutu",
            "daierduotutu"
          ]
        },
        "_explanation" : {
          "value" : 3.5402684,
          "description" : "weight(seriesPinYin:dedtt in 4) [PerFieldSimilarity], result of:",
          "details" : [
            {
              "value" : 3.5402684,
              "description" : "score(doc=4,freq=1.0 = termFreq=1.0\n), product of:",
              "details" : [
                {
                  "value" : 3.232121,
                  "description" : "idf, computed as log(1 + (docCount - docFreq + 0.5) / (docFreq + 0.5)) from:",
                  "details" : [
                    {
                      "value" : 1.0,
                      "description" : "docFreq",
                      "details" : [ ]
                    },
                    {
                      "value" : 37.0,
                      "description" : "docCount",
                      "details" : [ ]
                    }
                  ]
                },
                {
                  "value" : 1.0953391,
                  "description" : "tfNorm, computed as (freq * (k1 + 1)) / (freq + k1 * (1 - b + b * fieldLength / avgFieldLength)) from:",
                  "details" : [
                    {
                      "value" : 1.0,
                      "description" : "termFreq=1.0",
                      "details" : [ ]
                    },
                    {
                      "value" : 1.2,
                      "description" : "parameter k1",
                      "details" : [ ]
                    },
                    {
                      "value" : 0.75,
                      "description" : "parameter b",
                      "details" : [ ]
                    },
                    {
                      "value" : 3.8108108,
                      "description" : "avgFieldLength",
                      "details" : [ ]
                    },
                    {
                      "value" : 3.0,
                      "description" : "fieldLength",
                      "details" : [ ]
                    }
                  ]
                }
              ]
            }
          ]
        }
      },
      {
        "_shard" : "[ysten_program_series][4]",
        "_node" : "TTcu93ieRCeQ5WF4U_9Q-A",
        "_index" : "ysten_program_series",
        "_type" : "programSeries",
        "_id" : "348",
        "_score" : 3.0042098,
        "_source" : {
          "id" : 348,
          "name" : "大耳朵图图Ⅲ",
          "imgUrl" : "http://images.center.bcs.ottcn.com:8080/images/ysten/images/lanmudianbo/SE/DHP/DEDTT333.jpg",
          "bitRate" : "SD",
          "grade" : 8.2,
          "playCounts" : 0,
          "searchType" : "watchtv",
          "years" : "2014",
          "zone" : "",
          "tag" : [
            "动画",
            "2014",
            "中国大陆",
            "高兴",
            "益智"
          ],
          "language" : [
            "其他"
          ],
          "epgGroup" : [
            "1000169",
            "1000167"
          ],
          "cpCode" : "watchtv",
          "verticalPosterAdd" : "http://images.center.bcs.ottcn.com:8080/images/ysten/images/ysten/TV//KDSBHBsedhp044.jpg",
          "middlePosterAdd" : "",
          "programTotalCount" : 1,
          "isTidbits" : 0,
          "lastModifyDate" : "2016-09-12T20:03:23+0800",
          "weekClickRate" : 9798,
          "monthClickRate" : 40047,
          "syncTime" : "2018-04-24T16:33:45+0800",
          "modifyTime" : "2018-04-24T16:33:45+0800",
          "seriesPinYin" : [
            "dedtt",
            "daerduotutu",
            "daierduotutu"
          ]
        },
        "_explanation" : {
          "value" : 3.0042098,
          "description" : "weight(seriesPinYin:dedtt in 4) [PerFieldSimilarity], result of:",
          "details" : [
            {
              "value" : 3.0042098,
              "description" : "score(doc=4,freq=1.0 = termFreq=1.0\n), product of:",
              "details" : [
                {
                  "value" : 2.9618306,
                  "description" : "idf, computed as log(1 + (docCount - docFreq + 0.5) / (docFreq + 0.5)) from:",
                  "details" : [
                    {
                      "value" : 1.0,
                      "description" : "docFreq",
                      "details" : [ ]
                    },
                    {
                      "value" : 28.0,
                      "description" : "docCount",
                      "details" : [ ]
                    }
                  ]
                },
                {
                  "value" : 1.0143085,
                  "description" : "tfNorm, computed as (freq * (k1 + 1)) / (freq + k1 * (1 - b + b * fieldLength / avgFieldLength)) from:",
                  "details" : [
                    {
                      "value" : 1.0,
                      "description" : "termFreq=1.0",
                      "details" : [ ]
                    },
                    {
                      "value" : 1.2,
                      "description" : "parameter k1",
                      "details" : [ ]
                    },
                    {
                      "value" : 0.75,
                      "description" : "parameter b",
                      "details" : [ ]
                    },
                    {
                      "value" : 3.107143,
                      "description" : "avgFieldLength",
                      "details" : [ ]
                    },
                    {
                      "value" : 3.0,
                      "description" : "fieldLength",
                      "details" : [ ]
                    }
                  ]
                }
              ]
            }
          ]
        }
      }
    ]
  }
}
```