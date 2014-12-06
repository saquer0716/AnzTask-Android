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

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.anz.mobile.anzvolley.BuildConfig;


public class AnzVolley {
    private static AnzVolley mInstance;
    private RequestQueue mNetworkRequestQueue;
    private Context mContext;

    /**
     * Static singleton method for generating unique AnzVolley instance
     *
     * @param  context The Context that AnzVolley lives in
     * @return      the singleton instance of AnzVolley
     */
    public static synchronized AnzVolley getInstance(Context context) {
        if (mInstance == null) {
            if (BuildConfig.DEBUG) {
                AnzVolleyLog.enabled = true;
            }
            mInstance = new AnzVolley(context);
        }

        return mInstance;
    }

    /*set constructor to private for singleton design pattern*/
    private AnzVolley(Context context) {
        if (mNetworkRequestQueue == null) {
            mContext = context;
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
            Network network = new BasicNetwork(new AnzVolleyHurlStack());

            //Creates a default instance of the worker pool and start it.
            mNetworkRequestQueue = new RequestQueue(cache, network);
            mNetworkRequestQueue.start();
            AnzVolleyLog.d("AnzVolley pool generated and running queue is started");
        }
    }

    /**
     * Add a Volley request into the poll. The http request will start automatically
     * after this call
     *
     * @param  req The generic Volly request instance. T here corresponds to the response class
     *             of this request
     */
    public <T> void addToNomadRequestQueue(Request<T> req) {
        mNetworkRequestQueue.add(req);
        AnzVolleyLog.d("a request is added into pool, it would be running soon");
    }
}
