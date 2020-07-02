package com.yxj.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio实现文件复制 非直接缓冲分配在堆上
 */
public class NIOCopyFileTest {


    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("d:/test/nio1.txt");
        FileOutputStream outputStream = new FileOutputStream("d:/test/nio2.txt");
        FileChannel channel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        // 获得一个缓冲数组 1个字节
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        // 把输入流里面的文件读取到buffer缓冲
        // 往Buffer中写数据还可以使用put方法
        while (channel.read(byteBuffer) != -1) {
            // 将byteBuffer从写模式变为读模式 其实就是将position变成0 表示下一个要操作元素的起始位置为0
            byteBuffer.flip();
            // 将缓冲区的数据写入输出流的channel
            outChannel.write(byteBuffer);
            // 清空缓冲区 方便下次继续使用
            byteBuffer.clear();
        }
        System.out.println(byteBuffer.isDirect());
        channel.close();
        outChannel.close();
    }


}
