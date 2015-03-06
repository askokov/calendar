package com.askokov.calendar.model;

import java.util.List;

public class Hour extends Period {

    private List<Event> events;
    private int hour;

    @Override
    public Type getType() {
        return Type.HOUR;
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(final int hour) {
        this.hour = hour;
    }

    @Override
    public String getLabel() {
        return "" + hour + ":00";
    }

    public int eventCount() {
        return events != null ? events.size() : 0;
    }
}
