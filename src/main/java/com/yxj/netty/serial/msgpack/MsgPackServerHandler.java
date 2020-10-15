package com.yxj.netty.serial.msgpack;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

public class MsgPackServerHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger integer = new AtomicInteger();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        User user = (User) msg;
        System.out.println("server accept userName is " + user.getUserName() + " age is" + user.getAge());
        System.out.println("server counter is " + integer.incrementAndGet());
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器接收到了" + System.getProperty("line.separator"), Charset.forName("utf8")));
    }
}
