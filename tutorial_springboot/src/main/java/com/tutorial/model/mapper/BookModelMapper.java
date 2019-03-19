package com.tutorial.model.mapper;

import com.tutorial.domain.Book;
import com.tutorial.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 接口映射
 * @Author: jimmy
 * @Date: 2019/3/19
 */
@Mapper
public interface BookModelMapper {
    /**
     * 默认配置
     */
    BookModelMapper bookModelMapper = Mappers.getMapper(BookModelMapper.class);

    BookModel bookModel(Book book);

    List<BookModel> bookModelList(List<Book> bookList);
}
