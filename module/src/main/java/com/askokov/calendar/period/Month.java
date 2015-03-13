package com.askokov.calendar.period;

public class Month extends EventPeriod {

    public Month() {
        setType(Type.MONTH);
    }

    @Override
    public int getPeriod() {
        return getDate().getMonthOfYear();
    }

    @Override
    public String getLabel() {
        return "" + getPeriod();
    }
}
