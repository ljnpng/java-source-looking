
package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel ssChannel = ServerSocketChannel.open()) {
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
                        System.out.println(data.replace("EOF", "")); // Removing the special character
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
