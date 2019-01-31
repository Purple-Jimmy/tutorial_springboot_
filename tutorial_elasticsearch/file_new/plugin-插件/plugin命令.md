### plugin命令
 
## 插件分类
ElasticSearch中的插件是一个允许插入自定义功能的扩展。插件大致分为三类：

* java插件:这些插件只包含Jar文件,并且必须安装在集群中的每个节点上,安装后,每个节点必须重新启动,该插件才变得可见.比如分词器插件.
* 站点插件:这些插件包含了静态的Web内容,如JavaScript,HTML和CSS文件,可直接从Elasticsearch访问.
  站点插件可能只需要在一个节点上安装,并且不需要重新启动就能变得可见.站点插件的内容是通过一个类似的网址访问:HTTP://IP:9200/_plugin/插件名称
* 混合插件:混合插件同时包含jar文件和静态的Web内容

 
## 查看es安装的插件
```
[root@ibz bin]# ./elasticsearch-plugin list
analysis-icu
elasticsearch
ik
pinyin
```

## 安装插件
```
[root@ibz bin]# ./elasticsearch-plugin install analysis-icu
-> Downloading analysis-icu from elastic
[=================================================] 100%   
```

icu分词插件是个纯java插件,是Elasticsearch的分析器插件,使用国际化组件Unicode(ICU)提供丰富的处理Unicode编码的工具.
该查件对处理亚洲语言特别有用,还有大量对除英语外其他语言进行正确匹配和排序所必须的分词过滤器.

## 插件何时加载
plugins文件夹中的插件在Node实例化的时候被加载,构建代码如下:
```
this.pluginsService = new PluginsService(tmpSettings, environment.configFile(), environment.modulesFile(), environment.pluginsFile(), classpathPlugins);
```
其中environment.pluginsFile()的路径的内容就是xxx\elasticsearch\elasticsearch-6.0.0-rc2\plugins\analysis-icu