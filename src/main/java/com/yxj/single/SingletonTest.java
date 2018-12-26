package com.yxj.single;

import org.junit.Test;

/**
 * 单例测试类
 */
public class SingletonTest {

    /**
     * 测试饿汉模式单例
     */
    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "  " + Singleton.getSingleTon().hashCode());
            }).start();
        }
    }
    /**
     * 测试懒汉模式单例(不加同步存在安全问题 可能生成不同的对象)
     */
    @Test
    public void test2(){
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "  " + SingleTon2.getInstance().hashCode());
            }).start();
        }
    }

    /**
     * 测试懒汉模式单例(同步方法的方式)
     * 这种实现的好处是，充分利用了延迟加载的特性，只有在真正需要时创建对象，但坏处也很明显，并发环境下加锁，竞争激烈的场合对性能可能产生一定的影响。
     */
    @Test
    public void test3(){
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "  " + SingleTon3.getInstance().hashCode());
            }).start();
        }
    }

    /**
     * 测试懒汉模式单例(同步代码块的方式)
     *
     */
    @Test
    public void test4(){
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "  " + SingleTon4.getInstance().hashCode());
            }).start();
        }
    }



    /**
     * 测试内部类的方式实现的单例
     *
     */
    @Test
    public void test5(){
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "  " + SingleTonBest.getInstance().hashCode());
            }).start();
        }
    }
}
