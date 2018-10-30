package com.github.xiaoxfan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;


/**
 * @author xiaofan
 * @date 2018/10/30 17:34
 */
public class EchoServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
        // read message from client
        ByteBuf content = datagramPacket.copy().content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        String message = new String(bytes, CharsetUtil.UTF_8);
        System.out.println("receive message from client >>" + message);

        //send a message to client
        DatagramPacket datagramPacket1 = new DatagramPacket(Unpooled.copiedBuffer("Hello , I'm server. 时间：" + System.currentTimeMillis(), CharsetUtil.UTF_8), datagramPacket.sender());
        channelHandlerContext.writeAndFlush(datagramPacket1).sync();
    }
}
