package com.tutorial.controller;

import com.tutorial.domain.JpaTransEntity;
import com.tutorial.service.JpaTransEntityService;
import com.utils.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimmy
 * @date 2018/12/219:46
 */
@RestController
public class JpaTrainsController {
    @Autowired
    JpaTransEntityService jpaTransEntityService;

    @RequestMapping("/jpaTest1")
    public String saveJpaTrainsEntity(){
        JpaTransEntity jpaTrainsEntity = new JpaTransEntity();
        jpaTrainsEntity.setName("Test1");
        jpaTransEntityService.saveOrUpdate(jpaTrainsEntity);
        return Constant.SUCCESS;
    }
}
