package com.yxj.thread.waitnotify;

public class MyQueueTest {

     static boolean flag;

    public static void main(String[] args) {
        MyBlockQueue<String> myBlockQueue=new MyBlockQueue<>(8);

        new Thread(()->{
            for(int i=0;i<100;i++){
             myBlockQueue.put(i+"");
            }
          flag=true;
        },"放元素线程").start();
        new Thread(()->{
            while (!flag){
                myBlockQueue.getFirst();
            }
        },"取线程").start();

    }
}
