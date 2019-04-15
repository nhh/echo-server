package dev.nhh.echoserver;

import dev.nhh.echoserver.server.EventClient;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.websocket.jsr356.client.SimpleEndpointMetadata;

import javax.websocket.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class EchoClient
{
    public static void main(String[] args)
    {
        URI uri = URI.create("ws://localhost:8080/events/");
        URI uri2 = URI.create("ws://localhost:8080/log-events/");

        try
        {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            WebSocketContainer container2 = ContainerProvider.getWebSocketContainer();

            try
            {

                Session session = container.connectToServer(EventClient.class, uri);
                Session session2 = container2.connectToServer(EventClient.class, uri2);

                session.getUserProperties().putIfAbsent("publicKey", "Bla");
                session2.getUserProperties().putIfAbsent("publicKey", "Bla");
                // Send a message
                for(var i = 0; i < 10000000; i++) {
                    session.getBasicRemote().sendText("Hello " + i);
                    session2.getBasicRemote().sendText("Hello " + i);
                }

                // Close session
                session.close();
            }
            finally
            {
                // Force lifecycle stop when done with container.
                // This is to free up threads and resources that the
                // JSR-356 container allocates. But unfortunately
                // the JSR-356 spec does not handle lifecycles (yet)
                if (container instanceof LifeCycle)
                {
                    ((LifeCycle)container).stop();
                }
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}
