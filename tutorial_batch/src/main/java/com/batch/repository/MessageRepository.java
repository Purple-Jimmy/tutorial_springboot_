package com.batch.repository;

import com.batch.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018/11/1816:34
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, String>, JpaSpecificationExecutor<Message> {
}
