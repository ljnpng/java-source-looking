package org.example.steam;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
interface Observer {
    void notify(String msg);
}

interface Subject {
    void register(Observer observer);

    void notifyObserver(String msg);
}

class Fee implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver(String msg) {
        observers.forEach(observer -> observer.notify(msg));
    }
}

public class ObserverTest {
    public static void main(String[] args) {
        Subject sb = new Fee();
        sb.register(System.out::println);
        sb.register(msg -> System.out.println(msg + " gun"));
        sb.notifyObserver("hahah");
    }
}
