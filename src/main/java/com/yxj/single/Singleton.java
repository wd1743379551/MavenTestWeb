package com.yxj.single;

/**
 * 饿汉模式单例
 * (Singleton实例在什么时候创建是不受控制的，对于静态成员instance，它会在类第一次初始化的时候被创建。这个时刻并不一定是getInstance()方法第一次被调用的时候。也就是说这种单例实现方式不能够精确的控制instance的创建时间，如果这种不足不重要，那么这种单例实现方式是一个不错的选择。)
 */
public class Singleton {

    private static Singleton singleton = new Singleton();

    public  static Singleton getSingleTon(){
        return singleton;
    }

    private Singleton() {
    }
}
