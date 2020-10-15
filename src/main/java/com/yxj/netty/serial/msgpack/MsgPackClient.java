package com.yxj.netty.serial.msgpack;

import com.yxj.netty.serial.protobuf.ProtobufServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;


public class MsgPackClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1", MsgPackServer.PORT)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            // 给对象增加长度帧 长度帧的长度是2个字节 防止粘包半包问题
                            ch.pipeline().addLast(new LengthFieldPrepender(2))
                                    // msgpack编码对象 对客户端发送的对象进行编码
                                    .addLast(new MsgPackEncoder())
                                    // 处理服务器的应答粘包半包
                                    .addLast(new LineBasedFrameDecoder(32))
                                    // 业务类
                                    .addLast(new MsgPackClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
