package com.askokov.calendar.dao;

import java.io.File;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.askokov.calendar.model.Day;
import com.askokov.calendar.model.Month;

public class CalendarDao {
    private static final String TAG = "CalendarDao";
    private static final String DB_NAME = "/calendar.db";

    private DBHelper dbHelper;

    public CalendarDao(Context context, String packageName) {
        File path = new File(Environment.getExternalStorageDirectory(), packageName);
        if (!path.exists()) {
            if (path.mkdir()) {
                Log.i(TAG, "Directory<" + path.getPath() + "> is created");
            }
        }

        String dbPath = path.getPath() + DB_NAME;
        Log.i(TAG, "DB File: " + dbPath);

        dbHelper = new DBHelper(context, dbPath);
    }

    public Day getDayModel(Date date) {
        return dbHelper.getDayModel(date);
    }

    public Month getMonthModel(Date date) {
        return dbHelper.getMonthModel(date);
    }
}
