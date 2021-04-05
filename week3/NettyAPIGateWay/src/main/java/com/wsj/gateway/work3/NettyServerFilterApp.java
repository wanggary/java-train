package com.wsj.gateway.work3;

import com.wsj.gateway.work3.inbound.HttpInboundServer;

public class NettyServerFilterApp {
    public static void main(String[] args) {
        int port = 8182;
        HttpInboundServer server = new HttpInboundServer(port);
        System.out.println("Netty API Gateway Server with filter started at http://localhost:" + port);

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
