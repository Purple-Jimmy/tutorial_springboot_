package com.rabbitmq.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: jimmy
 * @Date: 2018/9/28
 */
@Data
public class Animal implements Serializable {

    private Long id;
    private String name;
    private String color;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
