package com.yxj.thread.reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * N个线程按顺序执行
 */
public class ConditionSample4 {

    private static final int THREAD_NUM = 5;
    private static Lock lock = new ReentrantLock();
    private static List<Condition> list = new ArrayList<>();
    private static volatile int i = 1;
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));

    static {
        for (int i = 0; i < THREAD_NUM; i ++) {
            list.add(lock.newCondition());
        }
    }


    public static void main(String[] args) {
        List<Future> futureList = new ArrayList<>();
        for (int i = 1; i <= THREAD_NUM; i ++) {
            Future<Integer> future = null;
            if (i < THREAD_NUM) {
                future = threadPool.submit(new CumstomCallable(list.get(i - 1), list.get(i), i));
            } else {
                future = threadPool.submit(new CumstomCallable(list.get(i -1), list.get(0), i));
            }
            futureList.add(future);
        }
        for (Future future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(i);
        threadPool.shutdown();
    }

    private static void notifyAllThread() {
        for (Condition condition : list) {
            condition.signalAll();
        }
    }

    private static class CumstomCallable implements Callable<Integer> {

        Condition current;
        Condition next;
        int printNum;

        @Override
        public Integer call() throws Exception {
            lock.lock();
            while (true) {
                if (i >= 100) {
                    break;
                }
                int modNumNew = i % THREAD_NUM;
                if (printNum < THREAD_NUM) {
                    if (modNumNew != printNum) {
                        next.signal();
                        current.await();
                        continue;
                    }
                } else {
                    if (modNumNew != 0) {
                        next.signal();
                        current.await();
                        continue;
                    }
                }
                i ++;
                System.out.println("线程号" + printNum);
                System.out.println("i加1后" + i);
            }
            notifyAllThread();
            lock.unlock();
            return 1;
        }

        public Condition getCurrent() {
            return current;
        }

        public void setCurrent(Condition current) {
            this.current = current;
        }

        public Condition getNext() {
            return next;
        }

        public void setNext(Condition next) {
            this.next = next;
        }

        public int getPrintNum() {
            return printNum;
        }

        public void setPrintNum(int printNum) {
            this.printNum = printNum;
        }

        public CumstomCallable(Condition current, Condition next, int printNum) {
            this.current = current;
            this.next = next;
            this.printNum = printNum;
        }
    }
}
