package com.yxj.thread.waitnotify;

import java.util.LinkedList;

/**
 * 使用wait notify实现
 * 自定义一个阻塞队列
 */
public class MyBlockQueue<T> {
    //存储数据的
    private LinkedList<T> linkedList=new LinkedList();
    //队列最大长度
    private int maxLength=5;
    //最小长度
    private int minLength;
    //队列当前长度
    private int size;

    public void put(T t){
        synchronized (this){
            if(size>=maxLength){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            linkedList.add(t);
            size++;
            System.out.println("新添加的元素为"+t+",队列长度为"+size);
            if(size<maxLength){
                this.notifyAll();
            }
        }
    }

    public T getFirst(){
        T t;
        synchronized (this){
            if(size<=minLength){
                try {
                    this.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            t=linkedList.removeFirst();
            size--;
            System.out.println("从队列中取出元素"+t+",队列长度为"+size);

            if(size>minLength){
                this.notifyAll();
            }
        }
        return t;
    }

    public MyBlockQueue(int maxLength) {
        this.maxLength = maxLength;
    }

    public LinkedList getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList linkedList) {
        this.linkedList = linkedList;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
