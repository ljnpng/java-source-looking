package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 实现一个Runnable 接口 当作 Thread 构造函数的入参, start Thread。
        new Thread(() -> System.out.println(Thread.currentThread().getName()), "Thread-1").start();

        // 继承 Thread， 重写 run 方法, start 这个子类。
        new Thread("Thread-2") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        // Callable 结合 Future 构建需要返回值的线程
        FutureTask<String> task = new FutureTask<>(() -> "I'm Thread-3");
        new Thread(task, "Thread-3").start();
        System.out.println(task.get());

        //-------------------------------
        // 通过Executors工具类快速创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            pool.execute(() -> System.out.println("Executor.newFixedThreadPool-" + finalI));
        }
        pool.shutdown();

        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();

    }
}