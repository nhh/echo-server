package dev.nhh;

import dev.nhh.echoserver.server.Channel;
import dev.nhh.echoserver.server.Server;
import dev.nhh.echoserver.server.UdpSocket;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Set;

public class EchoServer {

    Logger logger = LoggerFactory.getLogger(EchoServer.class);

    public static void main(String[] args) throws Exception {
        var server = new Server();
        server.setName("German Rush Company");

        var channel1 = new Channel();
        channel1.setName("Eingangshalle");
        channel1.setUsers(Set.of("Niklas", "Augusto"));

        var channel = new Channel();
        channel.setName("Laberecke #1");
        channel.setUsers(Set.of("Niklas", "Augusto"));

        server.setChannels(Set.of(channel1, channel));

        var udpSocket = new UdpSocket();
        var serverThread = new Thread(udpSocket);
        serverThread.start();

        /* init jaxb marshaler */
        JAXBContext jaxbContext = JAXBContext.newInstance( Server.class );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        /* set this flag to true to format the output */
        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );


        final long start = System.currentTimeMillis ();
        jaxbMarshaller.marshal( server, new File( "server.xml" ) );
        final long end = System.currentTimeMillis ();

        System.out.println (end - start);



        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:44455");
        broker.setPersistent(false);
        broker.start();

    }

}
