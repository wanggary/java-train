package com.wsj.gateway.work2.outbound;

import com.wsj.gateway.work2.outbound.client.NettyClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class NettyClientHandler {

    private NettyClient nettyClient;
    private String host;
    private int port;

    public NettyClientHandler(String host, int port) {
        this.host = host;
        this.port = port;

        nettyClient = new NettyClient();
    }

    public void handle(final FullHttpRequest request, final ChannelHandlerContext ctx) {
        try {
            nettyClient.connect(host, port, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
