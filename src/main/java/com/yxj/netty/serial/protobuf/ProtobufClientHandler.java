package com.yxj.netty.serial.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtobufClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PersonProto.Person.Builder person = PersonProto.Person.newBuilder();
        person.setEmail("1743379551@qq.com");
        person.setName("张三");
        person.setId(1);
        ctx.writeAndFlush(person);
    }
}
