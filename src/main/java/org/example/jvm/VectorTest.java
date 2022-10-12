package org.example.jvm;

import java.util.Vector;

/**
 * 测试Vector的同步性
 */
public class VectorTest {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            new Thread(() -> {
                synchronized (vector) { // 需要额外的对vector进行同步。否则会报边界溢出错误
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            }).start();

            new Thread(() -> {
                synchronized (vector) {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            }).start();


            while (Thread.activeCount() > 20);
        }
    }
}
