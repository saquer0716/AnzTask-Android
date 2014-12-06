/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * Right now this class's member variables should not be used because earthquake restful api
 * takes no parameter at the moment, if in the future parameters like data index range are
 * added, we can use the parameters straight away.
 */

package com.anz.mobile.anzvolley.wrapper.json;

public class RequestEarthquake extends RequestObject{
    private int mFrom;
    private int mTo;

    /**
     * Getter for start index
     *
     * @return start index
     */
    public int getFromIndex() {
        return this.mFrom;
    }

    /**
     * Setter for start index
     *
     * @param  from The start index
     */
    public void setFromIndex(int from) {
        this.mFrom = from;
    }

    /**
     * Getter for end index
     *
     * @return end index
     */
    public int getToIndex() {
        return mTo;
    }

    /**
     * Setter for end index
     *
     * @param  to The end index
     */
    public void setToIndex(int to) {
        this.mTo = to;
    }
}
