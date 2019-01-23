package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;

import javax.batch.api.chunk.listener.ItemProcessListener;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Slf4j
public class IQYProcessorListener implements ItemProcessListener {
    @Override
    public void beforeProcess(Object o) throws Exception {

    }

    @Override
    public void afterProcess(Object o, Object o1) throws Exception {

    }

    @Override
    public void onProcessError(Object o, Exception e) throws Exception {

    }
}
