package com.tutorial.controller;

import com.tutorial.domain.Account;
import com.tutorial.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/9/30
 */
@RestController
@Slf4j
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "account",method = RequestMethod.POST)
    public void saveAccount(@RequestParam("userName") String userName){
        log.info("account controller save");
      /*  Account account = new Account().setUserName("jimmy")
                                       .setEmail("qq@com")
                                       .setPassword("123456")
                                       .setToken("sdd");*/
        Account account = Account.builder().userName("jack").email("qq@com")
                                 .password("123456").token("sdd").build();
        accountService.saveAccount(account);
    }
}
