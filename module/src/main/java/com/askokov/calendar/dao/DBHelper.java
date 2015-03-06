package com.askokov.calendar.dao;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Month;

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

    public Day getDayModel(Date date) {
        SQLiteDatabase db = getReadableDatabase();

        db.close();

        return Demo.createDayModel(date);
    }

    public Month getMonthModel(Date date) {
        SQLiteDatabase db = getReadableDatabase();

        db.close();

        return Demo.createMonthModel(date);
    }
}
