package com.askokov.calendar.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.util.Pair;
import com.askokov.calendar.data.CalendarData;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Hour;
import com.askokov.calendar.model.ModelUtil;
import com.askokov.calendar.model.Month;
import com.askokov.calendar.model.Period;
import org.joda.time.DateTime;

public class CalendarFactory {

    private CalendarData calendarData;

    public CalendarFactory(Context context, String packageName) {
        calendarData = new CalendarData(context, packageName);
    }

    public List<Pair<Period, Period>> getPeriod(DateTime date, Period.Type type) {
        List<Pair<Period, Period>> result = null;

        switch (type) {
            case DAY:
                Day d = calendarData.getDayModel(date);
                result = convertDayToPairs(d);

                break;

            case MONTH:
                Month m = calendarData.getMonthModel(date);
                result = convertMonthToPairs(m, date);
                setCurrentDay(date, result);

                break;
        }

        return result;
    }

    private void setCurrentDay(DateTime date, List<Pair<Period, Period>> pairs) {
        int day = date.getDayOfMonth() - 1;

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
                Hour first = ModelUtil.byHour(hours, day.getDay(), i * 2);
                Hour second = ModelUtil.byHour(hours, day.getDay(), i * 2 + 1);

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

    private List<Pair<Period, Period>> convertMonthToPairs(Month month, DateTime date) {
        List<Pair<Period, Period>> result = new ArrayList<Pair<Period, Period>>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getMillis());
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int pairs = maxDays / 2;
        int modulo = maxDays % 2;

        if (month != null) {
            List<Period> days = month.getChildren();

            for (int i = 0; i < pairs; i++) {
                Period first = ModelUtil.byDay(days, i * 2);
                Period second = ModelUtil.byDay(days, i * 2 + 1);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                Period first = ModelUtil.byDay(days, maxDays - 1);
                Pair<Period, Period> pair = new Pair<Period, Period>(first, null);
                result.add(pair);
            }
        } else {
            for (int i = 0; i < pairs; i++) {
                Day first = new Day();
                first.setDay(i * 2 + 1);

                Day second = new Day();
                second.setDay(i * 2 + 2);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, second);
                result.add(pair);
            }

            if (modulo > 0) {
                Day first = new Day();
                first.setDay(maxDays);

                Pair<Period, Period> pair = new Pair<Period, Period>(first, null);
                result.add(pair);
            }
        }

        return result;
    }
}
