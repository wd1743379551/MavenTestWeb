package com.yxj.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class MyThread1 implements Runnable{

    private int i=0;

    private ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {
      lock.lock();
      try {
          for(int j=0;j<10000;j++){
              i++;
          }
      }finally {
          lock.unlock();
      }

    }


    public int getI() {
        return i;
    }
}
