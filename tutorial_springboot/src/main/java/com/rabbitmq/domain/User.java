package com.rabbitmq.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jimmy. 2018/1/30  16:46
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 7147781130354030521L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(name="password",nullable = false,length = 500)
    private String password;

    /**
     * 编程语言中字符串一般都用String表示,但是数据库中varchar数值类型有长度限制,一旦需要大文本,则需要text数值类型
     */
    @Column(columnDefinition="text")
    private String userDesc;

    /**
     * 不映射成列
     */
    @Transient
    private Long inCome;

    /**
     * 不加注解也会映射成列
     */
    private String address;

    /**
     * @DatetimeFormat是将String转换成Date,一般前台给后台传值时用
     * @JsonFormat(pattern="yyyy-MM-dd")  将Date转换成String  一般后台传值给前台
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthDay;

}
