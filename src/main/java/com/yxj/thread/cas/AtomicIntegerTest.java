package com.yxj.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(50);
        Thread thread = new Thread(() -> {
            //将atomicInteger从50更新到51  再更新到50
            //下面的第二个线程还是更新成功  这里就发生了ABA问题
            atomicInteger.compareAndSet(50, 51);
            atomicInteger.compareAndSet(51, 50);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger);
        Thread thread1 = new Thread(() -> {
            boolean b = atomicInteger.compareAndSet(50, 52);
            System.out.println(b);
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger);
    }

}
