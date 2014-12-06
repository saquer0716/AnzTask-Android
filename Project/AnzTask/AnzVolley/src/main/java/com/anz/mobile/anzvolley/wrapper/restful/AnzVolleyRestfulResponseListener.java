/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 */

package com.anz.mobile.anzvolley.wrapper.restful;

import com.android.volley.VolleyError;

/**
 * Created by gu on 5/09/14.
 */
public interface AnzVolleyRestfulResponseListener<T> {
    /**
     * Restful response successful event
     *
     * @param  response The actual response class that conforms to JSON response
     */
    public void onResponse(T response);

    /**
     * Restful request finishes with error
     *
     * @param  error The volley error
     */
    public void onErrorResponse(VolleyError error);
}
