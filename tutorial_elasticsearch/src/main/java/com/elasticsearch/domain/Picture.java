package com.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: jimmy
 * @Date: 2018/9/7
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

   // @JestId
    private Integer id;

    /**
     * 图片名字
     */
    private String pictureName;

    /**
     * 图片描述
     */
    private String desc;


}
