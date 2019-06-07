package com.learning.conference.scheduler;

import com.learning.conference.scheduler.model.Event;
import com.learning.conference.scheduler.model.SackObject;

import java.util.*;

public class Scheduler {


    public static List<SackObject> schedule(List<Event> events, int minimumWeight, int maximumWeight) {

        Stack<SackObject> sack = new Stack<>();

        getSack(events, sack, maximumWeight, 0, 0);

        if(!sack.isEmpty()) {
            System.out.println("******************Solution : ");
            sack.forEach(it -> {
                System.out.println(it.event.name + " : " + it.event.weight * 5);});
        } else {
            sack = new Stack<>();
            getSack(events, sack, minimumWeight, 0, 0);
            if(!sack.isEmpty()) {
                System.out.println("******************Solution : ");
                sack.forEach(it -> {
                    System.out.println(it.event.name + " : " + it.event.weight * 5);});
            } else {
                getSack(events, sack, minimumWeight, 0, 0);
                System.out.println("No solution");
            }
        }

        return sack.subList(0, sack.size());
    }

    static int getSack(List<Event> events, Stack<SackObject> sack, int max, int startPos, int currentTotal) {

        if(startPos > (events.size() -1)) {

            if(sack.size() >= 2) {
                SackObject pop = sack.pop();
                currentTotal -= pop.event.weight;
                events.get(pop.position).taken = false;
//                System.out.println("Popped : " + pop);

                pop = sack.pop();
                currentTotal -= pop.event.weight;
                events.get(pop.position).taken = false;
//                System.out.println("Popped : " + pop);

                return getSack(events, sack, max, pop.position + 1, currentTotal);
            } else {
                sack.clear();
                return 0;
            }
        }

//        System.out.println("position " + startPos + " number " + events.get(startPos) + " current total " + currentTotal * 5);
//        sack.forEach(it -> {
//            System.out.println(it.event.name + " : " + it.event.weight * 5);
//        });
//        System.out.println("********************");

        if((events.get(startPos).weight + currentTotal) <= max) {
            currentTotal += events.get(startPos).weight;
            sack.push(new SackObject(startPos, events.get(startPos)));
            events.get(startPos).taken = true;
            if(currentTotal == max) {
                return 0;
            } else {
                return getSack(events, sack, max, ++startPos, currentTotal);
            }
        } else {
            SackObject pop = sack.pop();
            currentTotal -= pop.event.weight;
            events.get(pop.position).taken = false;
//            System.out.println("Popped : " + pop);
            return getSack(events, sack, max, pop.position + 1, currentTotal);
        }
    }

}
