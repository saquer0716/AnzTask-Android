/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 */

package com.anz.mobile.task;

import android.app.Application;

import com.anz.mobile.task.util.AnzLog;

public class AnzTaskApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        //Only enable logs in debug mode.
        if (BuildConfig.DEBUG) {
            AnzLog.enabled = true;
            AnzLog.tag = "ANZ Task";
        }
    }
}
