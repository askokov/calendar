package com.askokov.calendar;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
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
import com.danikula.aibolit.Aibolit;
import com.danikula.aibolit.annotation.InjectView;
import org.joda.time.DateTime;


public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";

    private static final String POSITION = "position";
    private static final String CURRENT = "current";
    private static final int HALF_PERIOD = 2;

    private MenuItem mMenuMonth;
    private MenuItem mMenuDay;
    private CalendarAdapter mAdapter;
    private CalendarPagerAdapter mCalendarPagerAdapter;

    @InjectView(R.id.calendarList)
    private CalendarListView mListView;
    @InjectView(R.id.periodPager)
    private WrappedViewPager mCalendarPager;

    private Period current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Aibolit.setInjectedContentView(this, R.layout.main_layout);
        //setContentView(R.layout.main_layout);
        //Aibolit.doInjections(this);

        Period def = new Period(new DateTime(2015, 3, 1, 0, 0, 0, 0), Type.MONTH);
        current = savedInstanceState != null ? (Period)savedInstanceState.getParcelable(CURRENT) : def;
        int position = savedInstanceState != null ? savedInstanceState.getInt(POSITION, HALF_PERIOD) : HALF_PERIOD;

        //mCalendarPager = (CalendarViewPager) findViewById(R.id.periodPager);
        mCalendarPager.setPageMargin(10);
        initPagerAdapter(position);

        CalendarFactory calendarFactory = new CalendarFactory(this, getPackageName());
        mAdapter = new CalendarAdapter(this, new CalendarPositionListener(), calendarFactory);
        mAdapter.setContent(current);

        //CalendarListView mListView = (CalendarListView) findViewById(R.id.calendarList);
        mListView.setAdapter(mAdapter);

        mAdapter.setPeriodClickListener(new PeriodAdapter.PeriodClickListener() {
            @Override
            public void onPeriodClick(final EventPeriod period) {
                Log.i(TAG, "onPeriodClick: type=" + period.getType() + ", label=" + period.getLabel());
            }
        });

        mCalendarPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i1) {
                //Log.i(TAG, "onPageScrolled: i=" + i + ", v=" + v + ", i1=" + i1);
            }

            @Override
            public void onPageSelected(final int i) {
                Log.i(TAG, "onPageSelected: " + i);

                current = mCalendarPagerAdapter.getData(i);
                if (i == 0 || i == mCalendarPagerAdapter.getCount() - 1) {
                    initPagerAdapter(HALF_PERIOD);
                } else {
                    initCalendarAdapter();
                }
            }

            @Override
            public void onPageScrollStateChanged(final int i) {
                //Log.i(TAG, "onPageScrollStateChanged: " + i);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: outState=" + outState);
        outState.putInt(POSITION, mCalendarPager.getCurrentItem());
        outState.putParcelable(CURRENT, current);
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
                current = new Period(new DateTime(current.getDate().getYear(), current.getDate().getMonthOfYear(),
                    current.getDate().getDayOfMonth(), 0, 0, 0, 0), Type.MONTH);

                break;

            case R.id.menu_day:
                current = new Period(new DateTime(current.getDate().getYear(), current.getDate().getMonthOfYear(),
                    current.getDate().getDayOfMonth(), 0, 0, 0, 0), Type.DAY);

                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        initActionBar();
        initPagerAdapter(HALF_PERIOD);

        return true;
    }

    private void initPagerAdapter(int position) {
        List<Period> periodList = PeriodUtil.createPeriod(current, HALF_PERIOD);

        mCalendarPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager(), periodList);

        mCalendarPager.setAdapter(mCalendarPagerAdapter);
        mCalendarPager.setCurrentItem(position);
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
        mAdapter.notifyDataSetChanged();
    }
}
