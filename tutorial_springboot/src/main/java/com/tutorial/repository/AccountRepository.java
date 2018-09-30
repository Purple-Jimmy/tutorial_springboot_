package com.tutorial.repository;

import com.tutorial.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Jimmy. 2018/3/26  15:14
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>,JpaSpecificationExecutor<Account> {
}
