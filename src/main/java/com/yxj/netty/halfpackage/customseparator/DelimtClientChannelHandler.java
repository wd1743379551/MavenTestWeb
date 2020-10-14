package com.yxj.netty.halfpackage.customseparator;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class DelimtClientChannelHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "hello netty server" + DelimtServer.CUSTOM_SEPARATOR;
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg, Charset.forName("utf-8")));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("client accept " + byteBuf.toString(Charset.forName("utf8")));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }
}

