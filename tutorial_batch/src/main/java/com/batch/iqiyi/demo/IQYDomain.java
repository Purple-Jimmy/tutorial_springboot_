package com.batch.iqiyi.demo;

import lombok.Data;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Data
public class IQYDomain {
    private String id;
    private String name;
    private String imgUrl;
    private Float grade;
    private Integer playCounts;
    private String extInfo;
    private String leadingRole;
    private String director;
    private String programType;
    private String years;
    private String templateIds;
    private String lastModifyDate;
    private String verticalPosterAddr;
    private Integer programTotalCount;
}
