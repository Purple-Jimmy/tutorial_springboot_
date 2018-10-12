### aliases API
 1. 创建索引别名
 ```
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "my_index",
        "alias": "my_index_alias"
      }
    }
  ]
}
```

2. 聚合多个索引
索引my_index_1 和 my_index_2 创建一个别名my_index_alias，这样对my_index_alias的操作(仅限读操作)，会操作my_index_1和my_index_2，类似于聚合了my_index_1和my_index_2.我们是不能对my_index_alias进行写操作，当有多个索引时alias，不能区分到底操作哪一个
```
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "my_index_1",
        "alias": "my_index_alias"
      }
    },
    {
      "add": {
        "index": "my_index_2",
        "alias": "my_index_alias"
      }
    }
  ]
}

GET /my_index_alias/_search
{
}
```

3. 过滤数据
```
POST /_aliases
{
  "actions": [
    {
      "add": {
        "index": "my_index",
        "alias": "my_index__teamA_alias",
        "filter":{
            "term":{
                "team":"teamA"
            }
        }
      }
    },
    {
      "add": {
        "index": "my_index",
        "alias": "my_index__teamB_alias",
        "filter":{
            "term":{
                "team":"teamB"
            }
        }
      }
    },
    {
      "add": {
        "index": "my_index",
        "alias": "my_index__team_alias"
      }
    }
  ]
}

GET /my_index__teamA_alias/_search 只能看到teamA的数据
GET /my_index__teamB_alias/_search 只能看到teamB的数据
GET /my_index__team_alias/_search 既能看到teamA的，也能看到teamB的数据
```