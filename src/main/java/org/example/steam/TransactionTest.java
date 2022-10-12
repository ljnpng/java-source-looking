package org.example.steam;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * TODO
 */

class Transaction {
    private int value;
    private int currency;
    private Trader trader;

    public Transaction(Trader trader, int value, int currency) {
        this.value = value;
        this.currency = currency;
        this.trader = trader;
    }

    public int getValue() {
        return value;
    }

    public int getCurrency() {
        return currency;
    }

    public Trader getTrader() {
        return trader;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "value=" + value +
                ", currency=" + currency +
                ", trader=" + trader +
                '}';
    }
}

class Trader {
    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
public class TransactionTest {
    static List<Transaction> transactions;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");

        Trader mario = new Trader("Mario", "Milan");

        Trader alan = new Trader("Alan", "Cambridge");

        Trader brian = new Trader("Brian", "Cambridge");


        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),

                new Transaction(raoul, 2012, 1000),

                new Transaction(raoul, 2011, 400),

                new Transaction(mario, 2012, 710),

                new Transaction(mario, 2012, 700),

                new Transaction(alan, 2012, 950)
        );

    }

    public static void test1() {
        transactions.stream().filter(transaction -> transaction.getValue() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getCurrency).reversed())
        .forEach(System.out::println);
    }

    public static void test2() {
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct().forEach(System.out::println);
    }

    public static void test3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Trader::getName)))).forEach(System.out::println);
    }

    public static void test4() {
        String[] strings = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .toArray(String[]::new);
    }

    public static void test4_1() {
        List<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .collect(Collectors.toList());
    }

    public static void test4_2() {
        Set<String> collect = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .collect(Collectors.toSet());
        collect.forEach(System.out::println);
    }

    public static void test5() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .forEach(System.out::println);
    }



    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test4_2();

    }

    public void testUnary(String[] data) {
        UnaryOperator<String> operator = s -> s + "\n";

        List<String> lst = new ArrayList<>();
        for (String datum : data) {
            operator.apply(datum);
        }
    }
}
