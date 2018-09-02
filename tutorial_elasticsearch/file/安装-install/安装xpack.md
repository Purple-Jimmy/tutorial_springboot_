https://blog.csdn.net/zou79189747/article/details/81164576

http://blog.51cto.com/nginxs/2083985


curl -H "Content-Type:application/json" -XPOST  http://192.168.36.61:9200/_xpack/license/start_trial?acknowledge=true

curl -X DELETE 'localhost:9200/_xpack/license/start_trial?acknowledge=true'