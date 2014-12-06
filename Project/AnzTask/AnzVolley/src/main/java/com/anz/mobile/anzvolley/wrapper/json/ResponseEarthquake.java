/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 */

package com.anz.mobile.anzvolley.wrapper.json;

import com.anz.mobile.anzvolley.wrapper.model.Earthquake;

import java.util.ArrayList;

public class ResponseEarthquake extends ResponseObject{
    private String count;
    private ArrayList<Earthquake> earthquakes;

    /**
     * Getter for earthquake data count
     *
     * @return data count of earthquake
     */
    public String getCount() {
        return count;
    }

    /**
     * Setter for earthquake data count
     *
     * @param  count count of earthquake
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * Getter for earthquake data array
     *
     * @return data earthquake data array
     */
    public ArrayList<Earthquake> getEarthquakeData() {
        return earthquakes;
    }

    /**
     * Setter for earthquake data array
     *
     * @param  data earthquake data array
     */
    public void setProducts(ArrayList<Earthquake> data) {
        earthquakes = data;
    }
}
