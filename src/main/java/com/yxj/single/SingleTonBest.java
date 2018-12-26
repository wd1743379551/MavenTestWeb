package com.yxj.single;

/**
 * 通过内部类的方式首先，g
 * getInstance()方法中没有锁，这使得在高并发环境下性能优越。其次，只有在getInstance()方法被第一次调用时，Singleton对象才会被创建。因为这种方法巧妙的使用了内部类和类的初始化方式。内部类Singleton被声明为private，这使得我们不可能在外部访问并初始化它。而我们只可能在getInstance()内部对Singleton类进行初始化，利用虚拟机的类初始化机制创建单例。
 */
public class SingleTonBest {



    private SingleTonBest() {
    }

    private static class SingleInner{
        private static SingleTonBest instance=new SingleTonBest();
    }

    public static SingleTonBest getInstance(){
        return SingleInner.instance;
    }
}
