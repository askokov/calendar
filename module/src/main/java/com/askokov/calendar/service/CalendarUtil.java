package com.askokov.calendar.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Event;
import com.askokov.calendar.model.Hour;
import com.askokov.calendar.model.Period;

public class CalendarUtil {
    private static DateFormat DF = new SimpleDateFormat("HH:mm");

    private CalendarUtil() {
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH);
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHourOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int firstDayOfWeek = calendar.getFirstDayOfWeek();

        return calendar.get(Calendar.DAY_OF_WEEK) - (firstDayOfWeek - 1);
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    public static String formatHour(Date date) {
        return DF.format(date);
    }

    public static Date previousMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);

        return calendar.getTime();
    }

    public static Date nextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);

        return calendar.getTime();
    }

    public static Date previousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        return calendar.getTime();
    }

    public static Date nextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static int daysByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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
                        if (day == getDayOfMonth(event.getDateTime()) && hour == getHourOfDay(event.getDateTime())) {
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
