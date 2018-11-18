package com.batch.exception;

import lombok.Data;

/**
 * @author jimmy
 * @date 2018/11/1816:14
 */
@Data
public class MoneyNotEnoughException extends RuntimeException {
    private String msg;

    public MoneyNotEnoughException(){
        super();
    }

    public MoneyNotEnoughException(String msg){
        super(msg);
        this.msg = msg;
    }

}
