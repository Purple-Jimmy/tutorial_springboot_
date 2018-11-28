### IK配置同义词
https://www.jianshu.com/p/3e63f6739631
https://www.cnblogs.com/kaynet/p/6185859.html
https://www.cnblogs.com/NextNight/p/6837407.html
https://ruby-china.org/topics/21416  
http://blog.csdn.net/yusewuhen/article/details/50685685
http://blog.csdn.net/fighting_one_piece/article/details/77800921
http://blog.csdn.net/zhangbin666/article/details/73348160
http://blog.csdn.net/fenglailea/article/details/56845892
https://blog.csdn.net/wwd0501/article/details/80001790?utm_source=blogxgwz1

> 配置规则

1. Solr synonyms
2. WordNet synonyms

> 配置方式

1. 映射
阿迪、阿迪达斯和adidas的token将会转换为Adidas存入倒排索引中
```
阿迪, 阿迪达斯, adidas => Adidas
```
2. 对等  
当expand为true时,当出现以下任何一个token,三个token都会存入倒排索引中
当expand为false时,当出现以下任何一个token,第一个token也就是Nike会存入倒排索引中
```
Nike, 耐克, naike
```

#### 6.x配置同义词
1. 路径/etc/elasticsearch下新建synonyms.txt
2. 创建索引并setting如下
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/synonyms_index?pretty' -d '
{
  "settings": {
    "index": {
      "analysis": {
        "analyzer": {
          "synonym": {
            "tokenizer": "ik_max_word",
            "filter": ["synonym"]
          }
        },
        "filter": {
          "synonym": {
            "type": "synonym",
            "synonyms_path": "synonyms.txt",
            "ignore_case": true
          }
        }
      }
    }
  },
  "mappings": {
    "blog": {
      "properties": {
        "name": {
          "type": "text",
          "analyzer": "synonym"
        },
        "type": {
          "type": "text",
          "analyzer": "synonym"
        }
      }
    }
  }
}'
```
3. 插入数据
```
curl -H "Content-Type:application/json" -X POST 'localhost:9200/synonyms_index/blog?pretty' -d ' 
{
  "name":"西红柿" 
}'
```
4. 测试
```
curl -H "Content-Type:application/json" -XGET 'localhost:9200/synonyms_index/blog/_search?pretty' -d '
{
  "query": { 
        "match": { 
              "name": "番茄"
        }
  }
}'
```

curl -H "Content-Type:application/json" -XGET 'localhost:9200/synonyms_index/blog/_search?pretty' -d '
{
  "query": { 
        "match": { 
              "name": "番"
        }
  },
    "highlight" : {
          "fields" : {
              "name" : {}
          }
      }
}'



#### 动态更新同义词
curl -X DELETE 'localhost:9200/synonyms_index_1?pretty'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/synonyms_index_1?pretty' -d '
{
  "settings": {
    "index": {
      "analysis": {
        "analyzer": {
          "synonym": {
            "tokenizer": "ik_max_word",
            "filter": ["synonym"]
          }
        },
        "filter": {
          "synonym": {
            "type": "dynamic_synonym",
            "synonyms_path": "synonyms.txt",
            "ignore_case": true
          }
        }
      }
    }
  },
  "mappings": {
    "blog": {
      "properties": {
        "name": {
          "type": "text",
          "analyzer": "synonym"
        },
        "type": {
          "type": "text",
          "analyzer": "synonym"
        }
      }
    }
  }
}'

curl -H "Content-Type:application/json" -X POST 'localhost:9200/synonyms_index_1/blog?pretty' -d ' 
{
  "name":"西红柿" 
}'

curl -H "Content-Type:application/json" -XGET 'localhost:9200/synonyms_index_1/blog/_search?pretty' -d '
{
  "query": { 
        "match": { 
              "name": "番茄"
        }
  },
    "highlight" : {
          "fields" : {
              "name" : {}
          }
      }
}'


curl -H "Content-Type:application/json" -XGET 'localhost:9200/synonyms_index_1/_analyze?pretty' -d '
{
   "analyzer": "synonym",
    "text": "西红柿"
}'