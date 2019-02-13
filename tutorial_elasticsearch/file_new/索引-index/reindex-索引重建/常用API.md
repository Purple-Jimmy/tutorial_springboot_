* 查看所有索引的别名 curl -XGET "http://localhost:9200/_aliases"

* 查询某个index拥有的别名 curl -XGET "http://localhost:9200/tutorial_index_v2/_alias/*"

* 查某个别名映射的所有index curl -XGET "http://localhost:9200/*/_alias/tutorial_index"