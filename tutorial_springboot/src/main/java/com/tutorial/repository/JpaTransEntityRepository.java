package com.tutorial.repository;

import com.tutorial.domain.JpaTransEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author jimmy
 * @date 2018/12/219:42
 */
@Repository
public interface JpaTransEntityRepository extends JpaRepository<JpaTransEntity, Long>, JpaSpecificationExecutor<JpaTransEntity> {
}
