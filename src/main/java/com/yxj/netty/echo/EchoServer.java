package com.yxj.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    public static final int port = 9100;

    public static void main(String[] args) {
        // 新建一个线程组 里面有多个EventLoop 每个与一个Thread绑定
        EventLoopGroup group = new NioEventLoopGroup();
        // 服务器启动必备类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 单一处理器 此处用一个 类上需要加上可共享注解
        final ServerChannelHandler handler = new ServerChannelHandler();
        try {
            serverBootstrap
                    // 指定接收连接的Boss线程组和处理请求的线程组为同一个
                    .group(group)
                    // 指定接收连接的Channel为NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    //
                    .childHandler(new ChannelInitializer<Channel>() {  //连接时执行
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            // 每次有新的连接Channel加入会执行这里的代码 即给连接添加处理的handler
                            // 所以服务器端可以共享handler
                            ch.pipeline().addLast(handler);
                        }
                    })
                    //对Channel进行一些配置
                    //注意以下是socket的标准参数
                    //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    //Option是为了NioServerSocketChannel设置的，用来接收传入连接的
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                    //childOption是用来给父级ServerChannel之下的Channels设置参数的
                    .childOption(ChannelOption.SO_KEEPALIVE, true);;
                    // 绑定端口 阻塞直到绑定端口完成
            ChannelFuture future = serverBootstrap.bind("127.0.0.1", port).sync();
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            //sync()会同步等待连接操作结果，用户线程将在此wait()，直到channel被关闭时，线程被notify(),用户代码继续执行
            //closeFuture()当Channel关闭时返回一个ChannelFuture,用于链路检测
            future.channel().closeFuture().sync();  // 主要是使得当前main线程阻塞,直到关闭才继续向下执行,防止主线程直接执行了shutdownGracefully关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}
