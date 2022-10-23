package com.rick.chapter_03.d01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rick
 * @Date: 2022/10/3 20:32
 */
public class T14_FlightQueryTask extends Thread implements T13_FlightQuery {

    private final String origin;

    private final String destination;

    private final List<String> flightList = new ArrayList<>();

    public T14_FlightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }


    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);

        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The flight: %s list query successful\n", getName());
            for (int i = 1; i <= randomVal; i++) {
                System.out.println(getName() + "#" + i);
            }
            System.out.println();
        } catch (InterruptedException e) {

        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
