package com.tutorial.controller;

import com.tutorial.domain.Account;
import com.tutorial.util.CustomException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/9/29
 */
@RestController
public class ExceptionController {
    public static final Integer num = 10;

    @RequestMapping("customException")
    public String customExceptionTest(@RequestParam("id")Integer id) throws CustomException {
        if(id>num){
            throw new CustomException("id不能大于10",1001);
        }else{
            System.out.println(id+"-===");
        }
        return "success";
    }


    @RequestMapping("bindException")
    public String bindException(@Validated Account account) {
        System.out.println(account);
        return "success";
    }
}
