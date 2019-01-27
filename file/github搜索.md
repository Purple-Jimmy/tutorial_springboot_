## github搜索
* 搜索仓库名字包含spring cloud的仓库
```
in:name spring cloud
```

* 查找描述的内容
```
in:descripton 关键词
```

* 查找README文件包含的特定内容
```
in:readme 关键词
```

* 明确搜索 star、fork 数大于多少的
```
查找star数大于3000的Spring Cloud 仓库
stars:>3000 spring cloud
```


* 查找指定更新时间的项目
```
pushed:>2019-01-03 spring cloud
```

* 查找仓库大小,单位k
```
size:>=5000 关键词   
```

* 明确搜索仓库的LICENSE
```
license:apache-2.0 spring cloud
```

* 明确搜索仓库的语言
```
language:java  关键词
```

* 查询指定个人或组织
```
user:joshlong
org:spring-cloud 
```

* 组合使用,空格隔开
```
user:joshlong language:java
```