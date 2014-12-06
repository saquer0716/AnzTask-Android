/*
 * Copyright (C) 2014 Ning Gu
 *
 * Created by Ning on 5/12/14.
 *
 * MainActivity is the launching activity of this app. It loads
 * earthquake data and displays in a listview. The listview supports
 * drag down refresh and sort the earthquake data with a couple of criteria
 */

package com.anz.mobile.task.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.anz.mobile.anzvolley.wrapper.AnzVolley;
import com.anz.mobile.anzvolley.wrapper.json.RequestEarthquake;
import com.anz.mobile.anzvolley.wrapper.json.ResponseEarthquake;
import com.anz.mobile.anzvolley.wrapper.model.Earthquake;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulRequest;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulRequestFactory;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulResponseListener;
import com.anz.mobile.task.R;
import com.anz.mobile.task.adpter.EarthquakeAdapter;
import com.anz.mobile.task.util.AnzLog;
import com.anz.mobile.task.util.ArrayComparator;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity implements ActionBar.OnNavigationListener, SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mEarthquakeListView;
    private EarthquakeAdapter mAdapter;

    //the actual http request for getting earthquake data
    private AnzVolleyRestfulRequest<ResponseEarthquake> mRequest;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setTitle(getString(R.string.title_activity_main));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(
                new ArrayAdapter<String>(actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, getResources().getStringArray(R.array.earthquake_data_sort_list)), this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light);

        mEarthquakeListView = (ListView)findViewById(R.id.list_earthquake);

        mEarthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Earthquake data = (Earthquake)mAdapter.getItem(position);

                //now start google map through intent.
                StringBuilder sb = new StringBuilder();
                sb.append("geo:0,0?q=");
                sb.append(data.getLat() + ",");
                sb.append(data.getLon());
                sb.append("(" + data.getRegion()+ ")");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(sb.toString()));
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    //activity not found
                    AnzLog.e(e.getMessage());
                }
            }
        });

        //make http request in onCreate();
        makeEarthquakeDataRequest();

        //there is a known issue in support library v4 that refreshing widget would not show up when view is loaded,
        //this is a work around solution.
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        //when activity stops or is destroyed, cancel any pending or ongoing http request
        if (mRequest != null) {
            mRequest.cancel();
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        if (mAdapter == null) {
            return true;
        }
        switch (itemPosition) {
            case 0:
                Collections.sort(mAdapter.getmEarthquakeData(), ArrayComparator.magnitudeComparator(true));
                mAdapter.showFront();
                break;
            case 1:
                Collections.sort(mAdapter.getmEarthquakeData(), ArrayComparator.magnitudeComparator(false));
                mAdapter.showFront();
                break;
            case 2:
                Collections.sort(mAdapter.getmEarthquakeData(), ArrayComparator.depthComparator(true));
                mAdapter.showBack();
                break;
            case 3:
                Collections.sort(mAdapter.getmEarthquakeData(), ArrayComparator.depthComparator(false));
                mAdapter.showBack();
                break;
            default:
                return true;
        }

        mAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
        makeEarthquakeDataRequest();
    }

    private void makeEarthquakeDataRequest() {
        /*create request parameter, since there are no parameters in this earthquake data restful api, we don't
          need to set its properties*/
        RequestEarthquake request = new RequestEarthquake();

        mRequest = AnzVolleyRestfulRequestFactory.createRequest(AnzVolleyRestfulRequestFactory.Service.EARTHQUAKE_DATA,
                new AnzVolleyRestfulResponseListener<ResponseEarthquake>() {
                    @Override
                    public void onResponse(ResponseEarthquake response) {
                        //response json is converted into ResponseEarthquake throgh GSON library
                        //now we can use it directly
                        AnzLog.d("http response is successfully received");
                        ArrayList<Earthquake> earthquakeData = response.getEarthquakeData();

                        mAdapter = new EarthquakeAdapter(MainActivity.this, earthquakeData);
                        Collections.sort(mAdapter.getmEarthquakeData(), ArrayComparator.magnitudeComparator(true));

                        mEarthquakeListView.setAdapter(mAdapter);
                        AnzLog.d("ListView now updated with " + earthquakeData.size() + " rows");

                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AnzLog.e(error.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, request, ResponseEarthquake.class);

        //use AnzVolley class's singleton method to add request queue
        AnzVolley.getInstance(getApplicationContext()).addToNomadRequestQueue(mRequest);
    }
}
