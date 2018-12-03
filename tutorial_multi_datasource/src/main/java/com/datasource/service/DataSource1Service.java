package com.datasource.service;

import com.datasource.domain.DataSource1;
import com.datasource.data1.repository.DataSource1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy
 * @date 2018/12/321:49
 */
@Service
public class DataSource1Service {
    @Autowired
    DataSource1Repository dataSource1Repository;


    public void saveOrUpdate(DataSource1 dataSource1){
        dataSource1Repository.save(dataSource1);
    }

}
