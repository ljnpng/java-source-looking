package org.example.steam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * TODO
 */
public class ConSumerTest {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        Consumer<String> consumer = ((Consumer<String>) s -> System.out.println(s + " accept1 ")).andThen(s -> System.out.println(s + " andthen1"));
        consumer.accept("hello");

    }
}
