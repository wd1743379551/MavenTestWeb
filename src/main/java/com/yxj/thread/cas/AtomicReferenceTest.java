package com.yxj.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    private static AtomicReference<User> atomicReference = new AtomicReference<>();

    /**
     * 说明atomicReference指向的是对象引用
     * @param args
     */
    public static void main(String[] args) {
        User oldUser = new User();
        oldUser.setAge(18);
        oldUser.setUserName("zhangsan");
        atomicReference.set(oldUser);
        User newUser = new User();
        newUser.setUserName("lisi");
        newUser.setAge(22);
//        atomicReference.set(newUser);

        System.out.println(atomicReference.get().getAge());

        atomicReference.compareAndSet(oldUser, newUser);

        atomicReference.get().setAge(19);
        atomicReference.get().setUserName("wangwu");
        System.out.println(oldUser.getAge());
        System.out.println(oldUser.getUserName());
    }
}
