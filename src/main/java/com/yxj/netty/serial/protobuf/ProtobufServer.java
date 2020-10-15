package com.yxj.netty.serial.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * 使用netty的对象序列
 */
public class ProtobufServer {

    public static final int PORT = 10089;

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(PORT)
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
                    //添加 ProtobufVarint32FrameDecoder 以分离数据帧 去除长度帧
                    .addLast(new ProtobufVarint32FrameDecoder())
                    //添加 ProtobufDecoder 反序列化将字节解码为实体
                    .addLast(new ProtobufDecoder(PersonProto.Person.getDefaultInstance()))
                    .addLast(new ProtobufServerHandler());
        }
    }

}
