package com.wsj.gateway.work2.outbound.client;

import com.wsj.gateway.work2.outbound.NettyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new HttpResponseDecoder());
        channel.pipeline().addLast(new HttpRequestEncoder());
        channel.pipeline().addLast(new ClientOutboundHandler());
    }
}
