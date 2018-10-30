package com.github.xiaoxfan;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author xiaofan
 * @date 2018/10/30 17:34
 */
public class EchoServer {
    /**
     * server listening port
     */
    private static final int PORT = 8888;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(eventLoopGroup)
                .channel(NioDatagramChannel.class)
                .handler(new EchoServerHandler());
        bootstrap.bind(PORT).sync().channel().closeFuture().await();
    }
}
