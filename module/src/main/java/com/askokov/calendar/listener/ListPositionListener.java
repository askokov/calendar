package com.askokov.calendar.listener;

import com.askokov.calendar.period.Type;

public interface ListPositionListener {

    void setContentType(Type type);

    void setPosition(int position);

    int getPosition();
}
