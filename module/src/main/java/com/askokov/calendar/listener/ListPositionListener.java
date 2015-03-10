package com.askokov.calendar.listener;

import com.askokov.calendar.model.Period;

public interface ListPositionListener {

    void setContentType(Period.Type type);

    void setPosition(int position);

    int getPosition();
}
