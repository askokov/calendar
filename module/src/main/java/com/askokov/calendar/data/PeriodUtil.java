package com.askokov.calendar.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.askokov.calendar.period.Day;
import com.askokov.calendar.period.Event;
import com.askokov.calendar.period.EventPeriod;
import com.askokov.calendar.period.Hour;
import com.askokov.calendar.period.Month;
import com.askokov.calendar.period.Period;
import com.askokov.calendar.period.Type;
import org.joda.time.DateTime;

public class PeriodUtil {

    private PeriodUtil() {
    }

    public static Day byDay(Month month, int dayNumber) {
        Day d = new Day();
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

        d.setDate(new DateTime(month.getDate().getYear(), month.getDate().getMonthOfYear(), dayNumber + 1, 0, 0, 0, 0));

        if (month.getChildren() != null) {
            for (EventPeriod day : month.getChildren()) {
                if (dayNumber + 1 == day.getPeriod()) {
                    if (day.getChildren() != null) {
                        List<Hour> hours = new ArrayList<Hour>();

                        for (int i = 0; i < 24; i++) {
                            Hour hour = byHour(day, i);
                            hours.add(hour);
                        }

                        d.setChildren(hours);
                    }
                    break;
                }
            }
        }

        return d;
    }

    public static Hour byHour(EventPeriod day, int hourNumber) {
        Hour h = new Hour();
        h.setDate(new DateTime(day.getDate().getYear(), day.getDate().getMonthOfYear(), day.getDate().getDayOfMonth(), hourNumber, 0, 0, 0));

        if (day.getChildren() != null) {
            for (EventPeriod hour : day.getChildren()) {
                if (hour.getEvents() != null) {
                    List<Event> events = new ArrayList<Event>();
                    for (Event event : hour.getEvents()) {
                        if (day.getDate().getDayOfMonth() == event.getDateTime().getDayOfMonth()
                            && hourNumber == event.getDateTime().getHourOfDay()) {
                            events.add(event);
                        }
                    }

                    if (!events.isEmpty()) {
                        h.getEvents().addAll(events);
                    }
                }
            }
        }

        return h;
    }

    public static List<Period> createPeriod(Period current, int halfPeriod) {
        List<Period> list = new ArrayList<Period>();

        Period[] back = new Period[halfPeriod];
        Period[] forward = new Period[halfPeriod];

        if (Type.DAY == current.getType()) {
            for (int i = 0; i < halfPeriod; i++) {
                back[halfPeriod - i - 1] = new Period(current.getDate().minusDays(i + 1), Type.DAY);
            }

            for (int i = 0; i < halfPeriod; i++) {
                forward[i] = new Period(current.getDate().plusDays(i + 1), Type.DAY);
            }

            list.addAll(Arrays.asList(back));
            list.add(current);
            list.addAll(Arrays.asList(forward));

        } else if (Type.MONTH == current.getType()) {
            for (int i = 0; i < halfPeriod; i++) {
                back[halfPeriod - i - 1] = new Period(current.getDate().minusMonths(i + 1), Type.MONTH);
            }

            for (int i = 0; i < halfPeriod; i++) {
                forward[i] = new Period(current.getDate().plusMonths(i + 1), Type.MONTH);
            }

            list.addAll(Arrays.asList(back));
            list.add(current);
            list.addAll(Arrays.asList(forward));
        }

        return list;
    }
}
