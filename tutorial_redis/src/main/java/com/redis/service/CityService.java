package com.redis.service;

import com.redis.domain.City;
import com.redis.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * value 缓存位置名称,不能为空
     * @param city
     */
    @CachePut(value = "city",key = "'city' + #city.id",condition = "#city.id != null")
   // @CachePut(value = "city",key = "'city'.concat(#city.id.toString())",condition = "#city.id != null")
    public City saveOrUpdate(City city){
        city = cityRepository.save(city);
        return city;
    }


    /**
     * 根据key删除缓存中的数据
     * allEntries=true表示删除缓存中的所有数据
     */
    //@CacheEvict(value = "city", key = "'city'.concat(#id.toString())")
    @CacheEvict(value = "city",key = "'city' + #id")
    public void delCity(Long id){
        if(cityRepository.existsById(id)){
            cityRepository.deleteById(id);
        }
    }

    /**
     * 如果key不存在,查询db,并将结果更新到缓存中
     * 如果key存在,直接查询缓存中的数据
     * @param id
     * @return
     */
    //@Cacheable(value = "city", key = "'city'.concat(#id.toString())")
    @Cacheable(value = "city",key = "'city' + #id")
    public City findById(Long id){
        Optional<City> city = cityRepository.findById(id);
        return city.get();
    }


    @Cacheable(value = "city", key = "T(com.utils.common.Constant).CITY_CACHE_KEY")
    public List<City> findAll(){
        return cityRepository.findAll();
    }
}
