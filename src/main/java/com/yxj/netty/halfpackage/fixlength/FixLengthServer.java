package com.yxj.netty.halfpackage.fixlength;

import com.yxj.netty.echo.EchoServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * 固定长度解决粘包半包问题
 */
public class FixLengthServer {


    // 当传输中文时  使用固定长度的解码器指定的长度需要是字节数组的长度
    public static String SERVER_RESPONSE = "server accept消息";

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            // 当传输中文时  使用固定长度的解码器指定的长度需要是字节数组的长度
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(FixLengthClient.clientReq.getBytes().length))
                                    .addLast(new FixLengthServerHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(EchoServer.port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
