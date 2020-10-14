package com.yxj.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

// 表示Handler可以在多个channel共享
@ChannelHandler.Sharable
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server exceptionCaught");
        ctx.close();
        cause.printStackTrace();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server handlerAdded");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelActive");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)/*flush掉所有的数据*/
                .addListener(ChannelFutureListener.CLOSE);/*当flush完成后，关闭连接*/
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器接收到消息:" + byteBuf.toString(Charset.forName("UTF-8")));
        System.out.println("消息接收次数" + atomicInteger.incrementAndGet());
        String serverMsg = "hello client";
        ctx.writeAndFlush(Unpooled.copiedBuffer(serverMsg, Charset.forName("UTF-8")));
        ReferenceCountUtil.release(msg);

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelRegistered");
    }
}
