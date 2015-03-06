package com.askokov.calendar.model;

import java.util.List;

public class Day extends Period {

    private List<Period> hours;
    private int day;
    private boolean current;

    @Override
    public Type getType() {
        return Type.DAY;
    }

    @Override
    public List<Period> getChildren() {
        return hours;
    }

    public void setHours(final List<Period> hours) {
        this.hours = hours;
    }

    public int getDay() {
        return day;
    }

    public void setDay(final int day) {
        this.day = day;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(final boolean current) {
        this.current = current;
    }

    @Override
    public String getLabel() {
        return "" + day;
    }

    public int eventCount() {
        int count = 0;

        for(Period p : hours) {
            if (Type.HOUR != p.getType()) {
                count += p.eventCount();
            }
        }

        return count;
    }
}
