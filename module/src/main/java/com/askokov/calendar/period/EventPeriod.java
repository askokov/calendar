package com.askokov.calendar.period;

import java.util.ArrayList;
import java.util.List;

public class EventPeriod extends Period {

    private List<? extends EventPeriod> children;
    private List<Event> events = new ArrayList<Event>();

    public EventPeriod() {
    }

    public List<? extends EventPeriod> getChildren() {
        return children;
    }

    public void setChildren(final List<? extends EventPeriod> children) {
        this.children = children;
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getPeriod() {
        return 0;
    }

    public String getLabel() {
        return null;
    }
}
