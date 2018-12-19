package com.cassandra.service;

import com.cassandra.domain.MacBook;
import com.cassandra.repository.MacBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy
 * @date 2018-12-1822:13
 */
@Service
public class MacBookService {
    @Autowired
    MacBookRepository macBookRepository;

    public void saveOrUpdate(MacBook macBook){
        macBookRepository.save(macBook);
    }
}
