package com.yxj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * 非阻塞的服务器
 * 通过selector实现
 */
public class NoBlockServer {


    public static void main(String[] args) throws IOException {
        // 获取服务器的Channel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 绑定ip端口号
        serverChannel.bind(new InetSocketAddress("127.0.0.1", 9696));
        // 设置为非阻塞模式
        serverChannel.configureBlocking(false);
        // 获取一个selector
        Selector selector = Selector.open();
        // 把服务注册到selector 并指定关注的操作 关注accept操作 注意注册前要设置为非阻塞模式 不然抛出异常IllegalBlockingModeException
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动了");
        // 然后开始轮训
        while (true) {
            System.out.println("进入轮训");
            int select = selector.select();
            if (select == 0) {
                System.out.println("=====");
                continue;
            }
            // 不等于0 说明有可以处理的key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    //说明新的事件是有新的连接到服务器
                    ServerSocketChannel keyChannel = (ServerSocketChannel) selectionKey.channel();
                    // 如果是连接的请求 就用accept方法拿到客户端的
                    SocketChannel clientChannel = keyChannel.accept();
                    //TODO 注册前一定要设置为非阻塞模式
                    clientChannel.configureBlocking(false);
                    // 把客户端连接注册到选择器上 拿到客户端连接是为了监听客户端的读就绪事件
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()){
                    // 表示通道中有了可读的数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
                    // 把客户端文件存在哪里
                    FileChannel  fileChannel = FileChannel.open(Paths.get("noBlock.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    int read = socketChannel.read(byteBuffer);
                    while (read > 0) {
                        read = socketChannel.read(byteBuffer);
                        // 切换成读模式
                        byteBuffer.flip();
                        fileChannel.write(byteBuffer);
                        // 切换成写模式
                        byteBuffer.clear();
                    }
                    // 服务器接收完文件给客户端写消息
                    String message = "收到文件";
                    byteBuffer.put(message.getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                    if (read == -1) {
//                        selectionKey.cancel();
                        socketChannel.close();
                    }
                    fileChannel.close();
                    // 删除处理完成的key
                    iterator.remove();
                }
            }
        }
    }

}
