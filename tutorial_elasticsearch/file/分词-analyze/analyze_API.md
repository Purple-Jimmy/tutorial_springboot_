### Analysis 分析
> 分词和标准化的过程称为分析

> 分析的过程
1. 将文本分成适合于倒排索引的独立的词条 
2. 将这些词条统一化为标准格式以提高它们的"可搜索性"

> 分析器执行以上工作:实际上是将三个功能封装到了一个包里
1. 字符过滤器:字符串按顺序通过每个字符过滤器,可以用来去掉HTML,或者将 & 转化成 `and`
2. 分词器:符串被分词器分为单个的词条
3. Token过滤器:词条按顺序通过每个token过滤器,可能小写化,删除词条（a,and,the等无用词）,或者增加词条

> 内置分析器:标准分析器,简单分析器,空格分析器,语言分析器

> when使用分析器
1. 索引一个文档,它的全文域被分析成词条以用来创建倒排索引  
2. 在全文域搜索的时候,我们需要将查询字符串通过相同的分析过程保证我们搜索的词条格式与索引中的词条格式一致  
*查询一个全文域时会对查询字符串应用相同的分析器;查询一个精确值域时,不会分析查询字符串,而是搜索指定的精确值*

> 使用analyze API 测试分析器
standard标准分析器
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "standard",
  "text":     "Set the shape to semi-transparent by calling set_trans(5)"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "set",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "the",
      "start_offset" : 4,
      "end_offset" : 7,
      "type" : "<ALPHANUM>",
      "position" : 1
    },
    {
      "token" : "shape",
      "start_offset" : 8,
      "end_offset" : 13,
      "type" : "<ALPHANUM>",
      "position" : 2
    },
    {
      "token" : "to",
      "start_offset" : 14,
      "end_offset" : 16,
      "type" : "<ALPHANUM>",
      "position" : 3
    },
    {
      "token" : "semi",
      "start_offset" : 17,
      "end_offset" : 21,
      "type" : "<ALPHANUM>",
      "position" : 4
    },
    {
      "token" : "transparent",
      "start_offset" : 22,
      "end_offset" : 33,
      "type" : "<ALPHANUM>",
      "position" : 5
    },
    {
      "token" : "by",
      "start_offset" : 34,
      "end_offset" : 36,
      "type" : "<ALPHANUM>",
      "position" : 6
    },
    {
      "token" : "calling",
      "start_offset" : 37,
      "end_offset" : 44,
      "type" : "<ALPHANUM>",
      "position" : 7
    },
    {
      "token" : "set_trans",
      "start_offset" : 45,
      "end_offset" : 54,
      "type" : "<ALPHANUM>",
      "position" : 8
    },
    {
      "token" : "5",
      "start_offset" : 55,
      "end_offset" : 56,
      "type" : "<NUM>",
      "position" : 9
    }
  ]
}
```
*说明:  
token:实际存储到索引中的词条    
position:词条在原始文本中出现的位置    
start_offset和end_offset:字符在原始字符串中的位置*

english分析器：and,the被删除;分词器可以提取英语单词的词干
```
curl  -H "Content-Type:application/json" -XGET "localhost:9200/_analyze?pretty" -d'
{
  "analyzer": "english",
  "text":     "Set the shape to semi-transparent by calling set_trans(5)"
}
'
```
结果:
```
{
  "tokens" : [
    {
      "token" : "set",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "shape",
      "start_offset" : 8,
      "end_offset" : 13,
      "type" : "<ALPHANUM>",
      "position" : 2
    },
    {
      "token" : "semi",
      "start_offset" : 17,
      "end_offset" : 21,
      "type" : "<ALPHANUM>",
      "position" : 4
    },
    {
      "token" : "transpar",
      "start_offset" : 22,
      "end_offset" : 33,
      "type" : "<ALPHANUM>",
      "position" : 5
    },
    {
      "token" : "call",
      "start_offset" : 37,
      "end_offset" : 44,
      "type" : "<ALPHANUM>",
      "position" : 7
    },
    {
      "token" : "set_tran",
      "start_offset" : 45,
      "end_offset" : 54,
      "type" : "<ALPHANUM>",
      "position" : 8
    },
    {
      "token" : "5",
      "start_offset" : 55,
      "end_offset" : 56,
      "type" : "<NUM>",
      "position" : 9
    }
  ]
}
```
其中transparent,calling和set_trans被提取词干索引起来

