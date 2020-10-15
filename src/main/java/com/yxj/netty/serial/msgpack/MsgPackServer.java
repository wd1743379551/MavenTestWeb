package com.yxj.netty.serial.msgpack;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 使用自定义的对象序列化框架msgpack传输接收对象
 */
public class MsgPackServer {
    public static final int PORT = 10089;

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(PORT)
                    /*服务端每接收到一个连接请求，就会新启一个socket通信，也就是channel，
            所以下面这段代码的作用就是为这个子channel增加handle*/
                    .childHandler(new ServerInit());
            // 绑定端口 等待成功
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }


    private static class ServerInit extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline()
            .addLast(new LengthFieldBasedFrameDecoder(65536, 0, 2, 0,2))
                    .addLast(new MessagePackDecoder())
                    .addLast(new MsgPackServerHandler());
        }
    }
}
