package com.tutorial.spring.service.impl;

import com.tutorial.spring.repository.IBookRepository;
import com.tutorial.spring.service.IBookService;

/**
 * @author jimmy
 * @date 2019-03-0522:41
 */
public class BookServiceImpl implements IBookService {
    private IBookRepository bookRepository;


    @Override
    public void trans(Integer inId, Integer outId, Integer price) {
        bookRepository.transOut(outId,price);
        //抛出一个异常
        int a = 1/0;
        bookRepository.transInt(inId,price);

    }


    public IBookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
