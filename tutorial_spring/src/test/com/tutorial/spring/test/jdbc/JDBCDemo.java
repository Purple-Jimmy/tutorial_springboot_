package com.tutorial.spring.test.jdbc;

import com.tutorial.spring.entity.Book;
import com.tutorial.spring.repository.IBookRepository;
import com.tutorial.spring.service.IBookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author jimmy
 * @date 2019-03-0520:58
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-jdbc.xml")
public class JDBCDemo {
    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private IBookService bookService;


    @Test
    public void saveTest(){
        Book book = new Book();
        book.setName("java");
        bookRepository.save(book);
    }

    @Test
    public void updateTest(){
        Book book = new Book();
        book.setId(2);
        book.setName("kkk");
        bookRepository.update(book);
    }

    @Test
    public void queryByIdTest(){
        Book book = bookRepository.queryById(1);
        System.out.println(book);
    }

    @Test
    public void queryAllTest(){
        List<Book> list = bookRepository.queryAll();
        System.out.println(list);
    }


    @Test
    public void deleteTest(){
        bookRepository.delete(1);
    }


    @Test
    public void transTest(){
        bookService.trans(2,3,100);
    }


}
