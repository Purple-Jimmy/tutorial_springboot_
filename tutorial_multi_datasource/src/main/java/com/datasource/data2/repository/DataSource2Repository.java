package com.datasource.data2.repository;

import com.datasource.domain.DataSource2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018/12/321:48
 */
@Repository
public interface DataSource2Repository extends JpaRepository<DataSource2, Long>, JpaSpecificationExecutor<DataSource2> {
}
