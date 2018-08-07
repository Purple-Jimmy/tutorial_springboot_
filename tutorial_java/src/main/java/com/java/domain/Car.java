package com.java.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: jimmy
 * @Date: 2018/8/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    private static final long serialVersionUID = 4403335633735558138L;

    private Long id;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 价格
     */
    private Long price;
}
