package com.yxj.thread;

import java.util.concurrent.*;

/**
 * 多线程测试类
 */
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 thread1=new MyThread1("继承thread线程名");
        Thread thread2=new Thread(new MyThread2(),"实现Runnable线程名");
        MyThread3 callable=new MyThread3();
        FutureTask<Integer> futureTask=new FutureTask<Integer>(callable);
        //实现callable接口的方式实现多线程  有两种启动方式  第一种 配合Thread类的构造方法  下面直接用futuretask的get获取返回值
//        Thread thread3=new Thread(futureTask);
        ExecutorService threadPool= Executors.newFixedThreadPool(5);
        //线程池的submit方法表示往使用线程池执行任务  其参数可以是callable接口 和futureTask
        //当传入callable接口的参数时 ，返回值future可以用来得到执行结果
        //当传入futureTask的参数 就是把这个futureTask交给线程池管理 不用获取返回值future,直接用futureTask的get方法获取返回值
        //传入futureTask时，返回的future的get方法获取不到返回值 get方法是用来阻塞方法的，直到线程执行完返回结果。
        Future future = threadPool.submit(futureTask);
        thread1.start();
        thread2.start();
        System.out.println(future.get());
//        thread3.start();
        Integer integer = futureTask.get();

        System.out.println(integer);
        System.out.println(0);
        threadPool.shutdown();
    }
}
