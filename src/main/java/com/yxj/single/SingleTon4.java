package com.yxj.single;

/**
 * 懒汉模式第3种 只同步关键部分  不安全
 * 加volatile可解决
 *
 */
public class SingleTon4 {
    private static SingleTon4 ourInstance;

    public static SingleTon4 getInstance() {
        //  第一重检查：如果instance未被初始化，则进入同步代码块
        if(ourInstance==null){
            //  同步代码块，保证线程安全
            synchronized (SingleTon4.class){
                //  第二重检查：如果instance未被初始化，则初始化该类实例
                if(ourInstance==null){
                    ourInstance=new SingleTon4();
                }
            }
        }
        return ourInstance;
    }

    private SingleTon4() {
    }
}
