package com.yxj.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class NettyHttpClientChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        System.out.println("response headers" + response.headers());
        System.out.println("response content" + response.content().toString(Charset.forName("utf8")));
        ReferenceCountUtil.release(msg);
    }
}
