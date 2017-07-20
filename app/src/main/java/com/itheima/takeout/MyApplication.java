package com.itheima.takeout;

import android.app.Application;

/**
 * Created by itheima.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;
    }
}
