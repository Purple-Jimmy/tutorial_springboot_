package com.tutorial.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@Data
public class UserModel {
    private Long id;

    private String name;

    private String password;

    private String userDesc;

    private Long inCome;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date birthDay;

    private String test1;
}
