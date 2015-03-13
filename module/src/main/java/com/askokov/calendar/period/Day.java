package com.askokov.calendar.period;

public class Day extends EventPeriod {

    public Day() {
        setType(Type.DAY);
    }

    @Override
    public int getPeriod() {
        return getDate().getDayOfMonth();
    }

    @Override
    public String getLabel() {
        return "" + getPeriod();
    }
}
