package org.example.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SockerServerDaemon {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);

        while (true) {
            Socket socket = server.accept();

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int len = in.read(buffer);

            String request = new String(buffer, 0, len);
            System.out.println(request);

            out.write("HTTP/1.1 200 OK\r\n".getBytes());

            out.write("Content-Type:text/html;charset=utf-8\r\n".getBytes());

            out.write("Server:Apache-Coyote/1.1\r\n".getBytes());

            out.write("\r\n".getBytes());

            out.write("<h1>Hello World!</h1>".getBytes());

            out.flush();


            in.close();
            out.close();
            socket.close();
        }
    }
}
