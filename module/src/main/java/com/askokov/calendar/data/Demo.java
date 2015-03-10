package com.askokov.calendar.data;

import java.util.ArrayList;
import java.util.List;

import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Event;
import com.askokov.calendar.model.Hour;
import com.askokov.calendar.model.Month;
import com.askokov.calendar.model.Period;
import org.joda.time.DateTime;

public class Demo {

    /*
    DateTime dateTime = new DateTime(
      2000, //year
      1,    // month
      1,    // day
      0,    // hour (midnight is zero)
      0,    // minute
      0,    // second
      0     // milliseconds
    );
     */
    private static DateTime hour11 = new DateTime(2015,3,1,12,0,0,0);
    private static DateTime hour12 = new DateTime(2015,3,1,12,20,0,0);
    private static DateTime hour13 = new DateTime(2015,3,1,12,40,0,0);
    private static DateTime hour2 = new DateTime(2015,3,1,13,20,0,0);
    private static DateTime hour31 = new DateTime(2015,3,1,17,40,0,0);
    private static DateTime hour32 = new DateTime(2015,3,1,17,55,0,0);
    private static DateTime hour4 = new DateTime(2015,3,4,14,30,0,0);
    private static DateTime hour51 = new DateTime(2015,3,5,10,0,0,0);
    private static DateTime hour52 = new DateTime(2015,3,5,10,30,0,0);
    private static DateTime hour6 = new DateTime(2015,3,5,23,30,0,0);
    private static DateTime hour7 = new DateTime(2015,3,7,11,0,0,0);
    private static DateTime hour8 = new DateTime(2015,3,7,15,0,0,0);
    private static DateTime hour91 = new DateTime(2015,3,8,0,5,0,0);
    private static DateTime hour92 = new DateTime(2015,3,8,23,55,0,0);
    private static DateTime hour101 = new DateTime(2015,4,2,16,15,0,0);
    private static DateTime hour102 = new DateTime(2015,4,2,16,45,0,0);
    private static DateTime hour110 = new DateTime(2015,4,2,18,5,0,0);
    private static DateTime hour120 = new DateTime(2015,4,3,10,0,0,0);
    private static DateTime hour130 = new DateTime(2015,4,3,18,0,0,0);
    private static DateTime hour140 = new DateTime(2015,4,29,9,0,0,0);
    private static DateTime hour150 = new DateTime(2015,4,30,11,0,0,0);

    private Demo() {
    }

    private static Hour getHour1() {
        Hour h = new Hour();
        h.setHour(hour11.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour11, "Event 11"));
        events.add(new Event(hour12, "Event 12"));
        events.add(new Event(hour13, "Event 13"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour2() {
        Hour h = new Hour();
        h.setHour(hour2.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour2, "Event 2"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour3() {
        Hour h = new Hour();
        h.setHour(hour31.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour31, "Event 31"));
        events.add(new Event(hour32, "Event 32"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour4() {
        Hour h = new Hour();
        h.setHour(hour4.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour4, "Event 4"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour5() {
        Hour h = new Hour();
        h.setHour(hour51.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour51, "Event 51"));
        events.add(new Event(hour52, "Event 52"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour6() {
        Hour h = new Hour();
        h.setHour(hour6.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour6, "Event 6"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour7() {
        Hour h = new Hour();
        h.setHour(hour7.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour7, "Event 7"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour8() {
        Hour h = new Hour();
        h.setHour(hour8.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour8, "Event 8"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour91() {
        Hour h = new Hour();
        h.setHour(hour91.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour91, "Event 91"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour92() {
        Hour h = new Hour();
        h.setHour(hour92.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour92, "Event 92"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour10() {
        Hour h = new Hour();
        h.setHour(hour101.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour101, "Event 101"));
        events.add(new Event(hour102, "Event 102"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour11() {
        Hour h = new Hour();
        h.setHour(hour110.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour110, "Event 110"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour12() {
        Hour h = new Hour();
        h.setHour(hour120.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour120, "Event 120"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour13() {
        Hour h = new Hour();
        h.setHour(hour130.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour130, "Event 130"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour14() {
        Hour h = new Hour();
        h.setHour(hour140.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour140, "Event 140"));

        h.setEvents(events);

        return h;
    }

    private static Hour getHour15() {
        Hour h = new Hour();
        h.setHour(hour150.getHourOfDay());

        List<Event> events = new ArrayList<Event>();
        events.add(new Event(hour150, "Event 150"));

        h.setEvents(events);

        return h;
    }

    private static Day getDate1() {
        Day day = new Day();
        day.setDay(hour11.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour1());
        hours.add(getHour2());
        hours.add(getHour3());

        day.setHours(hours);

        return day;
    }

    private static Day getDate2() {
        Day day = new Day();
        day.setDay(hour4.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour4());

        day.setHours(hours);

        return day;
    }

    private static Day getDate3() {
        Day day = new Day();
        day.setDay(hour51.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour5());
        hours.add(getHour6());

        day.setHours(hours);

        return day;
    }

    private static Day getDate4() {
        Day day = new Day();
        day.setDay(hour7.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour7());
        hours.add(getHour8());

        day.setHours(hours);

        return day;
    }

    private static Day getDate5() {
        Day day = new Day();
        day.setDay(hour91.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour91());
        hours.add(getHour92());

        day.setHours(hours);

        return day;
    }

    private static Day getDate6() {
        Day day = new Day();
        day.setDay(hour101.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour10());
        hours.add(getHour11());

        day.setHours(hours);

        return day;
    }

    private static Day getDate7() {
        Day day = new Day();
        day.setDay(hour120.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour12());
        hours.add(getHour13());

        day.setHours(hours);

        return day;
    }

    private static Day getDate8() {
        Day day = new Day();
        day.setDay(hour140.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour14());

        day.setHours(hours);

        return day;
    }

    private static Day getDate9() {
        Day day = new Day();
        day.setDay(hour150.getDayOfMonth());

        List<Period> hours = new ArrayList<Period>();
        hours.add(getHour15());

        day.setHours(hours);

        return day;
    }

    private static Month getMonth1() {
        Month month = new Month();
        month.setMonth(hour11.getMonthOfYear());

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
        month.setMonth(hour101.getMonthOfYear());

        List<Period> days = new ArrayList<Period>();
        days.add(getDate6());
        days.add(getDate7());
        days.add(getDate8());
        days.add(getDate9());

        month.setDays(days);

        return month;
    }

    public static Month createMonthModel(DateTime date) {
        Month period = null;
        int month = date.getMonthOfYear();

        switch (month) {
            case 3:
                period = getMonth1();
                break;
            case 4:
                period = getMonth2();
                break;
            default:
                break;
        }

        return period;
    }

    public static Day createDayModel(DateTime date) {
        Day period = null;
        int month = date.getMonthOfYear();
        int day = date.getDayOfMonth();

        switch (month) {
            case 3:
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
            case 4:
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
