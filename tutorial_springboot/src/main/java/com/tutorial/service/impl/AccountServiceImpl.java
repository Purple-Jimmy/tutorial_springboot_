package com.tutorial.service.impl;

import com.tutorial.domain.Account;
import com.tutorial.repository.AccountRepository;
import com.tutorial.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jimmy. 2018/3/26  15:15
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void saveAccount(Account account) {
        log.info("save account");
        log.error("save account error");
        accountRepository.save(account);
    }

    @Override
    public Account queryById(Long id) {
        return accountRepository.getOne(id);
    }
}
