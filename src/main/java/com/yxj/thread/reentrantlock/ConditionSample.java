package com.yxj.thread.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 罗天 on 2020/6/16
 */
public class ConditionSample {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    private static Condition conditionD = lock.newCondition();
    private static Condition conditionE = lock.newCondition();

    private static void doSomthing(String name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while(true){
                    lock.lock();
                    Thread.sleep(2000);
                    doSomthing("A is doing");
                    conditionB.signal();
                    conditionA.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while(true){
                    lock.lock();
                    Thread.sleep(2000);
                    doSomthing("B is doing");
                    conditionC.signal();
                    conditionB.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while(true){
                    lock.lock();
                    Thread.sleep(2000);
                    doSomthing("C is doing");
                    conditionD.signal();
                    conditionC.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while(true){
                    lock.lock();
                    Thread.sleep(2000);
                    doSomthing("D is doing");
                    conditionE.signal();
                    conditionD.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while(true){
                    lock.lock();
                    Thread.sleep(2000);
                    doSomthing("E is doing");
                    conditionA.signal();
                    conditionE.await();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
