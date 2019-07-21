package dev.nhh.echoserver.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable {

    private DatagramSocket socket;
    private boolean running;

    @Override
    public void run() {
        running = true;

        try {
            socket = new DatagramSocket(4445);
            System.out.println("Listening on 4445");
        } catch(SocketException e) {
            e.printStackTrace();
        }

        while (running) {

            final byte[] buffer = new byte[128];

            final var request = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(request);
                // Todo Is it better to have multiple
                final var response = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), 44455);
                socket.send(response);
            } catch(IOException e) {
                e.printStackTrace();
            }

        }

        socket.close();
    }
}
