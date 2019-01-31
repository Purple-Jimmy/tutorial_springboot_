package com.elasticsearch.jieba.demo;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.junit.Test;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/1/31
 */
public class JieBaDemo {

    @Test
    public void testDemo() {
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        String[] sentences =
                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
        for (String sentence : sentences) {
            //System.out.println(jiebaSegmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
            List<SegToken> list = jiebaSegmenter.process(sentence, JiebaSegmenter.SegMode.INDEX);
            for(SegToken segToken : list){
                String name = segToken.word.trim();
                System.out.println(name);
            }
        }
    }
}
