package com.askokov.calendar;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.askokov.calendar.adapter.CalendarAdapter;
import com.askokov.calendar.adapter.CalendarPagerAdapter;
import com.askokov.calendar.adapter.PeriodAdapter;
import com.askokov.calendar.data.CalendarFactory;
import com.askokov.calendar.data.PeriodUtil;
import com.askokov.calendar.listener.CalendarPositionListener;
import com.askokov.calendar.period.EventPeriod;
import com.askokov.calendar.period.Period;
import com.askokov.calendar.period.Type;
import org.joda.time.DateTime;


public class MainActivity extends FragmentActivity implements PeriodFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    private static final String POSITION = "position";
    private static final int HALF_PERIOD = 2;

    private MenuItem mMenuMonth;
    private MenuItem mMenuDay;

    private Period current;
    private CalendarAdapter mAdapter;
    CalendarListView mListView;
    private CalendarViewPager mCalendarPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        current = new Period(new DateTime(2015, 3, 1, 0, 0, 0, 0), Type.MONTH);

        //int position = savedInstanceState != null ? savedInstanceState.getInt(POSITION, 0) : 0;
        CalendarFactory calendarFactory = new CalendarFactory(this, getPackageName());
        mAdapter = new CalendarAdapter(this, new CalendarPositionListener(), calendarFactory);
        mAdapter.setPeriodClickListener(new PeriodAdapter.PeriodClickListener() {
            @Override
            public void onPeriodClick(final EventPeriod period) {
                Log.i(TAG, "onPeriodClick: type=" + period.getType() + ", label=" + period.getLabel());
            }
        });

        mCalendarPager = (CalendarViewPager) findViewById(R.id.periodPager);
        mCalendarPager.setPageMargin(10);
        mCalendarPager.setOnChangePeriodListener(new CalendarViewPager.OnChangePeriodListener() {
            @Override
            public void onPeriodChange(final Period changed) {
                current = changed;
                initCalendarAdapter();
            }

            @Override
            public void onExtendPeriodChange(final Period changed) {
                current = changed;
                initPagerAdapter();

                initCalendarAdapter();
            }
        });

        mListView = (CalendarListView) findViewById(R.id.calendarList);
        initPagerAdapter();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: outState=" + outState);
        outState.putInt(POSITION, mCalendarPager.getCurrentItem());
        //super.onSaveInstanceState(outState);
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
                current = new Period(new DateTime(current.getDate().getYear(), current.getDate().getMonthOfYear(), current.getDate().getDayOfMonth(), 0, 0, 0, 0), Type.MONTH);

                break;

            case R.id.menu_day:
                current = new Period(new DateTime(current.getDate().getYear(), current.getDate().getMonthOfYear(), current.getDate().getDayOfMonth(), 0, 0, 0, 0), Type.DAY);

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        initActionBar();
        initPagerAdapter();
        initCalendarAdapter();

        return true;
    }

    private void initPagerAdapter() {
        List<Period> periodList = PeriodUtil.createPeriod(current, HALF_PERIOD);

        final CalendarPagerAdapter calendarPagerAdapter =
            new CalendarPagerAdapter(getSupportFragmentManager(), periodList);

        mCalendarPager.setAdapter(calendarPagerAdapter);
        mCalendarPager.setCurrentItem(HALF_PERIOD);
    }

    @Override
    public void onFragmentInteraction(final Period changed) {
        Log.i(TAG, "onFragmentInteraction");
        this.current = changed;

        initCalendarAdapter();
    }

    private void initActionBar() {
        switch (current.getType()) {
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

    private void initCalendarAdapter() {
        mAdapter.setContent(current);

        if (!mListView.isAdapter()) {
            mListView.setAdapter(mAdapter);
        }

        mAdapter.notifyDataSetChanged();
    }
}
