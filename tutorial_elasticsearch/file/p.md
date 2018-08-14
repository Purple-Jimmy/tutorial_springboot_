curl -H 'Content-Type: application/json' -XPUT "localhost:9200/content" -d ' 
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 1,
    "analysis": {
      "analyzer": {
        "ngram_analyzer": {
          "tokenizer": "my_ngram_tokenizer"
        },
        "pinyin_analyzer": {
          "tokenizer": "my_pinyin_tokenizer"
        },
        "my_ngram_analyzer": {
          "tokenizer": "my_pattern_tokenizer",
          "filter": "my_ngram_filter"
        },
        "my_pinyin_analyzer": {
          "tokenizer": "my_pattern_tokenizer",
          "filter": "my_pinyin_filter"
        }
      },
      "tokenizer": {
        "my_pattern_tokenizer": {
          "type": "pattern",
          "pattern": "\\|"
        },
        "my_ngram_tokenizer": {
          "type": "nGram",
          "min_gram": "1",
          "max_gram": "10"
        },
        "my_pinyin_tokenizer": {
          "type": "pinyin",
          "keep_first_letter": true,
          "keep_separate_first_letter": false,
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_none_chinese": true,
          "keep_none_chinese_together": true,
          "none_chinese_pinyin_tokenize": false,
          "keep_original": true,
          "keep_none_chinese_in_first_letter": false,
          "keep_none_chinese_in_joined_full_pinyin": true,
          "lowercase": true,
          "remove_duplicated_term": true
        }
      },
      "filter": {
        "my_ngram_filter": {
          "type": "nGram",
          "min_gram": "1",
          "max_gram": "10"
        },
        "my_pinyin_filter": {
          "type": "pinyin",
          "keep_first_letter": true,
          "keep_separate_first_letter": false,
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_none_chinese": true,
          "keep_none_chinese_together": true,
          "none_chinese_pinyin_tokenize": false,
          "keep_original": true,
          "keep_none_chinese_in_first_letter": false,
          "keep_none_chinese_in_joined_full_pinyin": true,
          "lowercase": true,
          "remove_duplicated_term": true
        }
      }
    }
  },
  "mappings": {
    "program_series": {
      "properties": {
        "id": {
          "type": "keyword"
        },
        "name": {
          "type": "text",
          "analyzer": "ngram_analyzer",
          "fields": {
            "pinyinname": {
              "type": "text",
              "analyzer": "pinyin_analyzer"
            }
          }
        },
        "imgUrl": {
          "type": "keyword",
          "index": false
        },
        "bitrate": {
          "type": "keyword"
        },
        "action": {
          "type": "keyword"
        },
        "actionUrl": {
          "type": "keyword",
          "index": false
        },
        "grade": {
          "type": "double"
        },
        "playCounts": {
          "type": "long"
        },
        "ppvId": {
          "type": "keyword"
        },
        "searchType": {
          "type": "keyword"
        },
        "leadingRole": {
          "type": "text",
          "analyzer": "my_ngram_analyzer",
          "fields": {
            "pinyinleadingRole": {
              "type": "text",
              "analyzer": "my_pinyin_analyzer"
            }
          }
        },
        "director": {
          "type": "text",
          "analyzer": "my_ngram_analyzer",
          "fields": {
            "pinyindirector": {
              "type": "text",
              "analyzer": "my_pinyin_analyzer"
            }
          }
        },
        "programType": {
          "type": "keyword"
        },
        "programClass": {
          "type": "keyword"
        },
        "tag": {
          "type": "keyword"
        },
        "year": {
          "type": "keyword"
        },
        "language": {
          "type": "keyword"
        },
        "zone": {
          "type": "keyword"
        },
        "channelProgramStart": {
          "type": "date",
          "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
        },
        "channelProgramEnd": {
          "type": "date",
          "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
        },
        "epgGroupIds": {
          "type": "long"
        },
        "cpCode": {
          "type": "keyword"
        },
        "verticalPosterAddr": {
          "type": "keyword",
          "index": false
        },
        "tvChannelLogo": {
          "type": "keyword",
          "index": false
        },
        "phoneChannelLogo": {
          "type": "keyword",
          "index": false
        },
        "isTidbits": {
          "type": "integer"
        },
        "titleLength": {
          "type": "integer"
        },
        "weekClickRate": {
          "type": "long"
        },
        "monthClickRate": {
          "type": "long"
        },
        "programTotalCount": {
          "type": "integer"
        },
        "programPriority": {
          "type": "integer"
        },
        "weight": {
          "type": "integer"
        },
        "bitrateSort": {
          "type": "integer"
        },
        "channelUuid": {
          "type": "keyword"
        },
        "intent": {
          "type": "keyword"
        },
        "lastModifyDate": {
          "type": "date",
          "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
        }
      }
    }
  }
}
'