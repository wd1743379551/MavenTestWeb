package com.yxj.netty.halfpackage.customseparator;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * 使用自定义的分隔符解决粘包半包问题
 */
public class DelimtServer {

    public static final int PORT = 12306;

    public static final String CUSTOM_SEPARATOR = "dshfjkewklle254";


    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(group)
                    .localAddress(PORT)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ByteBuf buffer = Unpooled.buffer(DelimtServer.CUSTOM_SEPARATOR.length());
                            buffer.writeBytes(DelimtServer.CUSTOM_SEPARATOR.getBytes());
                            ch.pipeline()
                                    .addLast(new DelimiterBasedFrameDecoder(1024, buffer))
                                    .addLast(new DelimtServerChannelHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
