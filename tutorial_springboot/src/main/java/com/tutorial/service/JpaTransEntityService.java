package com.tutorial.service;

import com.tutorial.domain.JpaTransEntity;

/**
 * @author jimmy
 * @date 2018/12/219:43
 */
public interface JpaTransEntityService {

    public JpaTransEntity saveOrUpdate(JpaTransEntity jpaTrainsEntity);

    public void saveJpaAndBook();
}
