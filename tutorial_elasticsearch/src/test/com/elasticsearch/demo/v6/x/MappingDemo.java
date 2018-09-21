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
     * ignoreAbove 指定字段索引和存储的长度,超过指定长度的部分被忽略
     * @throws IOException
     */
    @Test
    public void ignoreAboveDemo() throws IOException {

    }

}
