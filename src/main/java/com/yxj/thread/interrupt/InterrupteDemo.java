package com.yxj.thread.interrupt;

/**
 * 优雅的中断线程 java线程中断是协作式
 */
public class InterrupteDemo {

    private static class MyThread implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("hello");
//                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 抛出中断异常后 会将中断标志设置为false 需要再调用一次interrupt
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread testThread = new Thread(myThread);
        testThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 将线程中断标志设置为true
        testThread.interrupt();
    }

}
