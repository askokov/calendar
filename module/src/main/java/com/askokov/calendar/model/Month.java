package com.askokov.calendar.model;

import java.util.List;

public class Month extends Period {

    private List<Period> days;
    private int month;

    @Override
    public Type getType() {
        return Type.MONTH;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    @Override
    public List<Period> getChildren() {
        return days;
    }

    public void setDays(final List<Period> days) {
        this.days = days;
    }

    @Override
    public String getLabel() {
        return "" + month;
    }

    public int eventCount() {
        int count = 0;

        for(Period p : days) {
            if (Type.DAY != p.getType()) {
                count += p.eventCount();
            }
        }

        return count;
    }
}
