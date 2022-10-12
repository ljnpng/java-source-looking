package org.example.steam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * TODO
 */
public class PredicateTest {
    public static long count(Integer[] arr, Predicate<Integer> predicate) {
        long count = Arrays.stream(arr).filter(predicate).count();
        System.out.println(count);
        return count;
    }


    public static void main(String[] args) {
        Integer[] arr = {-12345, 9999, 520, 0, -38, -7758520, 941213};

        Predicate<Integer> p1 = integer -> integer >= 0;
        Predicate<Integer> p2 = integer -> Math.abs(integer) > 100;
        Predicate<Integer> p3 = integer -> integer % 2 == 0;

        count(arr, p1);
        count(arr, p1.negate());
        count(arr, p2.and(p3));
        count(arr, p1.negate().or(p3));

        Function<ArrayList<Integer>, Integer> function = integers -> {
            int sum = 0;
            for (Integer integer : integers) {
                sum += integer;
            }
            return sum / integers.size();
        };

    }
}
