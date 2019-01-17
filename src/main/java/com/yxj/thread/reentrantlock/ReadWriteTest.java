package com.yxj.thread.reentrantlock;

import java.util.concurrent.locks.Lock;

public class ReadWriteTest {

    private int value;


    public int readValue(Lock lock) throws InterruptedException {
        lock.lock();
        try {
            //读操作耗时越多越能提现读操作的优势
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"正在读取值"+value);
            return value;
        }finally {
            lock.unlock();
        }
    }


    public void writeValue(Lock lock,int newValue) throws InterruptedException {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"正在写值"+newValue);
            value=newValue;
        }finally {
            lock.unlock();
        }
    }

    public ReadWriteTest(int value) {
        this.value = value;
    }
}
