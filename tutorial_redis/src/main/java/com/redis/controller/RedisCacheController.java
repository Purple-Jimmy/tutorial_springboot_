package com.redis.controller;

import com.redis.domain.City;
import com.redis.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimmy
 * @date 2019-01-2221:16
 */
@RestController
@Slf4j
public class RedisCacheController {
    @Autowired
    CityService cityService;

    @RequestMapping("/saveCity")
    public void saveCity(){
        City city = new City();
        city.setName("nanjing");
        cityService.saveOrUpdate(city);
    }

    @RequestMapping("/updateCity")
    public void updateCity(){
        City city = new City();
        city.setId(10L);
        city.setName("beijing");
        cityService.saveOrUpdate(city);
    }


    @RequestMapping("/findById")
    public void findById(){
        City city = cityService.findById(21L);
        log.info("city {}",city);
    }

    @RequestMapping("/deleteById")
    public void deleteById(){
       log.info("delete by id");
       cityService.delCity(19L);
    }

    @RequestMapping("/findAllCity")
    public void findAllCity(){
        log.info("findAllCity ....");
        cityService.findAll();
    }
}
