package com.askokov.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.askokov.calendar.period.Period;
import com.danikula.aibolit.Aibolit;
import com.danikula.aibolit.annotation.InjectResource;
import com.danikula.aibolit.annotation.InjectView;


public class PeriodFragment extends Fragment {
    private static final String TAG = "PeriodFragment";

    @InjectResource(R.array.week)
    private String[] week;
    @InjectResource(R.array.month_name)
    private String[] monthName;
    @InjectResource(R.array.month_with_day)
    private String[] monthWithDay;

    @InjectView(R.id.textPeriod)
    private TextView mTextPeriod;
    @InjectView(R.id.day1)
    private TextView day1;
    @InjectView(R.id.day2)
    private TextView day2;
    @InjectView(R.id.day3)
    private TextView day3;
    @InjectView(R.id.day4)
    private TextView day4;
    @InjectView(R.id.day5)
    private TextView day5;
    @InjectView(R.id.day6)
    private TextView day6;
    @InjectView(R.id.day7)
    private TextView day7;

    private TextView[] mColumns;
    private Period current;

    public static PeriodFragment newInstance(Period current) {
        PeriodFragment fragment = new PeriodFragment();
        fragment.current = current;

        return fragment;
    }

    public PeriodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        View view = inflater.inflate(R.layout.fragment_period, container, false);
        Aibolit.doInjections(this, view);

        day1.setText(week[0]);
        day2.setText(week[1]);
        day3.setText(week[2]);
        day4.setText(week[3]);
        day5.setText(week[4]);
        day6.setText(week[5]);
        day7.setText(week[6]);

        mColumns = new TextView[] {day1, day2, day3, day4, day5, day6, day7};

        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCalendar();
    }

    public void setData(final Period current) {
        this.current = current;

        initCalendar();
    }

    private void initCalendar() {
        int dayOfWeek = current.getDate().getDayOfWeek() - 1;
        int month = current.getDate().getMonthOfYear() - 1;
        int day = current.getDate().getDayOfMonth();
        int year = current.getDate().getYear();

        switch (current.getType()) {
            case DAY:
                String textDay = String.format(monthWithDay[month], day, year);
                mTextPeriod.setText(textDay);
                break;

            case MONTH:
                String textMonth = String.format(monthName[month], year);
                mTextPeriod.setText(textMonth);
                break;
        }

        for(int i = 0; i < mColumns.length; i++) {
            if (i == dayOfWeek) {
                mColumns[i].setBackgroundResource(R.drawable.circle);
            } else {
                mColumns[i].setBackgroundResource(R.drawable.circle_grey);
            }
        }
    }
}
