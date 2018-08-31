package com.design.pattern.test;

import com.design.pattern.proxy.*;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author jimmy
 * @date 2018/8/2614:31
 */
public class ProxyPatternDemo {
    /**
     * 静态代理
     */
    @Test
    public void jingTaiProxyTest(){
        RealMovie realMovie = new RealMovie();
        Cinema cinema = new Cinema(realMovie);
        cinema.play();
    }

    /**
     * 动态代理
     * public static Object newProxyInstance(ClassLoader loader,
     *                                           Class<?>[] interfaces,
     *                                           InvocationHandler h)
     *
     * loader     类加载器
     * interfaces 代码要用来代理的接口
     * h          一个InvocationHandler 对象
     */
    @Test
    public void dongTaiProxyTest(){
        MaoTaiJiu maoTaiJiu = new MaoTaiJiu();
        //代理对象
        InvocationHandler maoTai = new GuiTai(maoTaiJiu);

        /**
         * Proxy.newProxyInstance()返回
         */
        SellWine sellMaoTai = (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),
                MaoTaiJiu.class.getInterfaces(), maoTai);

        sellMaoTai.maiJiu();
        //柜台也卖五粮液
        WuLiangYe wuLiangYeJiu = new WuLiangYe();
        InvocationHandler wuLiangYe = new GuiTai(wuLiangYeJiu);
        SellWine sellWuLiangYe = (SellWine) Proxy.newProxyInstance(WuLiangYe.class.getClassLoader(),WuLiangYe.class.getInterfaces(),wuLiangYe);
        sellWuLiangYe.maiJiu();

        //柜台还卖烟
        SuYan suYan = new SuYan();
        InvocationHandler yan = new GuiTai(suYan);
        SellYan sellYan = (SellYan) Proxy.newProxyInstance(SuYan.class.getClassLoader(),SuYan.class.getInterfaces(),yan);
        sellYan.maiYan();
    }

    @Test
    public void test(){
        Class<?>[] clazz = MaoTaiJiu.class.getInterfaces();
        System.out.println(clazz.getClass().getPackage());
    }
}
