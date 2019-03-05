package com.tutorial.spring.repository;

import com.tutorial.spring.entity.Book;

import java.util.List;

/**
 * @author jimmy
 * @date 2019-03-0520:52
 */
public interface IBookRepository {

    void save(Book book);

    void update(Book book);

    Book queryById(Integer id);

    List<Book> queryAll();

    void delete(Integer id);


    void transOut(Integer id,Integer price);

    void transInt(Integer id,Integer price);
}
