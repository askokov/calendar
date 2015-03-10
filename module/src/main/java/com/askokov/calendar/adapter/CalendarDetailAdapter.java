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
import com.askokov.calendar.listener.ListPositionListener;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Event;
import com.askokov.calendar.model.Period;

public class CalendarDetailAdapter extends CalendarBaseAdapter {
    private static final String TAG = "CalendarDetailAdapter";

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Pair<Period, Period>> content;

    public CalendarDetailAdapter(final Context context, final ListPositionListener listPositionListener) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        setListPositionListener(listPositionListener);
    }

    public void setContent(final List<Pair<Period, Period>> content, final Period.Type contentType) {
        Log.i(TAG, "setContent");
        this.content = content;

        getListPositionListener().setContentType(contentType);
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
    public Pair<Period, Period> getItem(int position) {
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
            view = mLayoutInflater.inflate(R.layout.events_layout, parent, false);
        }

        Pair<Period, Period> pair = getItem(position);

        final Period first = pair.first;
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
        if (Period.Type.HOUR == first.getType()) {
            populateEventLayout(first, firstLinearLayout);
            view.findViewById(R.id.pinFirst).setBackgroundResource(R.drawable.pin_none);
        } else if (Period.Type.DAY == first.getType()) {
            if (first.getChildren() != null) {
                for (Period p : first.getChildren()) {
                    populateEventLayout(p, firstLinearLayout);
                }
            }

            if (((Day)first).isCurrent()) {
                view.findViewById(R.id.pinFirst).setBackgroundResource(R.drawable.pin_blue);
            } else {
                view.findViewById(R.id.pinFirst).setBackgroundResource(R.drawable.pin_gray);
            }
        }
        ((TextView) view.findViewById(R.id.textLabelFirst)).setText(first.getLabel());

        final Period second = pair.second;
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
            if (Period.Type.HOUR == second.getType()) {
                populateEventLayout(second, secondLinearLayout);
                view.findViewById(R.id.pinFirst).setBackgroundResource(R.drawable.pin_none);
            } else if (Period.Type.DAY == second.getType()) {
                if (second.getChildren() != null) {
                    for (Period p : second.getChildren()) {
                        populateEventLayout(p, secondLinearLayout);
                    }
                }

                if (((Day)second).isCurrent()) {
                    view.findViewById(R.id.pinSecond).setBackgroundResource(R.drawable.pin_blue);
                } else {
                    view.findViewById(R.id.pinSecond).setBackgroundResource(R.drawable.pin_gray);
                }
            }
            ((TextView) view.findViewById(R.id.textLabelSecond)).setText(second.getLabel());
        } else {
            secondLayout.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    private void populateEventLayout(Period period, final LinearLayout layout) {
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