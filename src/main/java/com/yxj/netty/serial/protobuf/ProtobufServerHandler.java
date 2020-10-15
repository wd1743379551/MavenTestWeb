package com.yxj.netty.serial.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProtobufServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersonProto.Person person = (PersonProto.Person) msg;
        System.out.println(person.getName() + " ..." + person.getEmail());

    }
}
