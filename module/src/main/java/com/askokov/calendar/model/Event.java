package com.askokov.calendar.model;

import org.joda.time.DateTime;

public class Event {
    private DateTime dateTime;
    private String description;

    public Event() {
    }

    public Event(final DateTime dateTime, final String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description + " (" + dateTime.toString("HH:mm") + ")";
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
