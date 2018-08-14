### Deep paging

```
elasticsearch分页查询使用from和size.数据大于50000的时候会出现深分页问题.
比如总共有60000条数据,每个shard上分20000条,每页10条数据,搜索第1000页,实际上要拿到的是10001-10010,该怎么拿呢? 
请求首先可能是打到一个不包含这个index的shard的node上,这个node就是一个coordinate node(协同工作),
这个coordinate node就会将搜索请求转发到index的三个shard所在的node上去.
要搜索60000条数据中的第1000页,实际上每个shard都要将内部的20000条数据中的第10001-10010条数据拿出来,不是10条,
是10010条数据,3个shard每个shard都返回10010条数据(在每个shard内部已经排序)给coordinate node,
coordinate node会收到总共30030条数据,然后取到排位最高的前10条数据,其实就是我们要的最后的第1000页的10条数据
```
性能问题  
解决方案:scroll


### 分页与scroll