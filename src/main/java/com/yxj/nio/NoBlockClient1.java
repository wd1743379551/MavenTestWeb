package com.yxj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 非阻塞的NIO客户端
 * 这个不接收服务端消息发送完返回
 * TCP协议版本
 * */
public class NoBlockClient1 {

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
        clientChannel.close();
    }

}
