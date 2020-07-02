package com.yxj.thread.reentrantlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSample3 {


    private static Lock lock = new ReentrantLock();
    private static volatile int i = 1;
    private static final int THREAD_NUM = 5;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));

    public static void main(String[] args) throws InterruptedException {

        Condition condition = lock.newCondition();
        for (int i = 1; i <= THREAD_NUM; i++) {
            threadPoolExecutor.submit(new CustomThread(condition, i));
        }
        while (true) {
            if (i == 100) {
                break;
            }
        }
        threadPoolExecutor.shutdown();
    }


    static class CustomThread implements Callable<Integer> {

        Condition current;
        int printNum;

        public CustomThread(Condition current, int printNum) {
            this.current = current;
            this.printNum = printNum;
        }


        @Override
        public Integer call() throws Exception {
            while (true) {
                lock.lock();
                int index = i % THREAD_NUM;
                if (printNum < THREAD_NUM) {
                    if (index != printNum) {
                        current.await();
                        continue;
                    }
                } else {
                    if (index != 0) {
                        current.await();
                        continue;
                    }
                }
                doSomething();
                i++;
                System.out.println("当前i" + i);
                current.signalAll();
                if (i == 100) {
                    lock.unlock();
                    break;
                }
                lock.unlock();
            }
            return i;
        }

        private void doSomething() throws InterruptedException {
            System.out.println("线程号" + printNum);
        }
    }


}
