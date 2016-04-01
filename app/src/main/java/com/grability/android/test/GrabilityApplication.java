package com.grability.android.test;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GrabilityApplication extends Application {
    public static final String STORE_APPS_PREFERENCES_KEY ="STORE_APPS_PREFERENCES_KEY";
    public static final String PREF_JSON_LOADED = "jsonLoaded";
    private static GrabilityApplication mInstance;
    private static Context mAppContext;
    private static ConnectivityManager cm;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mAppContext=getApplicationContext();
        setConnectivityManager();
    }
    public Context getAppContext() {
        return mAppContext;
    }

    public static GrabilityApplication getmInstance(){
        return mInstance;
    }

    private static void setConnectivityManager(){
        cm=(ConnectivityManager)mAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public ConnectivityManager getConnectivityManager(){
        return cm;
    }

    public Boolean isNetworkConnected(){
        if (cm != null)
        {
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            if(activeNetworkInfo!=null) {
                boolean isConnected = activeNetworkInfo.isConnected();
                return isConnected;
            }
        }
        return false;



    }


}
