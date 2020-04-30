package com.yxj.spring.aop.target;

import org.springframework.aop.framework.AopContext;


public class BusinessService {


    public int updateValue(int a, int b) {

        System.out.println(" I am invoking updateValue method");
        return a / b;
    }

    public int addValue(int a, int b) {
        System.out.println(" I am invoking addValue method");
//        int updateValue = this.updateValue(a, b);  // 一个aop方法使用this调用另外一个方法  后一个方法aop会失效
//        System.out.println("addValue in update : " + updateValue);

//        Object currentProxy = AopContext.currentProxy();
//        BusinessService businessService = (BusinessService) currentProxy;
//        int updateValue = businessService.updateValue(a, b);
//        System.out.println("addValue proxy object update : " + updateValue);
        return a + b;
    }

    public void selectValue() {
        System.out.println(" I am invoking selectValue method");

    }



}
