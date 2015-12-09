package com.example.afif.mirrawtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by afif on 5/12/15.
 */
public class MainApplication extends Application{

    private static Context mContext;

    public MainApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }
}
