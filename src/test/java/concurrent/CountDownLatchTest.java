package concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    void test() {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            System.out.println("All threads are finished");
        } catch (InterruptedException e) {
        }
    }
}
