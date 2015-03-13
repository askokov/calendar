package com.askokov.calendar.data;

import java.util.Arrays;

import com.askokov.calendar.period.Day;
import com.askokov.calendar.period.Event;
import com.askokov.calendar.period.Hour;
import com.askokov.calendar.period.Month;
import org.joda.time.DateTime;

public class DemoPeriod {

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

    private DemoPeriod() {
    }

    private static Hour getHour1() {
        Hour h = new Hour();
        h.setDate(hour11);

        h.getEvents().addAll(
            Arrays.asList(new Event(hour11, "Event 11"), new Event(hour12, "Event 12"), new Event(hour13, "Event 13")));

        return h;
    }

    private static Hour getHour2() {
        Hour h = new Hour();
        h.setDate(hour2);

        h.getEvents().addAll(Arrays.asList(new Event(hour2, "Event 2")));

        return h;
    }

    private static Hour getHour3() {
        Hour h = new Hour();
        h.setDate(hour31);

        h.getEvents().addAll(
            Arrays.asList(new Event(hour31, "Event 31"), new Event(hour32, "Event 32")));

        return h;
    }

    private static Hour getHour4() {
        Hour h = new Hour();
        h.setDate(hour4);

        h.getEvents().addAll(Arrays.asList(new Event(hour4, "Event 4")));

        return h;
    }

    private static Hour getHour5() {
        Hour h = new Hour();
        h.setDate(hour51);

        h.getEvents().addAll(Arrays.asList(new Event(hour51, "Event 51"), new Event(hour52, "Event 52")));

        return h;
    }

    private static Hour getHour6() {
        Hour h = new Hour();
        h.setDate(hour6);

        h.getEvents().addAll(Arrays.asList(new Event(hour6, "Event 6")));

        return h;
    }

    private static Hour getHour7() {
        Hour h = new Hour();
        h.setDate(hour7);

        h.getEvents().addAll(Arrays.asList(new Event(hour7, "Event 7")));

        return h;
    }

    private static Hour getHour8() {
        Hour h = new Hour();
        h.setDate(hour8);

        h.getEvents().addAll(Arrays.asList(new Event(hour8, "Event 8")));

        return h;
    }

    private static Hour getHour91() {
        Hour h = new Hour();
        h.setDate(hour91);

        h.getEvents().addAll(Arrays.asList(new Event(hour91, "Event 91")));

        return h;
    }

    private static Hour getHour92() {
        Hour h = new Hour();
        h.setDate(hour92);

        h.getEvents().addAll(Arrays.asList(new Event(hour91, "Event 92")));

        return h;
    }

    private static Hour getHour10() {
        Hour h = new Hour();
        h.setDate(hour101);

        h.getEvents().addAll(Arrays.asList(new Event(hour101, "Event 101"), new Event(hour102, "Event 102")));

        return h;
    }

    private static Hour getHour11() {
        Hour h = new Hour();
        h.setDate(hour110);

        h.getEvents().addAll(Arrays.asList(new Event(hour110, "Event 110")));

        return h;
    }

    private static Hour getHour12() {
        Hour h = new Hour();
        h.setDate(hour120);

        h.getEvents().addAll(Arrays.asList(new Event(hour120, "Event 120")));

        return h;
    }

    private static Hour getHour13() {
        Hour h = new Hour();
        h.setDate(hour130);

        h.getEvents().addAll(Arrays.asList(new Event(hour130, "Event 130")));

        return h;
    }

    private static Hour getHour14() {
        Hour h = new Hour();
        h.setDate(hour140);

        h.getEvents().addAll(Arrays.asList(new Event(hour140, "Event 140")));

        return h;
    }

    private static Hour getHour15() {
        Hour h = new Hour();
        h.setDate(hour150);

        h.getEvents().addAll(Arrays.asList(new Event(hour150, "Event 150")));

        return h;
    }

    private static Day getDate1() {
        Day day = new Day();
        day.setDate(hour11);

        day.setChildren(Arrays.asList(getHour1(), getHour2(), getHour3()));

        return day;
    }

    private static Day getDate2() {
        Day day = new Day();
        day.setDate(hour4);

        day.setChildren(Arrays.asList(getHour4()));

        return day;
    }

    private static Day getDate3() {
        Day day = new Day();
        day.setDate(hour51);

        day.setChildren(Arrays.asList(getHour5(), getHour6()));

        return day;
    }

    private static Day getDate4() {
        Day day = new Day();
        day.setDate(hour7);

        day.setChildren(Arrays.asList(getHour7(), getHour8()));

        return day;
    }

    private static Day getDate5() {
        Day day = new Day();
        day.setDate(hour91);

        day.setChildren(Arrays.asList(getHour91(), getHour92()));

        return day;
    }

    private static Day getDate6() {
        Day day = new Day();
        day.setDate(hour101);

        day.setChildren(Arrays.asList(getHour10(), getHour11()));

        return day;
    }

    private static Day getDate7() {
        Day day = new Day();
        day.setDate(hour120);

        day.setChildren(Arrays.asList(getHour12(), getHour13()));

        return day;
    }

    private static Day getDate8() {
        Day day = new Day();
        day.setDate(hour140);

        day.setChildren(Arrays.asList(getHour14()));

        return day;
    }

    private static Day getDate9() {
        Day day = new Day();
        day.setDate(hour150);

        day.setChildren(Arrays.asList(getHour15()));

        return day;
    }

    private static Month getMonth1() {
        Month month = new Month();
        month.setDate(hour11);

        month.setChildren(Arrays.asList(getDate1(), getDate2(), getDate3(), getDate4(), getDate5()));

        return month;
    }

    private static Month getMonth2() {
        Month month = new Month();
        month.setDate(hour101);

        month.setChildren(Arrays.asList(getDate6(), getDate7(), getDate8(), getDate9()));

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
