# 需求:把一个字符转换为另一个字符,如'& => and'
https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-mapping-charfilter.html


 "char_filter" : {
                "charconvert" : {
                    "type" : "mapping",
                    "mappings_path":"char_filter_text.txt"
                }
            }