package com.yxj.thread.connectionpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(50);
    private static AtomicInteger get = new AtomicInteger();
    private static AtomicInteger noGet = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        DBConnectionPool pool = new DBConnectionPool(10);
        PoolTest poolTest = new PoolTest();

        for (int i = 0; i < 50; i++) {
            new Thread(poolTest.new ConnectionThread(pool)).start();
        }
        countDownLatch.await();

        System.out.println("拿到链接次数" + get);
        System.out.println("未拿到链接次数" + noGet);


    }

    private class ConnectionThread implements Runnable {


        DBConnectionPool pool;

        public ConnectionThread(DBConnectionPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            Connection connetcion = pool.getConnetcion(100);
            if (connetcion != null) {
                System.out.println(Thread.currentThread().getName() + "拿到了链接");
                try {
                    Thread.sleep(80);
                    connetcion.commit();
                    pool.releaseConnection(connetcion);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    get.incrementAndGet();
                }
            } else {
                noGet.incrementAndGet();
            }
            countDownLatch.countDown();
        }
    }
}
