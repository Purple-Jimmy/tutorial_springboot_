```
curl -X PUT "localhost:9200/city_index" -H 'Content-Type: application/json' -d'
{
	"mappings": {
		"tutorial_mapping": {
			"properties": {
				"id": {
					"type": "keyword"
				},
				"name": {
					"type": "text"
				}
			}
		}
	}
}
'
```