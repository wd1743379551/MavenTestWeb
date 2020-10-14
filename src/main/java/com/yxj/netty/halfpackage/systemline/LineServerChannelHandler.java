package com.yxj.netty.halfpackage.systemline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class LineServerChannelHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger atomicInteger = new AtomicInteger();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器接收消息" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("次数" + atomicInteger.incrementAndGet());

        String responseMsg = "hello netty client" + System.getProperty("line.separator");
        ctx.writeAndFlush(Unpooled.copiedBuffer(responseMsg, CharsetUtil.UTF_8));

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SERVER channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
