package org.example.jvm;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JspTool {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        for (int k = 0; k < 4; k++) {
            executor.execute(() -> {
                int i = 0;
                while (true) {
                    i++;
                    System.out.println(Instant.now());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i % 5 == 0) {
                        System.gc();
                        System.out.println("进行gc");
                    }
                }
            });
        }
    }
}
