package com.wsj.gateway.work3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpRequestFilter {

    public boolean filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        if (request.method().name().equalsIgnoreCase("NettyAPIGateway")) {
            return true;
        } else {
            return false;
        }
    }
}
