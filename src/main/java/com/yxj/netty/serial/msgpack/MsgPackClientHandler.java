package com.yxj.netty.serial.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

public class MsgPackClientHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger i  = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("lison");
            user.setAge(16);
            user.setId("5437");
            ctx.writeAndFlush(user);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String string = byteBuf.toString(Charset.forName("utf8"));
        System.out.println("client accept " + string);
        System.out.println("client count is" + i.incrementAndGet());
    }


    /*** 发生异常后的处理*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
