package com.yxj.thread.synchronize;

public class SellTicket implements Runnable{

    private Object lock=new Object();

    private int ticketNum=100;


    @Override
    public  void run() {
            while(ticketNum>0){
                synchronized (lock){
                        if(ticketNum>0){
                            System.out.println("线程名为"+Thread.currentThread().getName()+"的线程正在出售第"+ticketNum+"张票");
                            ticketNum--;
                        }
                        String payAmount="";
                    }
            }
    }
}
