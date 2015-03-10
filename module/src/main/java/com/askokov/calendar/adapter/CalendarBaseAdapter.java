package com.askokov.calendar.adapter;

import android.widget.BaseAdapter;
import android.widget.ListView;
import com.askokov.calendar.listener.ListPositionListener;
import com.askokov.calendar.listener.PeriodClickListener;

public abstract class CalendarBaseAdapter extends BaseAdapter {

    private ListPositionListener listPositionListener;
    private PeriodClickListener periodClickListener;
    private ListView listView;

    public void setListPositionListener(final ListPositionListener listPositionListener) {
        this.listPositionListener = listPositionListener;
    }

    public ListPositionListener getListPositionListener() {
        return listPositionListener;
    }

    public PeriodClickListener getPeriodClickListener() {
        return periodClickListener;
    }

    public void setPeriodClickListener(final PeriodClickListener periodClickListener) {
        this.periodClickListener = periodClickListener;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(final ListView listView) {
        this.listView = listView;
    }
}