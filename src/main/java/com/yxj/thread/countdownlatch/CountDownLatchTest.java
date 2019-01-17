package com.yxj.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *   CountDownLatch  传入一个整数  每次调用countDown方法改值减一 调用await方法时 会阻塞当前线程，然后到值减为0时，await阻塞的地方会开始执行
 *   public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 *   public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 */
public class CountDownLatchTest {



    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        CountDownLatch arriveDown=new CountDownLatch(10);
        RunThread runThread = new RunThread(countDownLatch,arriveDown);

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        for (int i=0;i<10;i++){
            executorService.submit(runThread);
        }
        try {
            arriveDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("比赛结束");
    }


}
