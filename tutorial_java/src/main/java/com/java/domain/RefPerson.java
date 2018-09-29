package com.java.domain;

import lombok.Data;

/**
 * @author jimmy
 * @date 2018/9/2822:31
 * 反射
 */
@Data
public class RefPerson {

    private Integer id;
    private String name;

    public String showNaame(){
        return this.name;
    }

    @Override
    public String toString() {
        return "RefPerson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
