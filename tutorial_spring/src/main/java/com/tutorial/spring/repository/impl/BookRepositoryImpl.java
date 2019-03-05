package com.tutorial.spring.repository.impl;

import com.tutorial.spring.entity.Book;
import com.tutorial.spring.repository.IBookRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author jimmy
 * @date 2019-03-0520:53
 */
public class BookRepositoryImpl implements IBookRepository {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name) VALUES (?)",book.getName());
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update("update book set name = ? where id = ?", book.getName(),book.getId());
    }

    @Override
    public Book queryById(Integer id) {
        Book book = jdbcTemplate.queryForObject(
                "select id, name from book where id = ?",
                new Object[]{1},
                (rs, rowNum) -> {
                    Book book1 = new Book();
                    book1.setId(rs.getInt("id"));
                    book1.setName(rs.getString("name"));
                    return book1;
                });
        return book;
    }

    @Override
    public List<Book> queryAll() {
        return this.jdbcTemplate.query( "select id, name from book", (rs, rowNum) -> {
            Book book1 = new Book();
            book1.setId(rs.getInt("id"));
            book1.setName(rs.getString("name"));
            return book1;
        });
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    @Override
    public void transOut(Integer id, Integer price) {
        jdbcTemplate.update("update book set price = price - ? where id = ?", price,id);
    }

    @Override
    public void transInt(Integer id, Integer price) {
        jdbcTemplate.update("update book set price = price + ? where id = ?", price,id);
    }
}
