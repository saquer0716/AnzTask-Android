package com.anz.mobile.anzvolley;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.volley.VolleyError;
import com.anz.mobile.anzvolley.wrapper.AnzVolley;
import com.anz.mobile.anzvolley.wrapper.json.RequestEarthquake;
import com.anz.mobile.anzvolley.wrapper.json.ResponseEarthquake;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulRequest;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulRequestFactory;
import com.anz.mobile.anzvolley.wrapper.restful.AnzVolleyRestfulResponseListener;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TAG = "AnzVolleyTest";
    private AnzVolleyRestfulRequest<ResponseEarthquake> mRequest;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.d(TAG, "set up");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Log.d(TAG, "tear down");
        if (mRequest != null) {
            mRequest.cancel();
        }
    }

    public void testEarthquakeDataRequest() {
        Log.d(TAG, "testing earthquake data request API");
        RequestEarthquake request = new RequestEarthquake();

        mRequest = AnzVolleyRestfulRequestFactory.createRequest(AnzVolleyRestfulRequestFactory.Service.EARTHQUAKE_DATA,
                new AnzVolleyRestfulResponseListener<ResponseEarthquake>() {
                    @Override
                    public void onResponse(ResponseEarthquake response) {
                        assertNotNull(response);
//                        ArrayList<Earthquake> earthquakeData = response.getEarthquakeData();
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, request, ResponseEarthquake.class);

        AnzVolley.getInstance(getContext()).addToNomadRequestQueue(mRequest);
    }
}