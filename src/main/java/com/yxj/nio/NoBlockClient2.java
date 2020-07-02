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
 * 非阻塞的NIO客户端
 * 这个会接收服务端返回的消息
 * TCP协议版本
 * */
public class NoBlockClient2 {

    // 发送文件到服务器
    public static void main(String[] args) throws IOException {
        // 客户端的channel
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9696));
        // 设置为非阻塞的
        clientChannel.configureBlocking(false);
        // 要发送的文件channel
        FileChannel fileChannel = FileChannel.open(Paths.get("d:/test/1904201454136002_4.jpg"), StandardOpenOption.READ);
        // 拿到Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        while (fileChannel.read(byteBuffer) > 0) {
            // 从buffer读之前调用这个
            byteBuffer.flip();
            clientChannel.write(byteBuffer);
            // 往buffer里面写就调用clear
            byteBuffer.clear();
        }
        fileChannel.close();
        Selector selector = Selector.open();
        clientChannel.register(selector, SelectionKey.OP_READ);
        while (true) {
            if (selector.select() == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 当有读事件
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    String msg = new String(byteBuffer.array(), 0, byteBuffer.limit());
                    System.out.println(msg);
                }
                iterator.remove();
            }
        }

    }

}
