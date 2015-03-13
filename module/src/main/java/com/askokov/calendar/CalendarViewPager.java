package com.askokov.calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.askokov.calendar.adapter.CalendarPagerAdapter;
import com.askokov.calendar.period.Period;

public class CalendarViewPager extends ViewPager {
    private static final String TAG = "CalendarViewPager";

    interface OnChangePeriodListener {
        void onPeriodChange(Period changed);

        void onExtendPeriodChange(Period changed);
    }

    private OnChangePeriodListener onChangePeriodListener;

    public CalendarViewPager(final Context context) {
        super(context);
    }

    public CalendarViewPager(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnChangePeriodListener(final OnChangePeriodListener onChangePeriodListener) {
        this.onChangePeriodListener = onChangePeriodListener;
    }

    @Override
    public void setAdapter(final PagerAdapter adapter) {
        super.setAdapter(adapter);

        final CalendarPagerAdapter calendarPagerAdapter = (CalendarPagerAdapter) adapter;

        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i1) {
                //Log.i(TAG, "onPageScrolled: i=" + i + ", v=" + v + ", i1=" + i1);
            }

            @Override
            public void onPageSelected(final int i) {
                Log.i(TAG, "onPageSelected: " + i);
                Period current = calendarPagerAdapter.getData(i);

                if (onChangePeriodListener != null) {
                    if (i == 0 || i == adapter.getCount() - 1) {
                        onChangePeriodListener.onExtendPeriodChange(current);
                    } else {
                        onChangePeriodListener.onPeriodChange(current);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(final int i) {
                //Log.i(TAG, "onPageScrollStateChanged: " + i);
            }
        });
    }
}
