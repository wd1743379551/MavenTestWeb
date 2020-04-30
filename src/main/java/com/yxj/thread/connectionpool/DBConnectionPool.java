package com.yxj.thread.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

public class DBConnectionPool {


    private static LinkedList<Connection> list;

    private int count;

    public DBConnectionPool(int size) {
        count = size;
        list = new LinkedList<>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                list.add(new ConnectionImpl());
            }
        }
    }

    public synchronized Connection getConnection() {
        if (list.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list.removeFirst();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (list.size() == count) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            list.addLast(connection);
            notifyAll();
        }
    }

    public synchronized Connection getConnetcion(long mills){

        if (mills <= 0) {
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!list.isEmpty()) {
                    return list.removeFirst();
                }
            }
        } else {
            long endTime = System.currentTimeMillis() + mills;
            while (true) {
                if (mills <= 0) {
                    System.out.println(Thread.currentThread().getName() + "获取链接超时");
                    return null;
                }
                if (list.isEmpty()) {
                    try {
                        wait(mills);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!list.isEmpty()) {
                    return list.removeFirst();
                }
                mills = endTime - System.currentTimeMillis();
            }
        }
    }
}
