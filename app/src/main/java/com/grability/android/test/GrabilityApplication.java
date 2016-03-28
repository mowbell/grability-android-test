package com.grability.android.test;

import android.app.Application;
import android.content.Context;

public class GrabilityApplication extends Application {
    public static final String STORE_APPS_PREFERENCES_KEY ="STORE_APPS_PREFERENCES_KEY";
    public static final String PREF_JSON_LOADED = "jsonLoaded";
    private static GrabilityApplication mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext=this.getApplicationContext();

    }
    public static Context getAppContext() {
        return mAppContext;
    }
}
