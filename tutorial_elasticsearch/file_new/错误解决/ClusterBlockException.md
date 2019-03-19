# ClusterBlockException
```
curl -H "Content-Type:application/json" -X GET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "standard",
  "text":     "心随云动"
}
'

curl -XPUT -H “Content-Type: application/json” 
http://127.0.0.1:9200/_all/_settings -d ‘{“index.blocks.read_only_allow_delete”: null}’


curl -H "Content-Type:application/json" -X PUT "localhost:9200/_all/_settings" -d'
{
  "index.blocks.read_only_allow_delete": null
}
'


```