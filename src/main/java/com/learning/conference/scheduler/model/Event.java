package com.learning.conference.scheduler.model;

public class Event {

    public String name;
    public int lengthInMinutes;
    public int weight;
    public boolean taken;

    public Event(String name, int lengthInMinutes) {
        this.name = name;
        this.lengthInMinutes = lengthInMinutes;
        weight = lengthInMinutes/5;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                '}';
    }
}
