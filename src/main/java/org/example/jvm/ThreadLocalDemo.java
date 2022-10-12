package org.example.jvm;

import java.text.SimpleDateFormat;

public class ThreadLocalDemo implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formattor = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
//        ThreadLocalDemo obj = new ThreadLocalDemo();
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(obj, i + "");
//            thread.start();
//        }

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {

//            executorService.execute(() -> System.out.println(Thread.currentThread().getName()));
//        }
//        executorService.shutdown();

//        ThreadPoolExecutor poolExecutor = ;


    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + formattor.get().toPattern());
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        formattor.set(new SimpleDateFormat());
        System.out.println(Thread.currentThread().getName() + ":" + formattor.get().toPattern());
    }
}
