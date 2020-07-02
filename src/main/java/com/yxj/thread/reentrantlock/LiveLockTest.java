package com.yxj.thread.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockTest {


    private static Lock oneLock = new ReentrantLock();
    private static Lock twoLock = new ReentrantLock();

    private static class OneTwoRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (oneLock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + "---getOneLock");
                    try {
                        // 业务逻辑
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (twoLock.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " ---getTwoLock");
                            System.out.println(Thread.currentThread().getName() + "  finished");
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            oneLock.unlock();
                            twoLock.unlock();
                        }
                    } else {
                        oneLock.unlock();
                    }
                }
            }
        }
    }



    private static class TwoOneRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (twoLock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + "---getTwoLock");
                    try {
                        // 业务逻辑
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (oneLock.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " ---getOneLock");
                            System.out.println(Thread.currentThread().getName() + "  finished");
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            oneLock.unlock();
                            twoLock.unlock();
                        }
                    } else {
                        twoLock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new OneTwoRunnable(), "线程1").start();
        new Thread(new TwoOneRunnable(), "线程2").start();
    }


}
