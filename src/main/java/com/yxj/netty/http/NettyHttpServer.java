package com.yxj.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * netty实现的http服务器
 */
public class NettyHttpServer {

    public static final int PORT = 12345;

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(group, worker)
                    .localAddress(PORT)
                    .childHandler(new ServerChannelHandlerImpl());
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    private static class ServerChannelHandlerImpl extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            //请求解码
            ch.pipeline().addLast(new HttpRequestDecoder())
                    // 响应编码
                    .addLast(new HttpResponseEncoder())
                    // 聚合http请求
                    .addLast(new HttpObjectAggregator(65536))
            //启用http压缩
            .addLast("compressor",new HttpContentCompressor())
            .addLast(new NettyHttpServerChannelHandler());
        }
    }
}
