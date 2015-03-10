package com.askokov.calendar.model;

import java.util.ArrayList;
import java.util.List;

public class ModelUtil {

    private ModelUtil() {
    }

    public static Day byDay(List<Period> days, int dayBased0) {
        int day = dayBased0 + 1;
        Day d = new Day();
        d.setDay(day);

        if (days != null) {
            for (Period period : days) {
                if (day == ((Day)period).getDay()) {
                    if (period.getChildren() != null) {
                        List<Period> hours = new ArrayList<Period>();

                        for (int i = 0; i < 24; i++) {
                            Period hour = byHour(period.getChildren(), day, i);
                            hours.add(hour);
                        }

                        d.setHours(hours);
                    }
                    break;
                }
            }
        }

        return d;
    }

    public static Hour byHour(List<Period> hours, int day, int hour) {
        Hour h = new Hour();
        h.setHour(hour);

        if (hours != null) {
            for (Period p : hours) {
                if (p.getEvents() != null) {
                    List<Event> events = new ArrayList<Event>();
                    for (Event event : p.getEvents()) {
                        if (day == event.getDateTime().getDayOfMonth() && hour == event.getDateTime().getHourOfDay()) {
                            events.add(event);
                        }
                    }

                    if (!events.isEmpty()) {
                        h.setEvents(events);
                    }
                }
            }
        }

        return h;
    }
}
