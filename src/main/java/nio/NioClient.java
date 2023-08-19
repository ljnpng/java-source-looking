package nio;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NioClient {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input something: ");
        while (in.hasNext()) {
            try (Socket socket = new Socket("127.0.0.1", 8080)) {
                socket.getOutputStream().write(in.nextLine().getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
