package com.yxj.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger atomicInteger = new AtomicInteger();


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        int i = atomicInteger.incrementAndGet();
        System.out.println("有新的连接加入, 当前连接数量" + i );
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;/*netty实现的缓冲区*/
        System.out.println("Server Accept:"+in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
