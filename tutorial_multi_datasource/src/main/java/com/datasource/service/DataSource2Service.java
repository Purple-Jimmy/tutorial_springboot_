package com.datasource.service;

import com.datasource.data2.repository.DataSource2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy
 * @date 2018/12/321:50
 */
@Service
public class DataSource2Service {
    @Autowired
    DataSource2Repository dataSource2Repository;
}
