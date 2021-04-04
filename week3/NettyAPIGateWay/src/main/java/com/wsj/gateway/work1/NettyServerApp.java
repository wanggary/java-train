package com.wsj.gateway.work1;

import com.wsj.gateway.work1.inbound.HttpInboundServer;

public class NettyServerApp {
    public static void main(String[] args) {
        int port = 8181;
        HttpInboundServer server = new HttpInboundServer(port);
        System.out.println("Netty API Gateway Server started at http://localhost:" + port);

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
