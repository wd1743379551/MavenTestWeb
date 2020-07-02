package com.yxj.nio;


import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * nio实现文件复制 直接缓冲 map的方式
 */
public class NIOCopyFileDirectTest2 {


    public static void main(String[] args) throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("d:/test/nio1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:/test/nio2.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ);

        MappedByteBuffer inMappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());

        byte[] arr = new byte[outMappedByteBuffer.limit()];
        // 从直接缓冲读取到byte数组
        inMappedByteBuffer.get(arr);
        // 把byte数组写入直接缓冲
        outMappedByteBuffer.put(arr);
        System.out.println(inMappedByteBuffer.isDirect());
        System.out.println(outMappedByteBuffer.isDirect());
        channel.close();
        outChannel.close();
    }


}
