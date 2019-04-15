package dev.nhh;

import dev.nhh.echoserver.server.JettyServer;
import dev.nhh.echoserver.server.UdpServer;

public class EchoServer {

    public static void main(String[] args) throws Exception {
        var server = new UdpServer();
        var serverThread = new Thread(server);
        serverThread.start();

        var websocket = new JettyServer();
        websocket.start();
    }


}
