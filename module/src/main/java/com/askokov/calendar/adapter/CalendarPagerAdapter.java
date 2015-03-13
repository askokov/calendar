package com.askokov.calendar.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.askokov.calendar.PeriodFragment;
import com.askokov.calendar.period.Period;

public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "CalendarPeriodAdapter";

    private List<Period> data;

    public CalendarPagerAdapter(final FragmentManager fm, final List<Period> data) {
        super(fm);
        this.data = data;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(final int position) {
        Log.i(TAG, "getItem: " + position);
        return PeriodFragment.newInstance(data.get(position));
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    public Period getData(final int position) {
        return data.get(position);
    }
}
