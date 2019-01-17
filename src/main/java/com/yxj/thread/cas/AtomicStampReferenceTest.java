package com.yxj.thread.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampReferenceTest {


    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference(50,0);
        Thread thread = new Thread(() -> {
            stampedReference.compareAndSet(50, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            stampedReference.compareAndSet(100, 50, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        });

        Thread thread1 = new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println("before sleep stamp"+stamp);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleep stamp" + stampedReference.getStamp());
            boolean b = stampedReference.compareAndSet(50, 200, stamp, stamp + 1);
            System.out.println(b);
        });
        thread1.start();
        thread.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stampedReference.getReference());
    }
}
