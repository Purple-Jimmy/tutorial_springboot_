package com.tutorial.service;

import com.tutorial.domain.Account;

/**
 * Created by Jimmy. 2018/3/26  15:15
 */
public interface AccountService {

    public void saveAccount(Account account);

    public Account queryById(Long id);
}
