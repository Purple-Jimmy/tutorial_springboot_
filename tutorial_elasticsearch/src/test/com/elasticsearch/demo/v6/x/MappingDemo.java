package com.elasticsearch.demo.v6.x;

import com.elasticsearch.ElasticsearchStart;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.DeleteIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author: jimmy
 * @Date: 2018/9/21
 * https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-params.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchStart.class)
public class MappingDemo {

    @Autowired
    private JestClient jestClient;

    /**
     * 删除索引
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndex.Builder index = new DeleteIndex.Builder("tutorial");
        JestResult jestResult = jestClient.execute(index.build());
        System.out.println(jestResult);
    }

    /**
     * analyzer 分析器,对索引和分析都有效
     * @throws IOException
     */
    @Test
    public void analyzerDemo() throws IOException {

    }

    /**
     * analyzer 解析前的标准化配置,比如所有字符转化为小写
     * @throws IOException
     */
    @Test
    public void normalizerDemo() throws IOException {

    }

    /**
     * boost 建议查询时指定boost值
     * @throws IOException
     */
    @Test
    public void boostDemo() throws IOException {

    }

    /**
     * coerce 清楚脏数据,默认true
     * @throws IOException
     */
    @Test
    public void coerceDemo() throws IOException {

    }

    /**
     * copy-to 配置自定义的_all字段,多个字段合并成一个字段
     * @throws IOException
     */
    @Test
    public void copyToDemo() throws IOException {

    }

    /**
     * doc_values 加快排序、聚合操作,默认开始,确定不需要聚合或者排序的字段可以关闭false
     * @throws IOException
     */
    @Test
    public void docValuesDemo() throws IOException {

    }

    /**
     * dynamic 检测新发现的字段
     * true(默认):新发现的字段添加到映射中
     * false:新发现的字段被忽略,必须显示指定字段
     * strict:如果检测到新字段,会引发异常并拒绝文档
     * @throws IOException
     */
    @Test
    public void dynamicDemo() throws IOException {

    }

    /**
     * elasticsearch默认索引所有字段
     * false:不索引即不可搜,但可在_source中获取
     * @throws IOException
     */
    @Test
    public void enabledDemo() throws IOException {

    }

    /**
     * format
     * epoch_millis:毫秒数
     * epoch_second:秒数
     * @throws IOException
     */
    @Test
    public void formatDemo() throws IOException {

    }

    /**
     * ignoreAbove 指定字段索引和存储的长度最大值,超过最大值的会被忽略
     * @throws IOException
     */
    @Test
    public void ignoreAboveDemo() throws IOException {

    }

    /**
     * ignore_malformed 忽略不规则数据.例如userId字段,有可能写整数,有可能写邮箱.
     * 如果给一个字段索引不合适的数据类型就会发生异常,导致整个文档索引失败.
     * ignore_malformed默认false.设置true,忽略抛出的异常,出异常的字段不被索引,其他字段索引正常
     * @throws IOException
     */
    @Test
    public void ignoreMalFormedDemo() throws IOException {

    }

    /**
     * index_options 控制哪些信息添加到倒排索引,用于搜索和突出显示目的
     * @throws IOException
     */
    @Test
    public void indexOptionsDemo() throws IOException {

    }

    /**
     * index 指定字段是否索引,不索引也就不可搜索
     * true false
     * @throws IOException
     */
    @Test
    public void indexDemo() throws IOException {

    }

    /**
     * fields 可以让同一文本有多种不同的索引方式,比如一个String类型的字段,
     * 可以使用text类型做全文检索,使用keyword类型做聚合和排序
     * true false
     * @throws IOException
     */
    @Test
    public void fieldsDemo() throws IOException {

    }


}
