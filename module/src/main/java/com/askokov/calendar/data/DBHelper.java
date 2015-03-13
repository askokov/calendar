package com.askokov.calendar.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.askokov.calendar.period.Day;
import com.askokov.calendar.period.Month;
import org.joda.time.DateTime;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    private static final int DB_VERSION = 1; // версия БД

    public DBHelper(Context context, String dbPath) {
        super(context, dbPath, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate database");

        db.beginTransaction();
        try {
            db.execSQL("CREATE TABLE CALENDAR (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, date INTEGER NOT NULL, event_description TEXT NOT NULL)");

            //Insert demo data

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade database from " + oldVersion + " to " + newVersion + " version");
    }

    public Day getDayModel(DateTime date) {
        SQLiteDatabase db = getReadableDatabase();

        db.close();

        return DemoPeriod.createDayModel(date);
    }

    public Month getMonthModel(DateTime date) {
        SQLiteDatabase db = getReadableDatabase();

        db.close();

        return DemoPeriod.createMonthModel(date);
    }
}
