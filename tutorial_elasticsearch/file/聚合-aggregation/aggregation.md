### aggregation 聚合
一个聚合就是一些桶和指标的组合,一个聚合可以只有一个桶,或者一个指标或者每样一个,在桶中甚至可以有多个嵌套的桶.

#### Metric Aggregations 
```
简单的对过滤出来的数据集进行avg,max等操作,是一个单一的数值
```

#### Bucket Aggregations
```
将过滤出来的数据集按条件分成多个小数据集,然后Metrics会分别作用在这些小数据集上
```

#### Pipeline Aggregations
```
其实就是组合一堆的Aggregations对已经聚合出来的结果再做处理
```