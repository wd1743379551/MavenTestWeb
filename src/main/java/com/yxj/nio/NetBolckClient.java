package com.yxj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞网络的nio客户端
 */
public class NetBolckClient {

    // 发送图片到服务器
    public static void main(String[] args) throws IOException {
        // 获取通道
        SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6969));
        FileChannel fileChannel = FileChannel.open(Paths.get("d:/test/1904201454136002_4.jpg"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            // 把读取到的byteBuffer写入服务器
            client.write(byteBuffer);
            byteBuffer.clear();
        }
        // 告诉服务器发送完成
        client.shutdownOutput();
        System.out.println("发送文件完成,准备接收消息");
        // 等待服务器消息,该方法会阻塞
        client.read(byteBuffer);
        System.out.println("接收到消息");

        byteBuffer.flip();
        String s = new String(byteBuffer.array(), 0, byteBuffer.limit());
        System.out.println("接收到消息的内容为"+s );

        // 这里关闭流的时候 会告诉服务器消息发送完了,如果还要接收服务器的消息,在发送完消息之后,还要告诉服务器已经发送完成，使用shutdownOutput方法
        client.close();
        fileChannel.close();
    }

}
