package com.askokov.calendar.period;

import org.joda.time.DateTime;

public class Period {

    private Type type;
    private DateTime date;

    public Period() {
    }

    public Period(final DateTime date, final Type type) {
        this.date = date;
        this.type = type;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Period period = (Period) o;

        if (!date.equals(period.date)) return false;
        if (type != period.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
