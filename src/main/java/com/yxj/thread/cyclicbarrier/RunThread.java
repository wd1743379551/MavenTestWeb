package com.yxj.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 *   CountDownLatch  传入一个整数  每次调用countDown方法改值减一 调用await方法时 会阻塞当前线程，然后到值减为0时，await阻塞的地方会开始执行
 */
public class RunThread implements Runnable{


    private CyclicBarrier cyclicBarrier;

    public RunThread(CyclicBarrier cyclicBarrier) {
       this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始跑步");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"到达终点");

    }
}
