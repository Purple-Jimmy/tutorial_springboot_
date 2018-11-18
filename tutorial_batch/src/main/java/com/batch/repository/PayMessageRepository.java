package com.batch.repository;

import com.batch.domain.PayMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018/11/1816:36
 */
@Repository
public interface PayMessageRepository extends JpaRepository<PayMessage, String>, JpaSpecificationExecutor<PayMessage> {
}
