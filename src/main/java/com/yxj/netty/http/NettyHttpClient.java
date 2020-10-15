package com.yxj.netty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import java.net.URI;
import java.nio.charset.Charset;

public class NettyHttpClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", NettyHttpServer.PORT)
                    .handler(new ChannelHandlerInit());
            ChannelFuture future = bootstrap.connect().sync();
            // 发起http请求
            URI uri = new URI("/test");
            String msg = "hello server";
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString(),
                    Unpooled.copiedBuffer(msg, Charset.forName("utf8")));
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            request.headers().set(HttpHeaderNames.HOST, "127.0.0.1").set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            future.channel().write(request);
            future.channel().flush();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

    private static class ChannelHandlerInit extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new HttpRequestEncoder())
                    .addLast(new HttpResponseDecoder())
                    .addLast(new HttpObjectAggregator(65536))
                    .addLast(new HttpContentDecompressor())
                    .addLast(new NettyHttpClientChannelHandler());
        }
    }
}
