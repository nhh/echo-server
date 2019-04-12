package dev.nhh;

import dev.nhh.echoserver.server.Server;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    Logger logger = LoggerFactory.getLogger(EchoServer.class);

    public static void main(String[] args) throws Exception {
        var server = new Server();
        var serverThread = new Thread(server);
        serverThread.start();

        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:44455");
        broker.setPersistent(false);
        broker.start();

    }

}
