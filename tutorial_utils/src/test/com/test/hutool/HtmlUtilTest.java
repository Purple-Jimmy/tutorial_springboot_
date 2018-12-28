package com.test.hutool;

import cn.hutool.http.HtmlUtil;
import org.junit.Test;

/**
 * @Author: jimmy
 * @Date: 2018/12/28
 */
public class HtmlUtilTest {

    /**
     * 清除所有html标签
     */
    @Test
    public void test1(){
        String str = "<font color=\"#127s\">hello</font>";
        System.out.println(HtmlUtil.cleanHtmlTag(str));
    }
}
