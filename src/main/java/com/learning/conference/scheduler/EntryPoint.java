package com.learning.conference.scheduler;

import com.learning.conference.scheduler.model.Event;

import java.util.ArrayList;
import java.util.List;

import static com.learning.conference.scheduler.Scheduler.schedule;
import static java.util.stream.Collectors.toList;

public class EntryPoint {

    public static void main(String[] args) {

        List<Event> events = getEvents();
        int totalWeightOfEvents = getTotalWeightOfEvents(events);

        for(int i = 0; i < totalWeightOfEvents; i = i + getRemainingWeights(totalWeightOfEvents, events)) {
            schedule(events.stream().filter(event -> !event.taken).collect(toList()), 36, 36);
            schedule(events.stream().filter(event -> !event.taken).collect(toList()), 36, 48);
        }
    }

    private static int getRemainingWeights(int totalWeightOfEvents, List<Event> events) {
        return totalWeightOfEvents - events.stream().map(event -> !event.taken ? event.weight : 0).reduce(0, Integer::sum);
    }

    public static List<Event> getEvents() {
        List<Event> events = new ArrayList<>();

        events.add(new Event("Writing Fast Tests Against Enterprise Rails", 60));
        events.add(new Event("Overdoing it in Python", 45));
        events.add(new Event("Lua for the Masses", 30));
        events.add(new Event("Ruby Errors from Mismatched Gem Versions", 45));
        events.add(new Event("Common Ruby Errors", 45));
        events.add(new Event("Rails for Python Developers", 5));
        events.add(new Event("Communicating Over Distance", 60));
        events.add(new Event("Accounting-Driven Development", 45));
        events.add(new Event("Woah", 30));
        events.add(new Event("Sit Down and Write", 30));
        events.add(new Event("Pair Programming vs Noise", 45));
        events.add(new Event("Rails Magic", 60));
        events.add(new Event("Ruby on Rails: Why We Should Move On", 60));
        events.add(new Event("Clojure Ate Scala (on my project)", 45));
        events.add(new Event("Programming in the Boondocks of Seattle", 30));
        events.add(new Event("Ruby vs. Clojure for Back-End Development", 30));
        events.add(new Event("Ruby on Rails Legacy App Maintenance", 60));
        events.add(new Event("A World Without HackerNews", 30));
        events.add(new Event("User Interface CSS in Rails Apps", 30));

        return events;
    }

    public static int getTotalWeightOfEvents(List<Event> events) {

        return events.stream().map(event -> event.weight).reduce(0, Integer::sum);
    }
}
