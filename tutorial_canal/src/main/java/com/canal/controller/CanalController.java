package com.canal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/5/29  11:58
 */
@RestController
public class CanalController {

    @RequestMapping("canal")
    public String canalTest(){
        return "canal";
    }
}
