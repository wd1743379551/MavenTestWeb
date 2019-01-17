package com.yxj.thread.reentrantlock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    /**
     * 测试读写锁  ReentrantLock是一个排他锁
     */
    private static ReentrantLock lock=new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static Lock readLock=readWriteLock.readLock();
    private static Lock writeLock=readWriteLock.writeLock();

    public static void main(String[] args) {
        //测试读写锁
        ReadWriteTest readWriteTest=new ReadWriteTest(666);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for (int i=0;i<2;i++){
            threadPool.execute(()->{
                try {
                    readWriteTest.readValue(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threadPool.execute(()->{
                try {
                    double random = Math.random();
                    readWriteTest.writeValue(writeLock, new Double(random*10).intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        while (true) {
            if (threadPool.isTerminated()) {
                long end = System.currentTimeMillis();
                System.out.println("使用读写锁用时: " + (end - start) + "ms");
//                System.out.println("使用普通锁锁用时: " + (end - start) + "ms");
                break;
            }
        }


        //测试可重入锁
//        MyThread1 thread1=new MyThread1();
//        Thread thread = new Thread(thread1, "线程一");
//        thread.start();
//        new Thread(thread1,"线程二").start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(thread1.getI());
    }
}
