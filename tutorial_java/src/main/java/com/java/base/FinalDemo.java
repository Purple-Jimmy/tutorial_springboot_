package com.java.base;

/**
 * final在Java中是一个保留的关键字,可以声明成员变量、方法、类以及本地变量
 * 一旦将引用声明作final,将不能改变这个引用了,编译器会检查代码,如果试图将变量再次初始化的话,编译器会报编译错误
 *
 *
 * final修饰的类叫作final类
 * final类通常功能是完整的,它们不能被继承
 * @Author: jimmy
 * @Date: 2019/2/13
 */
public final class FinalDemo {
    /**
     * final变量是只读的
     */
    private final String COLOUR = "purple";

    /**
     * final修饰方法,子类无法重写
     * final方法比非final方法要快,因为在编译的时候已经静态绑定了,不需要在运行时再动态绑定
     * @return
     */
    public final String getColour(){
        return "purple";
    }

    /**
     * 好处:
     * 1. final关键字提高了性能,JVM和Java应用都会缓存final变量
     * 2. final变量可以安全的在多线程环境下进行共享,而不需要额外的同步开销
     * 3. 使用final关键字,JVM会对方法、变量及类进行优化
     *
     * 注意:
     * 1. 接口中声明的所有变量本身是final的
     * 2. final关键字可以用于成员变量、本地变量(在方法或者代码块中的变量称为本地变量)、方法以及类
     * 3. final成员变量必须在声明的时候初始化或者在构造器中初始化,否则就会报编译错误
     * 4. 在匿名类中所有变量都必须是final变量
     * 5. final关键字容易与finalize()方法搞混,后者是在Object类中定义的方法,是在垃圾回收之前被JVM调用的方法
     * 6. final和abstract这两个关键字是反相关的,final类就不可能是abstract的
     * 7. final方法在编译阶段绑定,称为静态绑定(statics binding)
     * 8. 对于集合对象声明为final指的是引用不能被更改,但是可以向其中增加,删除或者改变内容
     *    private final List Loans = new ArrayList();
     *    list.add(“home loan”);  //valid
     *    list.add("personal loan"); //valid
     *    loans = new Vector();  //not valid
     *
     *
     */
}


