package com.tutorial.controller;

import com.tutorial.domain.Account;
import com.tutorial.domain.Book;
import com.tutorial.domain.User;
import com.tutorial.model.BookModel;
import com.tutorial.model.UserAccountModel;
import com.tutorial.model.UserModel;
import com.tutorial.model.mapper.BookModelMapper;
import com.tutorial.model.mapper.UserAccountMapper;
import com.tutorial.model.mapper.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@RestController
public class ModelMapperController {
    @Autowired
    UserModelMapper userModelMapper;
    @Autowired
    UserAccountMapper userAccountMapper;

    @RequestMapping("/bookMapper")
    public Object bookMapper() {
        Book book = new Book();
        book.setId(1L);
        book.setName("java");
        BookModel model = BookModelMapper.bookModelMapper.bookModel(book);
        System.out.println(model);
        return model;
    }


    @RequestMapping("/userMapper")
    public Object userMapper() {
        User user = new User();
        user.setId(1L);
        user.setUserName("jimmy");
        UserModel model = userModelMapper.userModel(user);
        System.out.println(model);
        return model;
    }


    @RequestMapping("/userAccountMapper")
    public Object userAccountMapper() {
        User user = new User();
        user.setId(1L);
        user.setUserName("jimmy");

        Account account = new Account();
        account.setId(2L);
        account.setEmail("xxxx@qq.com");
        UserAccountModel model = userAccountMapper.userAccountModel(user,account);
        System.out.println(model);
        return model;
    }
}
