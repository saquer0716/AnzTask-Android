/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 */

package com.anz.mobile.anzvolley.wrapper.restful;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class AnzVolleyRestfulRequest<T> extends JsonRequest<T> {
    private Gson mGson;
    private Class<T> mType;

    /**
     * Constructor for AnzVolleyRestfulRequest class
     * In AnzVolley, this class is not init directly but through AnzVolleyRestfulRequestFactory via
     * its factory method.
     * @param  method Http get or post
     * @param  jsonRequest json object when making a post
     * @param  listener restful request response event listener
     * @param  type the type of class that response class will be converted into through GSON
     * @return      the AnzVolleyRestfulRequest instance
     */
    public AnzVolleyRestfulRequest(int method, String url, final String jsonRequest, final AnzVolleyRestfulResponseListener<T> listener, final Class<T> type) {
        super(method, url, jsonRequest, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.onResponse(response);
            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(new VolleyError(error));
            }
        });
        mType = type;
        mGson = new Gson();
    }

    /**
     * This method is override to do our AnzVolley style of processing.
     * It converts the JSON string into actual response class through GSON
     * @param  response the raw network response passed from Volley library
     * @return      the API specific response class that can be used conveniently.
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T parsedGSON = mGson.fromJson(jsonString, mType);
            return Response.success(parsedGSON, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        }
    }
}
