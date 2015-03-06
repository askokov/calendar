package com.askokov.calendar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Pair;
import com.askokov.calendar.dao.CalendarDao;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Hour;
import com.askokov.calendar.model.Month;
import com.askokov.calendar.model.Period;

public class CalendarService {

    private CalendarDao calendarDao;

    public CalendarService(Context context, String packageName) {
        calendarDao = new CalendarDao(context, packageName);
    }

    public List<Pair<Period, Period>> getPeriod(Date date, Period.Type type) {
        List<Pair<Period, Period>> result = null;

        switch (type) {
            case DAY:
                Day d = calendarDao.getDayModel(date);
                result = convertDayToPairs(d);

                break;

            case MONTH:
                Month m = calendarDao.getMonthModel(date);
                result = convertMonthToPairs(m, date);
                setCurrentDay(date, result);

                break;
        }

        return result;
    }

    private void setCurrentDay(Date date, List<Pair<Period, Period>> pairs) {
        int day = CalendarUtil.getDayOfMonth(date) - 1;

        int rowN = day / 2;
        int colN = day % 2;

        Pair<Period, Period> row = pairs.get(rowN);
        Day d = colN == 0 ? (Day)row.first : (Day)row.second;
        d.setCurrent(true);
    }

    private List<Pair<Period, Period>> convertDayToPairs(Day day) {
        List<Pair<Period, Period>> result = new ArrayList<Pair<Period, Period>>();

        if (day != null) {
            List<Period> hours = day.getChildren();

            //24 hours = 12 hour pairs
            for (int i = 0; i < 12; i++) {
                Hour first = CalendarUtil.byHour(hours, day.getDay(), i * 2);
                Hour second = CalendarUtil.byHour(hours, day.getDay(), i * 2 + 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }
        } else {
            //24 hours = 12 hour pairs
            for (int i = 0; i < 12; i++) {
                Hour first = new Hour();
                first.setHour(i * 2);
                Hour second = new Hour();
                second.setHour(i * 2 + 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }
        }

        return result;
    }

    private List<Pair<Period, Period>> convertMonthToPairs(Month month, Date date) {
        List<Pair<Period, Period>> result = new ArrayList<Pair<Period, Period>>();

        int maxDays = CalendarUtil.daysByMonth(date);
        int pairs = maxDays / 2;
        int modulo = maxDays % 2;

        if (month != null) {
            List<Period> days = month.getChildren();

            for (int i = 0; i < pairs; i++) {
                Period first = CalendarUtil.byDay(days, i * 2);
                Period second = CalendarUtil.byDay(days, i * 2 + 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                Period first = CalendarUtil.byDay(days, maxDays - 1);
                Pair<Period, Period> pair = new Pair<Period, Period>(first, null);
                result.add(pair);
            }
        } else {
            for (int i = 0; i < pairs; i++) {
                Day first = new Day();
                first.setDay(i * 2);

                Day second = new Day();
                second.setDay(i * 2 + 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                Day first = new Day();
                first.setDay(maxDays - 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, null);
                result.add(pair);
            }
        }

        return result;
    }
}
