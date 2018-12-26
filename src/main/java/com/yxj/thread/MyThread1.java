package com.yxj.thread;

public class MyThread1 extends Thread{

    @Override
    public void run() {
        for(int i=0;i<50;i++){
            System.out.println("继承Thread类的方式的"+getName()+"正在运行"+i);
        }
    }

    public MyThread1(String name) {
        super(name);
    }
}
