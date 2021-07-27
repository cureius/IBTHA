package com.freelearners.ibtha.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this))
        {
            //you should not init your app
            return;
        }
        LeakCanary.install(this);
        // Normal app init code
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
