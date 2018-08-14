### Movie
> 数据结构设计

|Field       | Type    | Description
| :-: | :-: | :-: | 
id          | Long    | 影片id
name        | keyword | 片名
leadRole    | Text    | 主演
style       | Keyword | 影片类型:动作 恐怖 爱情
zone        | keyword | 中国香港 大陆 欧美
grade       | Float   | 评分
desc        | Text    | 简介
releaseDate | Date    | 上映日期

> 测试数据
```
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/1?pretty' -d ' 
{
  "id": "1",
  "name": "赌神1",
  "leadRole" : ["周润发","刘德华"],
  "style": "动作",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 9.6,
  "desc": "《赌神》是1987年上映的一部香港赌片。本片是由香港导演王晶执导，周润发、刘德华、张敏、王祖贤等领衔主演的电影。该片讲述了因赌术精湛闻名于世界的赌神高进。 由于意外，高进误入小刀设下的陷井，头部受重伤而失去记忆。高进手下与外敌勾结，企图取代高进的地位并谋夺其家产",
  "releaseDate": "1987-3-7"
}'
  
curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/2?pretty' -d ' 
{
  "id": "2",
  "name": "赌神2",
  "leadRole" : ["周润发","梁家辉"],
  "style": "动作",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 7.6,
  "desc": "《赌神2》是1994年上映的一部香港电影，该片由王晶导演，主要演员有周润发、吴倩莲、梁家辉、徐锦江等。该片讲述了一代赌神高进击败过无数对手后退出赌坛，与妻子隐居后，对手仇笑痴野心勃勃为迫高进重出江湖将其妻儿杀掉",
  "releaseDate": "1994-7-7"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/3?pretty' -d ' 
{
  "id": "3",
  "name": "精武英雄",
  "leadRole" : ["李连杰","中山忍","钱小豪","仓田保昭"],
  "style": "动作",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 9.8,
  "desc": "该片讲述的是陈真追查师父死因，他请医生验尸，证实师父霍元甲确系藤田刚下毒致死。与其展开了连场恶斗之后藤田刚挑战精武门，藤田刚诱使日本黑龙会总教头船越文夫向精武馆挑战",
  "releaseDate": "1994-12-22"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/4?pretty' -d ' 
{
  "id": "4",
  "name": "初恋这件小事",
  "leadRole" : ["马里奥·毛瑞尔","平采娜·乐维瑟派布恩"],
  "style": "爱情",
  "zone": {
      "country": "泰国",
      "city": ""
  },
  "grade": 8.8,
  "desc": "该片主要讲述的是长相平凡、家境平平的初中生小水，因喜欢上帅气的学长阿亮，从此生活发生天翻地覆变化的故事",
  "releaseDate": "2010-8-12"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/5?pretty' -d ' 
{
  "id": "5",
  "name": "大话西游1:月光宝盒",
  "leadRole" : ["周星驰","朱茵"],
  "style": "奇幻",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 8.8,
  "desc": "孙悟空（周星驰）护送唐三藏（罗家英）去西天取经路上，与牛魔王合谋欲杀害唐三藏，并偷走了月光宝盒，此举使观音萌生将其铲除心思，经唐三藏请求，孙悟空被判五百年后重新投胎做人赎其罪孽",
  "releaseDate": "1995-6-7"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/6?pretty' -d ' 
{
  "id": "6",
  "name": "大话西游2:大圣娶亲",
  "leadRole" : ["周星驰","朱茵"],
  "style": "奇幻",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 9.0,
  "desc": "《大话西游之大圣娶亲》（又名《大话西游之仙履奇缘》）",
  "releaseDate": "1997-7-8"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/7?pretty' -d ' 
{
  "id": "7",
  "name": "Titanic",
  "leadRole" : ["Leonardo Wilhelm DiCaprio","Kate Elizabeth Winslet"],
  "style": "灾难",
  "zone": {
      "country": "欧美",
      "city": ""
  },
  "grade": 9.9,
  "desc": "1912年4月10日，号称 “世界工业史上的奇迹”的豪华客轮泰坦尼克号开始了自己的处女航，从英国的南安普顿出发驶往美国纽约",
  "releaseDate": "1997-12-19"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/8?pretty' -d ' 
{
  "id": "8",
  "name": "中华赌侠",
  "leadRole" : ["古天乐","朱茵"],
  "style": "动作",
  "zone": {
      "country": "中国",
      "city": "香港"
  },
  "grade": 7.6,
  "desc": "影片讲述了香港赌王阿酷（古天乐）一度风光无限，以一手飞牌绝技名震江湖",
  "releaseDate": "2000-8-31"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/9?pretty' -d ' 
{
  "id": "9",
  "name": "羞羞的铁拳",
  "leadRole" : ["沈腾","马丽"],
  "style": "喜剧",
  "zone": {
      "country": "中国",
      "city": "大陆"
  },
  "grade": 7.8,
  "desc": "靠打假拳混日子的艾迪生（艾伦 饰），本来和正义感十足的体育记者马小（马丽 饰）是一对冤家，没想到因为一场意外的电击，男女身体互换",
  "releaseDate": "2017-10-1"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/10?pretty' -d ' 
{
  "id": "10",
  "name": "战狼2",
  "leadRole" : ["吴京","弗兰克·格里罗"],
  "style": "军事",
  "zone": {
      "country": "中国",
      "city": "大陆"
  },
  "grade": 7.8,
  "desc": "故事发生在非洲附近的大海上，主人公冷锋（吴京 饰）遭遇人生滑铁卢，被“开除军籍”，本想漂泊一生的他，正当他打算这么做的时候，一场突如其来的意外打破了他的计划",
  "releaseDate": "2017-10-1"
}'

curl -H "Content-Type:application/json" -X PUT 'localhost:9200/tutorial/movie/11?pretty' -d ' 
{
  "id": "11",
  "name": "钢铁侠1",
  "leadRole" : ["小罗伯特·唐尼"],
  "style": "科幻",
  "zone": {
      "country": "美国",
      "city": ""
  },
  "grade": 7.5,
  "desc": "斯塔克军火公司是美军在全球范围内第一大军火供应商，其新任掌门人托尼•斯塔克风流倜傥，天资聪颖",
  "releaseDate": "2008-10-9"
}'
```
















{
  "_index" : "tutorial",
  "_type" : "movie",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
