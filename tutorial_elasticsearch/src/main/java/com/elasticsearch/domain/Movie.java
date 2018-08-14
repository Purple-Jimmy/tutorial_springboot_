package com.elasticsearch.domain;

import io.searchbox.annotations.JestId;
import lombok.Data;

import java.util.Arrays;

/**
 * Created by Jimmy. 2018/1/29  13:05
 * id          | Long    | 影片id
   name        | keyword | 片名
   leadRole    | Text    | 主演
   style       | Keyword | 影片类型:动作 恐怖 爱情
   zone        | keyword | 中国香港 大陆 欧美
   grade       | Float   | 评分
   desc        | Text    | 简介
   releaseDate | Date    | 上映日期
 */
@Data
public class Movie {
    @JestId
    private Long     id;
    private String   name;
    private String[] leadRole;
    private String   style;
    private Zone     zone;
    private Float    grade;
    private String   desc;
    private String   releaseDate;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leadRole=" + Arrays.toString(leadRole) +
                ", style='" + style + '\'' +
                ", zone=" + zone +
                ", grade=" + grade +
                ", desc='" + desc + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
