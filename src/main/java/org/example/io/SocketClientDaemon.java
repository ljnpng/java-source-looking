package org.example.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SocketClientDaemon {


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(new SocketClient(latch, i)).start();
            latch.countDown();
        }

        // optional 阻塞主线程
        synchronized (SocketClientDaemon.class) {
            try {
                SocketClientDaemon.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SocketClient implements Runnable {

        final private CountDownLatch latch;

        final private int index;

        public SocketClient(CountDownLatch latch, int index) {
            this.latch = latch;
            this.index = index;
        }

        @Override
        public void run() {

            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;

            try {
                socket = new Socket("localhost", 8080);
                in = socket.getInputStream();
                out = socket.getOutputStream();
                latch.await();

                out.write(("HelloServer, I'm client " + index).getBytes());
                out.flush();

                System.out.println("client " + index + " send message to server");

                byte[] buffer = new byte[1024];
                int len = in.read(buffer);
                System.out.println("client " + index + " receive message from server: " + new String(buffer, 0, len));


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != in) {
                        in.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                    if (null != socket) {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("client " + index + " close");
            }
        }
    }
}
