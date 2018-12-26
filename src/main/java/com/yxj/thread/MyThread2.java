package com.yxj.thread;

public class MyThread2 implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<50;i++){
            System.out.println("实现Runnable接口的方式的"+Thread.currentThread().getName()+"正在运行"+i);
        }
    }
}
