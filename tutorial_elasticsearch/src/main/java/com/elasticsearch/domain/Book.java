package com.elasticsearch.domain;

import io.searchbox.annotations.JestId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2018/9/7
 */
@Data
@Accessors(chain = true)
@Builder
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @JestId
    private Long id;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String[] author;

    /**
     * 价格
     */
    private Float price;


    /**
     * 发布时间
     */
    private Date releaseDate;

    /**
     * 出版区域
     */
    private Zone zone;

    /**
     * 图片
     */
    private List<Picture> pictures;
}
