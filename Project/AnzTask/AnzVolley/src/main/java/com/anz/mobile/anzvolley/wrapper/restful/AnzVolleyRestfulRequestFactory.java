/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * Factory class for generating request product based on specific API
 */

package com.anz.mobile.anzvolley.wrapper.restful;

import com.android.volley.Request;
import com.anz.mobile.anzvolley.wrapper.json.RequestObject;

import java.lang.reflect.Field;

public class AnzVolleyRestfulRequestFactory {
    private static String SERVER = "http://www.seismi.org/api/eqs/";

    //when new restful api are supported, we can add new url here and identifier in service interface
    //PM2_5 is just an example for new api for getting PM2.5 data
    private static final String EARTHQUAKE_DATA = "";
    private static final String PM2_5_DATA = "pm25";
    public interface Service {
        int EARTHQUAKE_DATA = 0;
        int PM2_5_DATA = 1;
    }

    /**
     * The factory method for generating different restful request. Basically all request are
     * inherited from
     *
     * @param  service  the service id that identifies what kind of request should be generated
     * @param  listener event listener for restful api request result
     * @param  object the parameters of http get, potential will be converted to name pairs
     * @param  type the response type that corresponds to the request based on restful API type
     * @return      AnzVolleyRestfulRequest instance that can be added to the request pool
     */
    public static <T> AnzVolleyRestfulRequest<T> createRequest(final int service, final AnzVolleyRestfulResponseListener<T> listener, final RequestObject object, final Class<T> type) {
        String url;
        int method;
        String jsonObject = null;

        switch (service) {
            case Service.EARTHQUAKE_DATA:
                url = SERVER + EARTHQUAKE_DATA;
                method = Request.Method.GET;

                url += buildNameValuePairs(object);
                break;
            case Service.PM2_5_DATA:
                url = SERVER + PM2_5_DATA;
                method = Request.Method.GET;

                url += buildNameValuePairs(object);
                break;

            default:
                return null;
        }
        return new AnzVolleyRestfulRequest<T>(method, url, jsonObject, listener, type);
    }

    private static <T> String buildNameValuePairs(final T object) {
        if (object == null) {
            return "";
        }
        Field[] fieldsSelf = object.getClass().getDeclaredFields();
        Field[] fieldsSuper = object.getClass().getSuperclass().getDeclaredFields();

        Field[] fields= new Field[fieldsSelf.length + fieldsSuper.length];
        System.arraycopy(fieldsSelf, 0, fields, 0, fieldsSelf.length);
        System.arraycopy(fieldsSuper, 0, fields, fieldsSelf.length, fieldsSuper.length);

        String nameValuePairs = "";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            nameValuePairs += field.getName() + "=";

            try {
                Object value = field.get(object);
                nameValuePairs += value.toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();

                return "";
            }

            if (i < fields.length - 1) {
                nameValuePairs += "&";
            }
        }

        return nameValuePairs.equals("") ? "" : "?" + nameValuePairs;
    }
}
