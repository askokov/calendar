package com.askokov.calendar.data;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.askokov.calendar.period.Day;
import com.askokov.calendar.period.Month;
import org.joda.time.DateTime;

public class CalendarData {
    private static final String TAG = "CalendarData";
    private static final String DB_NAME = "/calendar.db";

    private DBHelper dbHelper;

    public CalendarData(Context context, String packageName) {
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

    public Day getDayModel(DateTime date) {
        return dbHelper.getDayModel(date);
    }

    public Month getMonthModel(DateTime date) {
        return dbHelper.getMonthModel(date);
    }
}
