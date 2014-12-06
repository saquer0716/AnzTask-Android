/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * API for sending logs to console. set enabled to true in debug mode.
 * Do remember to set it to false when release. A static tag can be set
 * as well. When tag is set in advance, use single argument method for logs.
 */

package com.anz.mobile.anzvolley.wrapper;

import android.util.Log;

public class AnzVolleyLog {
    public static boolean enabled = false;
    private static String tag = "AnzVolley";

    /**
     * Send an INFO log message.
     *
     * @param  msg The message you would like logged.
     */
    public static void i(String msg) {
        if (enabled) Log.i(tag, msg);
    }

    /**
     * Send an ERROR log message.
     *
     * @param  msg The message you would like logged.
     */
    public static void e(String msg) {
        if (enabled) Log.e(tag, msg);
    }

    /**
     * Send an DEBUG log message.
     *
     * @param  msg The message you would like logged.
     */
    public static void d(String msg) {
        if (enabled) Log.d(tag, msg);
    }

    /**
     * Send an VERBOSE log message.
     *
     * @param  msg The message you would like logged.
     */
    public static void v(String msg) {
        if (enabled) Log.v(tag, msg);
    }

    /**
     * Send an WARN log message.
     *
     * @param  msg The message you would like logged.
     */
    public static void w(String msg) {
        if (enabled) Log.w(tag, msg);
    }

    /**
     * Send an INFO log message.
     *
     * @param  t Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param  msg The message you would like logged.
     */
    public static void i(String t, String msg) {
        if (enabled) Log.i(t, msg);
    }

    /**
     * Send an ERROR log message.
     *
     * @param  t Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param  msg The message you would like logged.
     */
    public static void e(String t, String msg) {
        if (enabled) Log.e(t, msg);
    }

    /**
     * Send a DEBUG log message.
     *
     * @param  t Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param  msg The message you would like logged.
     */
    public static void d(String t, String msg) {
        if (enabled) Log.d(t, msg);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param  t Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param  msg The message you would like logged.
     */
    public static void v(String t, String msg) {
        if (enabled) Log.v(t, msg);
    }

    /**
     * Send a WARN log message.
     *
     * @param  t Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param  msg The message you would like logged.
     */
    public static void w(String t, String msg) {
        if (enabled) Log.w(t, msg);
    }
}
