package dev.nhh.echoserver.server;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/events/")
public class ChannelEventHandler
{

    private Session wsSession;
    private HttpSession httpSession;

    @OnOpen
    public void onWebSocketConnect(Session sess, EndpointConfig config) {
        this.wsSession = sess;
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
    }

    @OnMessage
    public void onWebSocketText(String message) {
        System.out.println("CHANNEL EVENT SESSION ID: " + wsSession.getId());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket Closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }

}
