package com.askokov.calendar;

import android.app.Application;
import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

@ReportsCrashes(formKey = "",
    mailTo = "skokov@inbox.ru",
    mode = ReportingInteractionMode.TOAST,
    resToastText = R.string.crash_report)
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ACRA.init(this);
    }
}
