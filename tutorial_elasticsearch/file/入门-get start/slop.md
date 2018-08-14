### slop
query string搜索文本中的几个term,最多可以移动几次去尝试跟一个doc匹配上,这个移动次数就是slop.

如果我们指定了slop,那么就允许java spark进行移动,来尝试与doc进行匹配  

java		is		very		good		spark		is  

java		spark  
java		-->		spark  
java				-->			spark  
java							-->			spark  

这里的slop就是3.因为java spark这个短语spark移动了3次就可以跟一个doc匹配上了
