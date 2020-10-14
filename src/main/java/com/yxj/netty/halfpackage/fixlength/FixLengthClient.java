package com.yxj.netty.halfpackage.fixlength;


import com.yxj.netty.echo.EchoServer;
import com.yxj.netty.halfpackage.customseparator.DelimtClientChannelHandler;
import com.yxj.netty.halfpackage.customseparator.DelimtServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

public class FixLengthClient {


    public static String clientReq = "你好 netty服务器";

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture future = bootstrap.channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", EchoServer.port)
                    .group(group)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(FixLengthServer.SERVER_RESPONSE.getBytes().length))
                                    .addLast(new FixLengthClientHandler());
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
