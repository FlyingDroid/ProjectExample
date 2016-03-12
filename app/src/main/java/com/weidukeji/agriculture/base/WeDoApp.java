package com.weidukeji.agriculture.base;

import android.app.Application;
import android.content.Context;



/**
 * Created by admin on 2014/12/29.
 */
public class WeDoApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context =this.getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}

