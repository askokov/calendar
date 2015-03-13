package com.askokov.calendar.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.util.Pair;
import com.askokov.calendar.period.Day;
import com.askokov.calendar.period.EventPeriod;
import com.askokov.calendar.period.Hour;
import com.askokov.calendar.period.Month;
import com.askokov.calendar.period.Period;
import org.joda.time.DateTime;

public class CalendarFactory {

    private CalendarData calendarData;

    public CalendarFactory(Context context, String packageName) {
        calendarData = new CalendarData(context, packageName);
    }

    public List<Pair<EventPeriod, EventPeriod>> getPeriod(Period current) {
        List<Pair<EventPeriod, EventPeriod>> result = null;

        switch (current.getType()) {
            case DAY:
                Day d = calendarData.getDayModel(current.getDate());
                result = convertDayToPairs(d, current.getDate());

                break;

            case MONTH:
                Month m = calendarData.getMonthModel(current.getDate());
                result = convertMonthToPairs(m, current.getDate());

                break;
        }

        return result;
    }

    private List<Pair<EventPeriod, EventPeriod>> convertDayToPairs(Day day, DateTime date) {
        List<Pair<EventPeriod, EventPeriod>> result = new ArrayList<Pair<EventPeriod, EventPeriod>>();

        if (day != null) {
            //24 hours = 12 hour pairs
            for (int i = 0; i < 12; i++) {
                Hour first = PeriodUtil.byHour(day, i * 2);
                Hour second = PeriodUtil.byHour(day, i * 2 + 1);

                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, second);
                result.add(pair);
            }
        } else {
            //24 hours = 12 hour pairs
            for (int i = 0; i < 12; i++) {
                Hour first = new Hour();
                first.setDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), i * 2, 0, 0, 0));

                Hour second = new Hour();
                second.setDate(new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), i * 2 + 1, 0, 0, 0));

                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, second);
                result.add(pair);
            }
        }

        return result;
    }

    private List<Pair<EventPeriod, EventPeriod>> convertMonthToPairs(Month month, DateTime date) {
        List<Pair<EventPeriod, EventPeriod>> result = new ArrayList<Pair<EventPeriod, EventPeriod>>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getMillis());
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int pairs = maxDays / 2;
        int modulo = maxDays % 2;

        if (month != null) {
            for (int i = 0; i < pairs; i++) {
                EventPeriod first = PeriodUtil.byDay(month, i * 2);
                EventPeriod second = PeriodUtil.byDay(month, i * 2 + 1);

                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                EventPeriod first = PeriodUtil.byDay(month, maxDays - 1);
                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, null);
                result.add(pair);
            }
        } else {
            for (int i = 0; i < pairs; i++) {
                Day first = new Day();
                first.setDate(new DateTime(date.getYear(), date.getMonthOfYear(), i * 2 + 1, 0, 0, 0, 0));

                Day second = new Day();
                second.setDate(new DateTime(date.getYear(), date.getMonthOfYear(), i * 2 + 2, 0, 0, 0, 0));

                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                Day first = new Day();
                first.setDate(new DateTime(date.getYear(), date.getMonthOfYear(), maxDays, 0, 0, 0, 0));

                Pair<EventPeriod, EventPeriod> pair = new Pair<EventPeriod, EventPeriod>(first, null);
                result.add(pair);
            }
        }

        return result;
    }
}
