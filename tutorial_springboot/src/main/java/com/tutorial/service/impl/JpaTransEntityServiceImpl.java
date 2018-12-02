package com.tutorial.service.impl;

import com.tutorial.domain.JpaTransEntity;
import com.tutorial.repository.BookRepository;
import com.tutorial.repository.JpaTransEntityRepository;
import com.tutorial.service.JpaTransEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jimmy
 * @date 2018/12/219:43
 */
@Service
public class JpaTransEntityServiceImpl implements JpaTransEntityService {
    @Autowired
    JpaTransEntityRepository jpaTransEntityRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JpaTransEntity saveOrUpdate(JpaTransEntity jpaTrainsEntity) {
        jpaTransEntityRepository.save(jpaTrainsEntity);
        int i = 1/0;
        return jpaTrainsEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJpaAndBook() {
        JpaTransEntity jpaTrainsEntity = new JpaTransEntity();
        jpaTrainsEntity.setName("add");
        jpaTransEntityRepository.save(jpaTrainsEntity);

        //int i = 1/0;
    }
}
