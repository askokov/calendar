package com.askokov.calendar.period;

import android.os.Parcel;
import android.os.Parcelable;
import org.joda.time.DateTime;

public class Period implements Parcelable {

    private Type type;
    private DateTime date;

    public Period() {
    }

    public Period(final DateTime date, final Type type) {
        this.date = date;
        this.type = type;
    }

    // конструктор, считывающий данные из Parcel
    private Period(Parcel parcel) {
        type = Type.valueOf(parcel.readString());
        date = new DateTime(parcel.readInt());
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(final DateTime date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }


    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(type.name());
        parcel.writeLong(date.getMillis());
    }

    public static final Parcelable.Creator<Period> CREATOR = new Parcelable.Creator<Period>() {
        // распаковываем объект из Parcel
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        public Period[] newArray(int size) {
            return new Period[size];
        }
    };

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Period period = (Period) o;

        if (!date.equals(period.date)) return false;
        if (type != period.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
