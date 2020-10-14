package com.yxj.netty.halfpackage.customseparator;

import com.yxj.netty.halfpackage.systemline.LineClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class DelimClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", DelimtServer.PORT)
                    .group(group)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ByteBuf buffer = Unpooled.buffer(DelimtServer.CUSTOM_SEPARATOR.length());
                            buffer.writeBytes(DelimtServer.CUSTOM_SEPARATOR.getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buffer))
                            .addLast(new DelimtClientChannelHandler());
                        }
                    })
                    .connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
