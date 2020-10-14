package com.yxj.netty.halfpackage.customseparator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;


public class DelimtServerChannelHandler extends ChannelInboundHandlerAdapter {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("server accept msg:"  + byteBuf.toString(Charset.forName("utf8")));
        String response = "hello client netty" + DelimtServer.CUSTOM_SEPARATOR;
        ctx.writeAndFlush(Unpooled.copiedBuffer(response, Charset.forName("utf8")));
        System.out.println("server accept count:" + atomicInteger.incrementAndGet());
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete222");
    }
}
