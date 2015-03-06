package com.askokov.calendar;

import java.util.List;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Event;
import com.askokov.calendar.model.Period;

public class CalendarDetailAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Pair<Period, Period>> objects;

    public CalendarDetailAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setObjects(final List<Pair<Period, Period>> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Pair<Period, Period> getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.events_layout, parent, false);
        }

        Pair<Period, Period> pair = getItem(position);

        Period first = pair.first;
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

        Period second = pair.second;
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.second);
        if (second != null) {
            layout.setVisibility(View.VISIBLE);

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
            layout.setVisibility(View.INVISIBLE);
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