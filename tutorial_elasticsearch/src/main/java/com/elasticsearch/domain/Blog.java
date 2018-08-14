package com.elasticsearch.domain;

import io.searchbox.annotations.JestId;
import lombok.Data;

import java.util.Date;

/**
 * Created by Jimmy. 2018/2/1  10:55
 * id          | long    | id
 * title       | text    | 标题
 * author      | text    | 作者
 * tag         | text    | 标签:life java spring javascript elasticsearch
 * zone        | text    | 中国香港 大陆 欧美
 * weekClick   | double  | 周访问量
 * monthClick  | double  | 月访问量
 * desc        | text    | 简介
 * releaseDate | date    | 发布日期
 * tagNum      | double  | 标签数
 * visible     | boolean | 是否可见
 */
@Data
public class Blog {
    @JestId
    private Long     id;
    private String   title;
    private String[] author;
    private String   tag;
    private Zone     zone;
    private Double   weekClick;
    private Double   monthClick;
    private String   desc;
    private Date     releaseDate;
    private Double   tagNum;
    private Boolean  visible;
}
