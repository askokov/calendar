package com.askokov.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.askokov.calendar.adapter.PeriodAdapter;

public class CalendarListView extends ListView {
    private static final String TAG = "CalendarListView";

    private PeriodAdapter mAdapter;

    public CalendarListView(final Context context) {
        super(context);
    }

    public CalendarListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarListView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        Log.i(TAG, "setAdapter");
        if (!(adapter instanceof PeriodAdapter)) {
            throw new ClassCastException(adapter.toString() + " must extends PeriodAdapter");
        }
        super.setAdapter(adapter);

        mAdapter = (PeriodAdapter) adapter;
        mAdapter.setListView(this);

        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final AbsListView view, final int scrollState) {
                //Log.i(TAG, "ListView.onScrollStateChanged: scrollState=" + scrollState);
            }

            @Override
            public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
                //Log.i(TAG, "ListView.onScrollStateChanged: firstVisibleItem=" + firstVisibleItem);

                if (mAdapter.getListPositionListener() != null) {
                    mAdapter.getListPositionListener().setPosition(firstVisibleItem);
                }
            }
        });
    }
}
