package com.redis.service;

import com.redis.domain.City;
import com.redis.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author jimmy
 * @date 2019-01-2221:13
 *一般用于更新和插入操作，每次都会请求db
 * 通过key去redis中进行操作。
 * 1. 如果key存在，更新内容
 * 2. 如果key不存在，插入内容
 @CachePut(value = "user", key = "'user'.concat(#user.id.toString())"
 根据key删除缓存中的数据。allEntries=true表示删除缓存中的所有数据
 @CacheEvict(value = "user", key = "'user'.concat(#id.toString())")
 */
@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    //@CachePut(key = "\"city_\" + #city.id")
    public void saveOrUpdate(City city){
        cityRepository.save(city);
    }


    public void delCity(Long id){
        cityRepository.deleteById(id);
    }

    /**
     * 如果key不存在,查询db,并将结果更新到缓存中
     * 如果key存在,直接查询缓存中的数据
     * @param id
     * @return
     */
    @Cacheable(value = "city", key = "'city'.concat(#id.toString())")
    public City findById(Long id){
        City city = cityRepository.getOne(id);
        System.out.println(city);
        return city;
    }
}
