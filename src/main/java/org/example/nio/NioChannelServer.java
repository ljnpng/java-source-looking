package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioChannelServer {
    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel ssChannel = ServerSocketChannel.open();
             Scanner scanner = new Scanner(System.in)) {

            ssChannel.configureBlocking(false);
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            ssChannel.bind(new InetSocketAddress(8080));

            ByteBuffer buffer = ByteBuffer.allocate(1024); // 1kb
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssChannel1.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        String data = readDataFromSocketChannel(socketChannel, buffer);
                        System.out.println("Client:" + data.replace("EOF", "")); // Removing the special character

                        socketChannel.register(selector, SelectionKey.OP_WRITE);

                    } else if (key.isWritable()) {
                        // SelectionKey.OP_WRITE 通常会一直被报告为准备就绪，因为通道通常总是准备好写入数据（除非它的内部缓冲区已满）
                        System.out.print("Server: ");
                        String serverMessage = scanner.nextLine();

                        buffer.clear();
                        buffer.put(serverMessage.getBytes(StandardCharsets.UTF_8));
                        buffer.flip();

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(buffer);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readDataFromSocketChannel(SocketChannel socketChannel, ByteBuffer buffer) throws IOException {
        StringBuilder data = new StringBuilder();
        while (true) {
            buffer.clear();
            int read = socketChannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            byte[] dest = new byte[buffer.limit()];
            buffer.get(dest);
            String segment = new String(dest, StandardCharsets.UTF_8);
            data.append(segment);
            if (segment.endsWith("EOF")) {
                break;
            }
        }
        return data.toString();
    }
}
