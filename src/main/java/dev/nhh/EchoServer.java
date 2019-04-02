package dev.nhh;

import dev.nhh.echoserver.server.Server;

public class EchoServer {

    public static void main(String[] args) {
        var server = new Server();
        var serverThread = new Thread(server);
        serverThread.start();
    }

}
