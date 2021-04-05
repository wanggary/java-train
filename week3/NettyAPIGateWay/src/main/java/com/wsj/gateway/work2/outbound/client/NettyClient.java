package com.wsj.gateway.work2.outbound.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;

public class NettyClient {

    public void connect(String host, int port, FullHttpRequest request) throws Exception {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ClientOutboundHandler());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}
