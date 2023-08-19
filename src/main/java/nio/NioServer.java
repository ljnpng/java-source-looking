package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        // 创建一个ServerSocketChannel，并且绑定到指定端口上
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        ssChannel.socket()
                .bind(new InetSocketAddress(8080));

        while (true) {
            selector.select(); // 阻塞等待就绪的Channel，这是关键点之一
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    System.out.println("接受请求");
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = ssChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    System.out.println("读取数据");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(socketChannel));
                    socketChannel.close();
                }
                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 1kb
        StringBuilder data = new StringBuilder();
        while (true) {
            buffer.clear();
            int read = socketChannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            byte[] dest = new byte[limit];
            buffer.get(dest);
            data.append(new String(dest, StandardCharsets.UTF_8));
        }
        return data.toString();
    }
}
