package com.cassandra.repository;

import com.cassandra.domain.MacBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018-12-1822:12
 */
@Repository
public interface MacBookRepository extends JpaRepository<MacBook, Long>, JpaSpecificationExecutor<MacBook> {
}
