package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NioChannelClient {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in);
             SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080))) {

            ByteBuffer buffer = ByteBuffer.allocate(1024); // Or an appropriate size

            System.out.print("Client: ");
            while (in.hasNext()) {
                String userInput = in.nextLine() + "EOF";

                // Write user input to server
                buffer.clear();
                buffer.put(userInput.getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                socketChannel.write(buffer);

                // Read server response
                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();
                byte[] responseBytes = new byte[buffer.remaining()];
                buffer.get(responseBytes);
                String serverResponse = new String(responseBytes, StandardCharsets.UTF_8);

                System.out.println("Server: " + serverResponse);
                System.out.print("Client: ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
