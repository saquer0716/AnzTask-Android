/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * The member variable name must equal to the name in json object due to GSON parser requirement.
 * If this api changes, this class must be changed as well
 */

package com.anz.mobile.anzvolley.wrapper.model;

public class Earthquake {
    private String eqid;
    private String src;
    private String region;
    private String lat;
    private String lon;
    private String magnitude;
    private String depth;
    private String timedate;


    /**
     * Getter for earthquake data id
     *
     * @return data id
     */
    public String getId() {
        return eqid;
    }

    /**
     * Setter for earthquake data id
     *
     * @param  id data id
     */
    public void setId(String id) {
        this.eqid = eqid;
    }

    /**
     * Getter for earthquake src
     *
     * @return earthquake src
     */
    public String getSrc() {
        return src;
    }

    /**
     * Setter for earthquake src
     *
     * @param  src src
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Getter for earthquake happened region
     *
     * @return earthquake happened region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter for earthquake happened region
     *
     * @param  region earthquake happened region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter for earthquake location's latitude
     *
     * @return earthquake location's latitude
     */
    public String getLat() {
        return lat;
    }

    /**
     * Setter for earthquake location's latitude
     *
     * @param  lat earthquake location's latitude
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Getter for earthquake location's longitude
     *
     * @return earthquake location's longitude
     */
    public String getLon() {
        return lon;
    }

    /**
     * Setter for earthquake location's longitude
     *
     * @param  lon earthquake location's longitude
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * Getter for earthquake magnitude
     *
     * @return earthquake magnitude
     */
    public String getMagnitude() {
        return magnitude;
    }

    /**
     * Setter for earthquake magnitude
     *
     * @param  magnitude earthquake magnitude
     */
    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    /**
     * Getter for earthquake depth
     *
     * @return earthquake depth
     */
    public String getDepth() {
        return depth;
    }

    /**
     * Setter for earthquake depth
     *
     * @param  depth earthquake depth
     */
    public void setDepth(String depth) {
        this.depth = depth;
    }

    /**
     * Getter for earthquake date
     *
     * @return earthquake date
     */
    public String getDate() {
        return timedate;
    }

    /**
     * Setter for earthquake date
     *
     * @param  date earthquake date
     */
    public void setDate(String date) {
        this.timedate = date;
    }
}
