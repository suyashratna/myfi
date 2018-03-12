package com.example.lenovo.myfinance;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by lenovo on 3/12/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
