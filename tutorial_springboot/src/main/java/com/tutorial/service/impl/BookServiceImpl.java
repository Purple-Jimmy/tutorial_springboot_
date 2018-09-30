package com.tutorial.service.impl;

import com.tutorial.domain.Book;
import com.tutorial.repository.BookRepository;
import com.tutorial.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jimmy. 2018/1/24  15:16
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookRepository bookRepository;


    @Override
    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }
}
