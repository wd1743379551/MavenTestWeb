package com.yxj.netty.serial.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        MessagePack messagePack = new MessagePack();
        // 返回可读字节长度
        int length = msg.readableBytes();
        // 定义字节数组
        byte[] arr = new byte[length];
        msg.readBytes(arr);
        User user = messagePack.read(arr, User.class);
        System.out.println("在MessagePackDecoder解析的用户对象为" + user.getUserName());
        // 把解析得到的用户对象传递给下一个入栈处理器
        out.add(user);
    }
}
