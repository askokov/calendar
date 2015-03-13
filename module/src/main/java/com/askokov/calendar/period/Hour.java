package com.askokov.calendar.period;

public class Hour extends EventPeriod {

    public Hour() {
        setType(Type.HOUR);
    }

    @Override
    public int getPeriod() {
        return getDate().getHourOfDay();
    }

    @Override
    public String getLabel() {
        return "" + getPeriod() + ":00";
    }
}
