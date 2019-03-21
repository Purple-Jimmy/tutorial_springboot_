https://github.com/medcl/elasticsearch-analysis-pinyin/issues/182

 "highlight": {
      "boundary_chars":".,!? \t\n，。！？",
      "pre_tags" : ["<font color='red'>"],
      "post_tags" : ["</font>"],
      "fields": {
        "name" : {
          "number_of_fragments" : 0
        },
        "name.pinyin" : {
          "number_of_fragments" : 0
        },
        "address" : {
          "number_of_fragments" : 0
        }
      }
