package com.yxj.thread.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 *   CountDownLatch  传入一个整数  每次调用countDown方法改值减一 调用await方法时 会阻塞当前线程，然后到值减为0时，await阻塞的地方会开始执行
 */
public class RunThread implements Runnable{


    private CountDownLatch countDownLatch;

    private CountDownLatch arriveDownLatch;

    public RunThread(CountDownLatch countDownLatch, CountDownLatch arriveDownLatch) {
        this.countDownLatch = countDownLatch;
        this.arriveDownLatch = arriveDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始跑步");
        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"到达终点");
        arriveDownLatch.countDown();
    }
}
