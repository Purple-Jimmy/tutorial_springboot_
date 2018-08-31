package com.design.pattern.proxy;

/**
 * @author jimmy
 * @date 2018/8/2614:26
 */
public class RealMovie implements Movie {

    @Override
    public void play() {
        System.out.println("正在播放古惑仔");
    }
}
