package com.tutorial.controller;

import com.tutorial.domain.Book;
import com.tutorial.model.BookModel;
import com.tutorial.model.mapper.BookModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@RestController
public class ModelMapperController {

    @RequestMapping("/bookMapper")
    public void bookMapper(){
        BookModelMapper instance = new BookModelMapper() {
            @Override
            public BookModel bookModel(Book book) {
                return null;
            }

            @Override
            public List<BookModel> bookModelList(List<Book> bookList) {
                return null;
            }
        };


    }
}
