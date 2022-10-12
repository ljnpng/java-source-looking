package org.example.steam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 */
public class StreamGetTest {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.stream().collect(Collectors.toList());

    }
}
