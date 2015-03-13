package com.askokov.calendar.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.askokov.calendar.R;
import com.askokov.calendar.data.CalendarFactory;
import com.askokov.calendar.listener.ListPositionListener;
import com.askokov.calendar.period.Event;
import com.askokov.calendar.period.EventPeriod;
import com.askokov.calendar.period.Period;
import com.askokov.calendar.period.Type;

public class CalendarAdapter extends PeriodAdapter {
    private static final String TAG = "CalendarAdapter";

    private Context context;
    private LayoutInflater mLayoutInflater;
    private int layout;
    private CalendarFactory calendarFactory;
    private List<Pair<EventPeriod, EventPeriod>> content;

    public CalendarAdapter(final Context context, final ListPositionListener listPositionListener,
                           final CalendarFactory calendarFactory) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.calendarFactory = calendarFactory;

        setListPositionListener(listPositionListener);
    }

    public void setContent(final Period current) {
        Log.i(TAG, "Set content with type");

        this.content = calendarFactory.getPeriod(current);
        layout = current.getType() == Type.MONTH ? R.layout.month_layout : R.layout.day_layout;

        getListPositionListener().setContentType(current.getType());
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

        getListView().setSelection(getListPositionListener().getPosition());
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public Pair<EventPeriod, EventPeriod> getItem(int position) {
        return content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(layout, parent, false);
        }

        Pair<EventPeriod, EventPeriod> pair = getItem(position);

        final EventPeriod first = pair.first;
        LinearLayout firstLayout = (LinearLayout) view.findViewById(R.id.first);
        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (getPeriodClickListener() != null) {
                    getPeriodClickListener().onPeriodClick(first);
                }
            }
        });

        LinearLayout firstLinearLayout = (LinearLayout)view.findViewById(R.id.eventFirstContainer);
        firstLinearLayout.removeAllViews();
        if (Type.HOUR == first.getType()) {
            populateEventLayout(first, firstLinearLayout);
        } else if (Type.DAY == first.getType()) {
            if (first.getChildren() != null) {
                for (EventPeriod p : first.getChildren()) {
                    populateEventLayout(p, firstLinearLayout);
                }
            }
        }
        ((TextView) view.findViewById(R.id.textLabelFirst)).setText(first.getLabel());

        final EventPeriod second = pair.second;
        LinearLayout secondLayout = (LinearLayout) view.findViewById(R.id.second);
        if (second != null) {
            secondLayout.setVisibility(View.VISIBLE);

            secondLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (getPeriodClickListener() != null) {
                        getPeriodClickListener().onPeriodClick(second);
                    }
                }
            });

            LinearLayout secondLinearLayout = (LinearLayout)view.findViewById(R.id.eventSecondContainer);
            secondLinearLayout.removeAllViews();
            if (Type.HOUR == second.getType()) {
                populateEventLayout(second, secondLinearLayout);
            } else if (Type.DAY == second.getType()) {
                if (second.getChildren() != null) {
                    for (EventPeriod p : second.getChildren()) {
                        populateEventLayout(p, secondLinearLayout);
                    }
                }
            }
            ((TextView) view.findViewById(R.id.textLabelSecond)).setText(second.getLabel());
        } else {
            secondLayout.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    private void populateEventLayout(EventPeriod period, final LinearLayout layout) {
        if (period.getEvents() != null) {
            for (Event event : period.getEvents()) {
                TextView tv = createEventTextView(event);
                layout.addView(tv);
            }
        }
    }

    private TextView createEventTextView(final Event event) {
        TextView tv = new TextView(context);
        tv.setText(event.getDescription());
        tv.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT));
        return tv;
    }
}