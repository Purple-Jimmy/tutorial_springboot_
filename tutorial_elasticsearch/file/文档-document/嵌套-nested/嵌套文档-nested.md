### nested 嵌套文档 https://www.elastic.co/guide/cn/elasticsearch/guide/current/nested-objects.html
#### 问题
字段类型设置成对象数组,则JSON格式的文档被处理成扁平式键值对的结构.



**检索的结果并不是只有嵌套对象,而是整个文档**

#### score 分数
```
默认情况下,根文档的分数是这些嵌套文档分数的平均值.可以通过设置 score_mode 参数来控制这个得分策略.
相关策略有avg(平均值),max(最大值),sum(加和)和none(直接返回 1.0 常数值分数).
```
如果 nested 查询放在一个布尔查询的 filter 子句中,其表现就像一个 nested 查询,只是 score_mode 参数不再生效.
因为它被用于不打分的查询中 — 只是符合或不符合条件,不必打分 — 那么 score_mode 就没有任何意义,因为根本就没有要打分的地方.