package com.askokov.calendar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.askokov.calendar.adapter.CalendarDetailAdapter;
import com.askokov.calendar.data.CalendarFactory;
import com.askokov.calendar.listener.CalendarPositionListener;
import com.askokov.calendar.listener.PeriodClickListener;
import com.askokov.calendar.model.Period;
import org.joda.time.DateTime;


public class MainActivity extends FragmentActivity implements PeriodFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    private MenuItem mMenuMonth;
    private MenuItem mMenuDay;

    private DateTime current;
    private Period.Type periodType;
    private CalendarDetailAdapter mAdapter;
    private CalendarFactory calendarFactory;
    private PeriodFragment periodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        periodType = Period.Type.MONTH;
        current = new DateTime(2015,3,1,0,0,0,0);

        periodFragment = PeriodFragment.newInstance(current, periodType);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .add(R.id.periodContainer, periodFragment)
            .commit();

        calendarFactory = new CalendarFactory(this, getPackageName());

        mAdapter = new CalendarDetailAdapter(this, new CalendarPositionListener());
        mAdapter.setPeriodClickListener(new PeriodClickListener() {
            @Override
            public void onPeriodClick(final Period period) {
                Log.i(TAG, "onPeriodClick: type=" + period.getType() + ", label=" + period.getLabel());
            }
        });
        mAdapter.setContent(calendarFactory.getPeriod(current, periodType), periodType);

        CalendarListView mListView = (CalendarListView) findViewById(R.id.calendarList);
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
        periodFragment.setData(current, periodType);
        initEvents(current);

        return true;
    }

    @Override
    public void onFragmentInteraction(final DateTime current) {
        Log.i(TAG, "onFragmentInteraction");
        this.current = current;
        initEvents(current);
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

    private void initEvents(final DateTime current) {
        Log.i(TAG, "initEvents");
        mAdapter.setContent(calendarFactory.getPeriod(current, periodType), periodType);
        mAdapter.notifyDataSetChanged();
    }
}
