package com.design.pattern.proxy;

/**
 * @author jimmy
 * @date 2018/8/2614:27
 */
public class Cinema implements Movie{
    RealMovie movie;

    public Cinema(RealMovie realMovie){
        this.movie = realMovie;
    }

    @Override
    public void play() {
        guanggao(true);

        movie.play();

        guanggao(false);
    }

    public void guanggao(boolean isStart){
        if ( isStart ) {
            System.out.println("电影马上开始了，爆米花、可乐、口香糖9.8折，快来买啊！");
        } else {
            System.out.println("电影马上结束了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
        }
    }
}
