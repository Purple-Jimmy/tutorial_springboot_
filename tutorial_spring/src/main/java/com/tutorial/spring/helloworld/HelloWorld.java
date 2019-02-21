package com.tutorial.spring.helloworld;

/**
 * @author jimmy
 * @date 2019-02-1920:21
 */
public class HelloWorld {

    private Integer id;
    private String name;

    public void sayHello(){
        System.out.println("Hello "+name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
