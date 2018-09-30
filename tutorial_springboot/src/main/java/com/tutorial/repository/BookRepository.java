package com.tutorial.repository;

import com.tutorial.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Created by Jimmy. 2018/1/24  15:12
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>,JpaSpecificationExecutor<Book> {
}
