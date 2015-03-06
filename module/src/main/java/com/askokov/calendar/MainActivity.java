package com.askokov.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.askokov.calendar.model.Period;
import com.askokov.calendar.service.CalendarService;
import com.askokov.calendar.service.CalendarUtil;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    private MenuItem mMenuMonth;
    private MenuItem mMenuDay;
    private TextView mTextPeriod;
    private TextView[] mColumns = new TextView[7];

    private Date current;
    private Period.Type periodType;
    private String[] monthName;
    private String[] monthWithDay;
    private CalendarDetailAdapter mAdapter;

    private CalendarService calendarService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        periodType = Period.Type.MONTH;
        try {
            current = DF.parse("2015-03-01");
        } catch (ParseException ex) {
            Log.i(TAG, ex.getMessage());
        }

        String[] week = getResources().getStringArray(R.array.week);
        monthName = getResources().getStringArray(R.array.month_name);
        monthWithDay = getResources().getStringArray(R.array.month_with_day);

        ImageButton btnPrev = (ImageButton)findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (periodType) {
                    case DAY:
                        current = CalendarUtil.previousDay(current);
                        break;

                    case MONTH:
                        current = CalendarUtil.previousMonth(current);
                        break;
                }
                initCalendar();
                initEvents();
            }
        });

        ImageButton btnNext = (ImageButton)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (periodType) {
                    case DAY:
                        current = CalendarUtil.nextDay(current);

                        break;

                    case MONTH:
                        current = CalendarUtil.nextMonth(current);

                        break;
                }
                initCalendar();
                initEvents();
            }
        });

        mTextPeriod = (TextView)findViewById(R.id.textPeriod);

        TextView day1 = (TextView)findViewById(R.id.day1);
        day1.setText(week[0]);

        TextView day2 = (TextView)findViewById(R.id.day2);
        day2.setText(week[1]);

        TextView day3 = (TextView)findViewById(R.id.day3);
        day3.setText(week[2]);

        TextView day4 = (TextView)findViewById(R.id.day4);
        day4.setText(week[3]);

        TextView day5 = (TextView)findViewById(R.id.day5);
        day5.setText(week[4]);

        TextView day6 = (TextView)findViewById(R.id.day6);
        day6.setText(week[5]);

        TextView day7 = (TextView)findViewById(R.id.day7);
        day7.setText(week[6]);

        mColumns[0] = day1;
        mColumns[1] = day2;
        mColumns[2] = day3;
        mColumns[3] = day4;
        mColumns[4] = day5;
        mColumns[5] = day6;
        mColumns[6] = day7;

        initCalendar();
        calendarService = new CalendarService(this, getPackageName());

        mAdapter = new CalendarDetailAdapter(this);
        mAdapter.setObjects(calendarService.getPeriod(current, periodType));

        ListView mListView = (ListView) findViewById(R.id.calendarList);
        mListView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu");

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.i(TAG, "onPrepareOptionsMenu");

        mMenuMonth = menu.findItem(R.id.menu_month);
        mMenuDay = menu.findItem(R.id.menu_day);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_month:
                periodType = Period.Type.MONTH;

                break;

            case R.id.menu_day:
                periodType = Period.Type.DAY;

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        initActionBar(periodType);
        initCalendar();
        initEvents();

        return true;
    }

    private void initActionBar(Period.Type type) {
        switch (type) {
            case MONTH:
                mMenuDay.setChecked(false);
                mMenuMonth.setChecked(true);

                break;
            case DAY:
                mMenuMonth.setChecked(false);
                mMenuDay.setChecked(true);

                break;
        }
    }

    private void initCalendar() {
        int month = CalendarUtil.getMonth(current);
        int dayOfMonth = CalendarUtil.getDayOfMonth(current);
        int dayOfWeek = CalendarUtil.getDayOfWeek(current);
        int year = CalendarUtil.getYear(current);

        switch (periodType) {
            case DAY:
                String textDay = String.format(monthWithDay[month], dayOfMonth, year);
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

    private void initEvents() {
        mAdapter.setObjects(calendarService.getPeriod(current, periodType));
        mAdapter.notifyDataSetChanged();
    }
}
