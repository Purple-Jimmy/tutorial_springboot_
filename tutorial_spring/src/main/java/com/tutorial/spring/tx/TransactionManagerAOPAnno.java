package com.tutorial.spring.tx;


/*import org.aspectj.lang.JoinPoint;*/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 模拟事务管理器
 * @author jimmy
 * @date 2019-02-2823:40
 */
@Component
//配置一个切面
@Aspect
public class TransactionManagerAOPAnno {
    @Pointcut("execution(* com.tutorial.spring.service.impl.*ServiceImpl.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void begin(){
        System.out.println("开启事务");
    }

    @AfterReturning("pointCut()")
    public void commit(){
        System.out.println("提交事务");
    }

    @AfterThrowing(value = "pointCut()",throwing = "ex")
    public void rollbackAOP(Throwable ex){
        System.out.println("回滚事务,异常信息"+ex.getMessage());
    }

    @After("pointCut()")
    public void close(){
        System.out.println("释放资源");
    }

    /**
     * 环绕增强
     * 1. 需要返回一个Object
     * 2. 方法第一个参数必须是 ProceedingJoinPoint 是org.aspectj.lang.JoinPoint的子类,只用于环绕增强
     * @return
     */
   // @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object object = null;
        System.out.println("开启事务");
        try{
            //调用真实对象的方法
            object = proceedingJoinPoint.proceed();
            System.out.println("提交事务");
        }catch(Throwable e){
            System.out.println("回滚事务,异常信息"+e.getMessage());
        }
        return object;
    }
}
