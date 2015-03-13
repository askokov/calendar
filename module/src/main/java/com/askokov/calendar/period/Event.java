package com.askokov.calendar.period;

import org.joda.time.DateTime;

public class Event {
    private DateTime dateTime;
    private String description;

    public Event(final DateTime dateTime, final String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}
