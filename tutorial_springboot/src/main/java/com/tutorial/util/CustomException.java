package com.tutorial.util;

import lombok.Data;

/**
 * @Author: jimmy
 * @Date: 2018/9/29
 * 自定义异常
 */
@Data
public class CustomException extends Exception {
    private Integer errorCode;

    public CustomException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
