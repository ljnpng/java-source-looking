
package org.example.nio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in);
             Socket socket = new Socket("127.0.0.1", 8080);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {
            System.out.println("Please input something: ");
            while (in.hasNext()) {
                writer.write(in.nextLine());
                writer.write("EOF"); // Special character to mark end of message
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
