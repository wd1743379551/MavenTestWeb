package com.yxj.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AspectDemo {
    @Pointcut("execution(public * com.yxj.spring.aop.target.BusinessService.update*(..))||execution(public * com.yxj.spring.aop.target.BusinessService.add*(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void beforeMethod() {
        System.out.println("执行 Before");
    }

    @After("pointCut()")
    public void afterMethod() {
        System.out.println("执行 After");

    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("执行 Around前");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("Around 里面打印:" + result);
        System.out.println("执行 Around后");
        return result;
    }

    @AfterThrowing("pointCut()")
    public void throwMethod() {
        System.out.println("执行 AfterThrowing");
    }

    @AfterReturning("pointCut()")
    public void returnMethod() {
        System.out.println("执行 AfterReturning");
    }
}
