package com.askokov.calendar.model;

import java.util.List;

import android.content.res.Resources;

public abstract class Period {
    private static final String DEFAULT_MSG = "Not defined";

    public enum Type {
        EMPTY,
        HOUR,
        DAY,
        MONTH
    }

    public Type getType() {
        return Type.EMPTY;
    }

    public List<Period> getChildren() {
        return null;
    };

    public List<Event> getEvents() {
        return null;
    }

    public abstract int eventCount();

    public abstract String getLabel();

    public String getDescription(final Resources resources, final int idIfPresent, final int idIfMissing) {
        if (getEvents() != null) {
            int size = getEvents().size();
            Event last = getEvents().get(size - 1);

            String msg = last.getDescription();
            return resources.getString(idIfPresent, msg, eventCount());
        }

        return resources.getString(idIfMissing);
    }
}
