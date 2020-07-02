package com.yxj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞网络的nio服务器
 */
public class NetBolckServer {

    // 发送图片到服务器
    public static void main(String[] args) throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器绑定到6868端口
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 6969));
        //  获取没有则创建的文件channel
        FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 该方法会一直阻塞 直至获取到消息 会获取到客户端的连接 还必须客户端告诉服务端已经发送消息完成
        SocketChannel client = serverSocketChannel.accept();
        // 获取缓冲 一次读1K
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int i = 0;
        while (client.read(allocate) > 0) {
            allocate.flip();
            fileChannel.write(allocate);
            allocate.clear();
            i++;
        }
        System.out.println("循环次数" + i);


        // 获取完了告诉客户端已经获取完成
        String message = "成功收到文件";
        allocate.put(message.getBytes());
        allocate.flip();
        //把buffer的数据往channel里面写也是一种读 所以写buffer的数据之前要调用flip方法
        client.write(allocate);

        client.close();
        serverSocketChannel.close();
        fileChannel.close();
    }

}
