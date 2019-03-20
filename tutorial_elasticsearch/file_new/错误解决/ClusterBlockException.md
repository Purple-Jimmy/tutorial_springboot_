# ClusterBlockException

## 原因
这是由于ES新节点的数据目录data存储空间不足,导致从master主节点接收同步数据的时候失败,此时ES集群为了保护数,会自动把索引分片index置为只读read-only

## 解决
1. 磁盘扩容,可在配置文件中修改ES数据存储目录,重启ES
2. 放开索引只读设置,在服务器上通过curl工具发起PUT请求
```
curl -H "Content-Type:application/json" -X PUT "localhost:9200/_all/_settings" -d'
{
   "index.blocks.read_only_allow_delete": null
}
'
```