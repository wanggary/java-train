package com.wsj.gateway.work2.outbound.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


/**
 * 客户端发消息，消息入站
 */
public class ClientOutboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        ctx.writeAndFlush(new Date() + " API Gateway link sucess");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = null;
        String resMsg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " API Gateway response msg：" + msg;
        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resMsg.getBytes("UTF-8")));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", response.content().readableBytes());
        ctx.writeAndFlush(resMsg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("Exception: " + cause.getMessage());
    }

}
