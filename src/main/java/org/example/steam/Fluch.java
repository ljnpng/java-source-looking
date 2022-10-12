package org.example.steam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TODO
 */

interface Shape {
    void draw();
}

public class Fluch {


    public static void main(String[] args) {
        //获取 Circle 的对象，并调用它的 draw 方法
        Shape shape1 = () -> System.out.println("Inside Circle::draw() method.");
        shape1.draw();

        List<List<String>> lst = new ArrayList<>();
        List<String> sub1 = new ArrayList<>();
        sub1.add("1");
        sub1.add("2");
        sub1.add("3");
        List<String> sub2 = new ArrayList<>();
        sub1.add("8");
        sub1.add("5");
        sub1.add("6");

        lst.add(sub1);
        lst.add(sub2);

        List<String> collect = lst.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        // 这个相当于map和reduce组合
        System.out.println("reduct: " + collect.stream().reduce(0, (integer, s) -> integer >= Integer.parseInt(s) ? integer : Integer.parseInt(s), Integer::max));
        collect.stream()
                .collect(Collectors.partitioningBy(s -> s.equals("a"), Collectors.reducing("", a -> a, (serializable, serializable2) -> serializable + serializable2)))
                .entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));

        collect.forEach(System.out::println);

        System.out.println("=============");
        Function<String, Date> f1 = (new Function<String, Date>() {
            @Override
            public Date apply(String s) {
                return null;
            }
        }).andThen(new Function<Date, Date>() {
            @Override
            public Date apply(Date date) {
                return new Date();
            }
        });

        f1.apply("3");

        collect.stream().sorted(Comparator.nullsFirst(String::compareTo));

        List<People> people = new ArrayList<>();
        people.stream()
                .sorted(Comparator.nullsFirst(Comparator.comparing(People::getName, Comparator.nullsFirst(String::compareTo))));


    }


}

class People {
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
