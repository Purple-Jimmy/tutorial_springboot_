package com.datasource.controller;

import com.datasource.domain.DataSource1;
import com.datasource.service.DataSource1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimmy
 * @date 2018/12/321:51
 */
@RestController
public class DataSourceController {
    @Autowired
    DataSource1Service dataSource1Service;


    @RequestMapping("/save")
    public String save(){
        DataSource1 dataSource1 = new DataSource1();
        dataSource1.setUserName("JImmy");
        dataSource1Service.saveOrUpdate(dataSource1);
        return "success";
    }
}
