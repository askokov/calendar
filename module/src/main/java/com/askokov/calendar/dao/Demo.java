package com.askokov.calendar.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Event;
import com.askokov.calendar.model.Hour;
import com.askokov.calendar.model.Month;
import com.askokov.calendar.model.Period;
import com.askokov.calendar.service.CalendarUtil;

public class Demo {

    private static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static Date hour11;
    private static Date hour12;
    private static Date hour13;
    private static Date hour2;
    private static Date hour31;
    private static Date hour32;
    private static Date hour4;
    private static Date hour51;
    private static Date hour52;
    private static Date hour6;
    private static Date hour7;
    private static Date hour8;
    private static Date hour91;
    private static Date hour92;
    private static Date hour101;
    private static Date hour102;
    private static Date hour110;
    private static Date hour120;
    private static Date hour130;
    private static Date hour140;
    private static Date hour150;

    static {
        try {
            hour11 = DF.parse("2015-03-01 12:00");
            hour12 = DF.parse("2015-03-01 12:20");
            hour13 = DF.parse("2015-03-01 12:40");

            hour2 = DF.parse("2015-03-01 13:20");

            hour31 = DF.parse("2015-03-01 17:40");
            hour32 = DF.parse("2015-03-01 17:55");

            hour4 = DF.parse("2015-03-04 14:30");

            hour51 = DF.parse("2015-03-05 10:00");
            hour52 = DF.parse("2015-03-05 10:30");

            hour6 = DF.parse("2015-03-05 23:30");

            hour7 = DF.parse("2015-03-07 11:00");
            hour8 = DF.parse("2015-03-07 15:00");

            hour91 = DF.parse("2015-03-08 00:05");
            hour92 = DF.parse("2015-03-08 23:55");

            hour101 = DF.parse("2015-04-02 16:15");
            hour102 = DF.parse("2015-04-02 16:45");

            hour110 = DF.parse("2015-04-02 18:05");

            hour120 = DF.parse("2015-04-03 10:00");
            hour130 = DF.parse("2015-04-03 18:00");

            hour140 = DF.parse("2015-04-29 9:00");
            hour150 = DF.parse("2015-04-30 11:00");
        } catch (ParseException ex) {

        }
    }

    private Demo() {
    }

    private static Hour getHour1() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour11));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour11, "Event 11"));
        events.add(new Event(hour12, "Event 12"));
        events.add(new Event(hour13, "Event 13"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour2() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour2));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour2, "Event 2"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour3() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour31));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour31, "Event 31"));
        events.add(new Event(hour32, "Event 32"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour4() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour4));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour4, "Event 4"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour5() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour51));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour51, "Event 51"));
        events.add(new Event(hour52, "Event 52"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour6() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour6));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour6, "Event 6"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour7() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour7));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour7, "Event 7"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour8() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour8));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour8, "Event 8"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour91() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour91));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour91, "Event 91"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour92() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour92));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour92, "Event 92"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour10() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour101));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour101, "Event 101"));
        events.add(new Event(hour102, "Event 102"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour11() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour110));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour110, "Event 110"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour12() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour120));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour120, "Event 120"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour13() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour130));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour130, "Event 130"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour14() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour140));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour140, "Event 140"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour15() {
        Hour h = new Hour();
        h.setHour(CalendarUtil.getHourOfDay(hour150));

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour150, "Event 150"));

        h.setEvents(events);

        return h;
    }

    private static Day getDate1() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour11));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour1());
        hours.add(getHour2());
        hours.add(getHour3());

        day.setHours(hours);

        return day;
    }

    private static Day getDate2() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour4));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour4());

        day.setHours(hours);

        return day;
    }

    private static Day getDate3() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour51));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour5());
        hours.add(getHour6());

        day.setHours(hours);

        return day;
    }

    private static Day getDate4() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour7));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour7());
        hours.add(getHour8());

        day.setHours(hours);

        return day;
    }

    private static Day getDate5() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour91));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour91());
        hours.add(getHour92());

        day.setHours(hours);

        return day;
    }

    private static Day getDate6() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour101));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour10());
        hours.add(getHour11());

        day.setHours(hours);

        return day;
    }

    private static Day getDate7() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour120));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour12());
        hours.add(getHour13());

        day.setHours(hours);

        return day;
    }

    private static Day getDate8() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour140));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour14());

        day.setHours(hours);

        return day;
    }

    private static Day getDate9() {
        Day day = new Day();
        day.setDay(CalendarUtil.getDayOfMonth(hour150));

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour15());

        day.setHours(hours);

        return day;
    }

    private static Month getMonth1() {
        Month month = new Month();
        month.setMonth(CalendarUtil.getMonth(hour11));

        List<Period> days = new ArrayList<Period>();
        days.add(getDate1());
        days.add(getDate2());
        days.add(getDate3());
        days.add(getDate4());
        days.add(getDate5());

        month.setDays(days);

        return month;
    }

    private static Month getMonth2() {
        Month month = new Month();
        month.setMonth(CalendarUtil.getMonth(hour101));

        List<Period> days = new ArrayList<Period>();
        days.add(getDate6());
        days.add(getDate7());
        days.add(getDate8());
        days.add(getDate9());

        month.setDays(days);

        return month;
    }

    public static Month createMonthModel(Date date) {
        Month period = null;
        int month = CalendarUtil.getMonth(date);

        switch (month) {
            case 2:
                period = getMonth1();
                break;
            case 3:
                period = getMonth2();
                break;
            default:
                break;
        }

        return period;
    }

    public static Day createDayModel(Date date) {
        Day period = null;
        int month = CalendarUtil.getMonth(date);
        int day = CalendarUtil.getDayOfMonth(date);

        switch (month) {
            case 2:
                switch (day) {
                    case 1:
                        period = getDate1();
                        break;
                    case 4:
                        period = getDate2();
                        break;
                    case 5:
                        period = getDate3();
                        break;
                    case 7:
                        period = getDate4();
                        break;
                    case 8:
                        period = getDate5();
                        break;
                }
            case 3:
                switch (day) {
                    case 2:
                        period = getDate6();
                        break;
                    case 3:
                        period = getDate7();
                        break;
                    case 29:
                        period = getDate8();
                        break;
                    case 30:
                        period = getDate9();
                        break;
                }

            default:
                break;

        }

        return period;
    }
}
