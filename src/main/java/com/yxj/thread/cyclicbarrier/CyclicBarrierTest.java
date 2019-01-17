package com.yxj.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 回环屏障（CyclicBarrier与countDownLatch的区别在于）
 *      CyclicBarrier是另一种并发控制实用工具。和CountDownLatch非常类似，它也可以实现线程间的计数等待，但它的功能比CountDownLatch更加复杂且强大。
 *      CyclicBarrier可以接收一个参数作为barrierAction，所谓barrierAction就是当计数器一次计数完成后，系统会执行的动作
 *
 *      有以下两个构造方法
 *      public CyclicBarrier(int parties, Runnable barrierAction) {   }    (该方法的第二个参数为第一轮执行到barrier状态后执行的线程)
 *      public CyclicBarrier(int parties) {   }
 *      上面的parties参数为N个线程执行到某个状态后开始执行后面的方法
 *
 *      public int await() throws InterruptedException, BrokenBarrierException { };
 *      该await方法表示阻塞 被该方法挡住的线程会阻塞，直到有parties个方法执行到await方法时会执行后面的代码
 *      用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
 *      barrier状态就是调用await方法的地方
 *
 *      public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
 *      是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
 *
 *      CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10);
        for(int i=0;i<10;i++){
            new Thread(new RunThread(cyclicBarrier),"运动员"+i).start();
        }
    }


}
