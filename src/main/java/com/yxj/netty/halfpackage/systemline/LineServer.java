package com.yxj.netty.halfpackage.systemline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 使用系统分隔符解决粘包半包问题
 */
public class LineServer {

    public static final int PORT = 12306;

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        try {
            ChannelFuture future = sb.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    // Server必须设置childHandler
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new LineServerChannelHandler());
                        }
                    })
                    .bind("127.0.0.1",PORT).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
