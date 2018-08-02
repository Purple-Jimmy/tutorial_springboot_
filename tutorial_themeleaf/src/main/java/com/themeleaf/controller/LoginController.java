package com.themeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jimmy
 * @date 2018/7/2908:42
 */
@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }
}
