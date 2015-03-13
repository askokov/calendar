package com.askokov.calendar.listener;

import android.util.Log;
import com.askokov.calendar.period.Type;

public class CalendarPositionListener implements ListPositionListener {
    private static final String TAG = "CalendarPositionListener";

    private Type contentType;
    private boolean changed;
    private int position;

    @Override
    public void setContentType(final Type type) {
        changed = contentType == null || contentType != type;

        Log.i(TAG, "Change content type from " + contentType + " to " + type);
        contentType = type;
    }

    @Override
    public void setPosition(final int position) {
        this.position = position;

    }

    @Override
    public int getPosition() {
        return changed ? 0 : position;
    }
}
