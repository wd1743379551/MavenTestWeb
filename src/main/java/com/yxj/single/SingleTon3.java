package com.yxj.single;

/**
 * 懒汉模式单例(同步方法的方式)
 */
public class SingleTon3 {
    private static SingleTon3 ourInstance ;

    public synchronized static SingleTon3 getInstance() {
        if(ourInstance==null){
            ourInstance=new SingleTon3();
        }
        return ourInstance;
    }

    private SingleTon3() {
    }
}
