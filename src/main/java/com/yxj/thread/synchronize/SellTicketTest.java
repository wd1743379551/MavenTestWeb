package com.yxj.thread.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SellTicketTest {

    public static void main(String[] args) throws InterruptedException {
        SellTicket sellTicket=new SellTicket();
        Thread window1 = new Thread(sellTicket, "窗口一");
        Thread window2 = new Thread(sellTicket,"窗口二");
        window1.start();
        window2.start();
 /*       ExecutorService service= Executors.newFixedThreadPool(10);
        service.submit(sellTicket);
        service.submit(sellTicket);
        service.shutdown();*/
    }
}
