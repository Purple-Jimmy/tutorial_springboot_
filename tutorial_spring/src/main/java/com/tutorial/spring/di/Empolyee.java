package com.tutorial.spring.di;

/**
 * @author jimmy
 * @date 2019-02-2420:30
 */
public class Empolyee {

    private Integer id;
    private String name;

//    public Empolyee() {
//    }

    public Empolyee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Empolyee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
