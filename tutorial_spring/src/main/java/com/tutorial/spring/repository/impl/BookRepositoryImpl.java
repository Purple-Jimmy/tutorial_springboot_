package com.tutorial.spring.repository.impl;

import com.tutorial.spring.entity.Book;
import com.tutorial.spring.repository.IBookRepository;

import java.util.List;

/**
 * @author jimmy
 * @date 2019-03-0520:53
 */
public class BookRepositoryImpl implements IBookRepository {


    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public Book queryById(Integer id) {
        return null;
    }

    @Override
    public List<Book> queryAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
