package com.yxj.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class ClientChannelHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client handlerAdded");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client Channel Register");

    }

    // 通道激活调用 往服务器写入消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channelActive");
        for (int i = 0; i < 100; i ++) {
            String msg = "hello server";
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg, Charset.forName("UTF-8")));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client channelRead");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端接收到服务器返回消息" + byteBuf.toString(Charset.forName("UTF-8")));
        ReferenceCountUtil.release(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exceptionCaught");
        ctx.channel().close();
        cause.printStackTrace();
    }
}
