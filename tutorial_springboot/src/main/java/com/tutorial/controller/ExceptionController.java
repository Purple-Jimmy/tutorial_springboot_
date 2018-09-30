package com.tutorial.controller;

import com.tutorial.domain.Account;
import com.tutorial.util.CustomException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jimmy
 * @Date: 2018/9/29
 */
@RestController
public class ExceptionController {
    public static final Integer num = 10;

    /**
     * 自定义异常
     * @param id
     * @return
     * @throws CustomException
     */
    @RequestMapping("customException")
    public String customExceptionTest(@RequestParam("id")Integer id) throws CustomException {
        if(id>num){
            throw new CustomException("id不能大于10",1001);
        }else{
            System.out.println(id+"-===");
        }
        return "success";
    }

    /**
     * 参数绑定异常
     * @param account
     * @return
     */
    @RequestMapping(value = "bindException",method = RequestMethod.POST)
    public String bindException(@Validated @RequestBody Account account) {
        System.out.println(account);
        return "success";
    }
}
