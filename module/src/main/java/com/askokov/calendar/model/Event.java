package com.askokov.calendar.model;

import java.util.Date;

import com.askokov.calendar.service.CalendarUtil;

public class Event {
    private Date dateTime;
    private String description;

    public Event() {
    }

    public Event(final Date dateTime, final String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(final Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description + " (" + CalendarUtil.formatHour(dateTime) + ")";
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
