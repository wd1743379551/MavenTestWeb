package com.yxj.single;
/**
 * 懒汉模式单例(不加同步存在安全问题 可能生成不同的对象)
 */
public class SingleTon2 {
    private static SingleTon2 ourInstance;

    public static SingleTon2 getInstance() {
        if(ourInstance==null){
            ourInstance=new SingleTon2();
        }
        return ourInstance;
    }

    private SingleTon2() {
    }
}
