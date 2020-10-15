package com.yxj.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;
import java.util.List;

public class NettyHttpServerChannelHandler extends MessageToMessageDecoder<FullHttpRequest> {
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
        String uri = msg.uri();
        System.out.println("client request uri is" + uri);
        HttpMethod method = msg.method();
        System.out.println("method is " + method.name());
        HttpVersion httpVersion = msg.protocolVersion();
        System.out.println("httpVersion is" + httpVersion.protocolName());
        ByteBuf content = msg.content();
        System.out.println("content is " + content.toString(Charset.forName("utf8")));
        if (method.equals(HttpMethod.POST)) {
            sendResponse(ctx, "POST请求不允许", HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        sendResponse(ctx, "请求成功", HttpResponseStatus.OK);
        return;

    }

    private void sendResponse(ChannelHandlerContext ctx, String content, HttpResponseStatus status) {
        DefaultHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(content, Charset.forName("utf8")));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
