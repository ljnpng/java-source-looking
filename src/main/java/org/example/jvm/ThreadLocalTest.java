package org.example.jvm;

public class ThreadLocalTest {
    private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getName());

    public static void main(String[] args) {
        System.out.println("Im a main thread : " + threadLocal.get());
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadLocal.set(threadLocal.get() + "_" + i);
                System.out.println("Im thread_1 threadLocal = " + threadLocal.get());
            }
        }).start();
        new Thread(() -> {
            for (int i = 20; i < 40; i = i + 2) {
                threadLocal.set(threadLocal.get() + "_" + i);
                System.out.println("Im thread_2 threadLocal = " + threadLocal.get());
            }
        }).start();

    }
}
