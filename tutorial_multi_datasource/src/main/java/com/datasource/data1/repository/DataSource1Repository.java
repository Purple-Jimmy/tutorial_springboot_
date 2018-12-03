package com.datasource.data1.repository;

import com.datasource.domain.DataSource1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018/12/321:48
 */
@Repository
public interface DataSource1Repository extends JpaRepository<DataSource1, Long>, JpaSpecificationExecutor<DataSource1> {
}
