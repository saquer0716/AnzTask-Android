/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 */

package com.anz.mobile.task.adpter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anz.mobile.anzvolley.wrapper.model.Earthquake;
import com.anz.mobile.task.R;
import com.anz.mobile.task.ui.CircleFlipButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class EarthquakeAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Earthquake> mEarthquakeData;
    private Typeface mTypeface;

    //Storing round button's flip status for each cell, true - showing front, false - showing back
    private Boolean[] mFlipStatusArray;

    //for reusable cell in listview
    private class ViewHolder {
        public CircleFlipButton flipButton;
        public TextView mRegion;
        public TextView mDate;
        public TextView mDateType;
    }

    /**
     * Constructor for EarthquakeAdapter class
     *
     * @param  context The Context the listview cell is running in,
     *                   through which it can access the current theme, resources, etc.
     * @param  items the array list of earthquake data
     * @return      the adapter for populating earthquake data
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> items) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mEarthquakeData = items;
        //the default flip status is showing front
        mFlipStatusArray = new Boolean[mEarthquakeData.size()];
        showFront();
        mTypeface = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
    }

    /**
     * Getter for accessing the data source of adapter
     *
     * @return      the earthquake data source
     */
    public ArrayList<Earthquake> getmEarthquakeData() {
        return mEarthquakeData;
    }

    /**
     * Set the flip status for all earthquake data, used in sort magnitude
     */
    public void showFront() {
        Arrays.fill(mFlipStatusArray, Boolean.TRUE);
    }

    /**
     * Clear the flip status for all earthquake data, used in sort depth
     */
    public void showBack() {
        Arrays.fill(mFlipStatusArray, Boolean.FALSE);
    }

    @Override
    public int getCount() {
        return mEarthquakeData.size();
    }

    @Override
    public Object getItem(int position) {
        return mEarthquakeData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = (View) mInflater.inflate(R.layout.earthquake_list_view_cell, parent, false);
            holder = new ViewHolder();

            holder.flipButton = (CircleFlipButton)convertView.findViewById(R.id.earthquake_flip_button);
            holder.mRegion = (TextView)convertView.findViewById(R.id.earthquake_region);
            holder.mDate = (TextView) convertView.findViewById(R.id.earthquake_date);
            holder.mDateType = (TextView) convertView.findViewById(R.id.earthquake_magnitude);

            holder.mRegion.setTypeface(mTypeface);
            holder.mDate.setTypeface(mTypeface);
            holder.mDateType.setTypeface(mTypeface);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final Earthquake earthquake = mEarthquakeData.get(position);

        //need to register this on click event so we can change between magnitude and depth
        holder.flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //round button flipped, status reversed.
                mFlipStatusArray[position] = !mFlipStatusArray[position];
                ViewGroup cell = (ViewGroup) v.getParent();
                ((TextView) cell.findViewById(R.id.earthquake_magnitude)).setText(mFlipStatusArray[position] ? mContext.getString(R.string.magnitude) : mContext.getString(R.string.depth));
            }
        });

        //returned depth comes with two decimal places and always has one trailing zero, remove it.
        String depth = new DecimalFormat("#.#").format(Float.parseFloat(earthquake.getDepth()));
        holder.flipButton.setDisplayText(mFlipStatusArray[position], earthquake.getMagnitude(), depth);
        holder.mRegion.setText(earthquake.getRegion());
        holder.mDate.setText(earthquake.getDate());
        holder.mDateType.setText(mFlipStatusArray[position] ? mContext.getString(R.string.magnitude) : mContext.getString(R.string.depth));

        return convertView;
    }
}
