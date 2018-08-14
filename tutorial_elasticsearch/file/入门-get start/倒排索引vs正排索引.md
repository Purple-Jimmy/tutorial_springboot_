假设有如下文档：
```
doc1: jimmy 27
doc2: lucy  20
```


### 倒排索引-词语所属的文档关系:搜索
适用于快速的全文搜索.一个倒排索引由文档中所有不重复词的列表构成,对于其中每个词,有一个包含它的文档列表

| 词语  | 文档 |
| :-:  | :-: | 
| jimmy| doc1 | 
| 27   | doc1 | 
| lucy | doc2 | 
| 20   | doc2 | 

> 倒排索引结构
1. 包含这个关键词的document list
2. 包含这个关键词的所有document的数量:IDF（inverse document frequency）
3. 这个关键词在每个document中出现的次数:TF（term frequency）
4. 这个关键词在这个document中的次序
5. 每个document的长度:length norm
6. 包含这个关键词的所有document的平均长度

> 倒排索引好处
1. 不需要锁,提升并发能力,避免锁的问题
2. 数据不变,一直保存在os cache中,只要cache内存足够
3. filter cache一直驻留在内,因为数据不变
4. 可以压缩,节省cpu和io开销

> 倒排索引坏处
1. 每次都要重新构建整个索引


### 正排索引-文档和词语的包含关系:排序,聚合
|文档  | 姓名  | 年龄|
| :-: | :-:  | :-: | 
| doc1| jimmy | 27 |
| doc2| lucy  | 20 |

