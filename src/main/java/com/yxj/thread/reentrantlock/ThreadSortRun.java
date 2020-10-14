package com.yxj.thread.reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadSortRun {

    private static final int THREAD_NUM = 5;
    private static List<Semaphore> list = new ArrayList<>(THREAD_NUM);

    private static volatile int i = 1;
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(THREAD_NUM));


    static {
        for (int i = 0; i < THREAD_NUM; i++) {
            Semaphore semaphore = new Semaphore(1);
            list.add(semaphore);
            if (i != 0) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i< THREAD_NUM; i ++) {
            Future<String> future = null;
            if (i != THREAD_NUM -1) {
                future = threadPool.submit(new MyCallAble(list.get(i), list.get(i + 1), i + 1));
            } else {
                future = threadPool.submit(new MyCallAble(list.get(i), list.get(0), i + 1));
            }
            futureList.add(future);
        }
        for (Future future : futureList) {
            System.out.println(future.get());
        }
        System.out.println("最终i值" + i);
        threadPool.shutdown();
    }


    private static void notifyAllSemaphore() {
        for (Semaphore semaphore : list) {
            semaphore.release();
        }
    }


 private static class MyCallAble implements Callable<String> {

        private Semaphore current;
        private Semaphore next;
        private int printNum;

     public MyCallAble(Semaphore current, Semaphore next, int printNum) {
         this.current = current;
         this.next = next;
         this.printNum = printNum;
     }


     @Override
        public String call() throws Exception {
         while (true) {
             current.acquire();
             if (i >= 100) {
                 break;
             }
             System.out.println("当前线程号" + printNum +  "当前i值" + i);
             i++;
             next.release();
         }
         notifyAllSemaphore();
            return "success";
        }
    }
}






