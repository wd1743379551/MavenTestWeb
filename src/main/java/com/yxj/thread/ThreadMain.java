package com.yxj.thread;

/**
 * 测试守护线程
 * 1.注意守护线程的finally不一定会执行
 * 2.守护线程方法必须在start前调用
 */
public class ThreadMain {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("The thread is try code ");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {

            } finally {
                System.out.println("The thread enter finally");
            }
        });
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "will end");
    }
}
