package org.example.apache;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatcher {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sleepOneSecond();
        stopWatch.split();
        System.out.println("split time: " + stopWatch.getSplitTime());
        sleepOneSecond();
        stopWatch.split();
        System.out.println("split time: " + stopWatch.getSplitTime());
        stopWatch.stop();
        System.out.println("total time: " + stopWatch.getTime());
    }

    private static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
