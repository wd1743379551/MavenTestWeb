package com.yxj.thread;

import java.util.concurrent.Callable;

public class MyThread3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Integer t=0;
        for(int i=0;i<50;i++){
            System.out.println("实现Callable的方式的"+Thread.currentThread().getName()+"正在运行"+i);
            t++;
        }
        return t ;
    }
}
