package org.stingraymappingproject.api.app;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.RecurringRequest;
import org.stingraymappingproject.api.clientandroid.activities.BaseStingrayActivity;
import org.stingraymappingproject.api.clientandroid.models.Factoid;
import org.stingraymappingproject.api.clientandroid.models.StingrayReading;
import org.stingraymappingproject.api.clientandroid.requesters.FactoidsRequester;
import org.stingraymappingproject.api.clientandroid.requesters.NearbyRequester;
import org.stingraymappingproject.api.clientandroid.requesters.PostStingrayReadingRequester;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseStingrayActivity {
    private final String TAG = "MainActivity";
    private static final int FREQUENCY_VALUE = 10;
    private static final TimeUnit FREQUENCY_UNIT = TimeUnit.SECONDS;

    @Override
    protected void scheduleRequesters() {
//        scheduleNearbyRequester();
        scheduleFactoidsRequester();
        schedulePostStingrayReadingRequester();
    }

    public void scheduleNearbyRequester() {
        NearbyRequester nearbyRequester = new NearbyRequester(mStingrayAPIService) {
            @Override
            protected String getRequestParams() {
                return null;
            }

            @Override
            public void onResponse(StingrayReading[] response) {
                List<StingrayReading> factoids = Arrays.asList(response);
                Log.d(TAG, "onResponse");
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        };
        RecurringRequest recurringRequest = new RecurringRequest(FREQUENCY_VALUE, FREQUENCY_UNIT, nearbyRequester);
        mStingrayAPIService.addRecurringRequest(recurringRequest);
    }

    public void scheduleFactoidsRequester() {
        FactoidsRequester factoidsRequester = new FactoidsRequester(mStingrayAPIService) {
            @Override
            public void onResponse(Factoid[] response) {
                List<Factoid> factoids = Arrays.asList(response);
                Log.d(TAG, "onResponse");
                for(Factoid f : factoids) {
                    Log.d(TAG, "Factoid: " + f.getFact());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        };
        RecurringRequest recurringRequest = new RecurringRequest(FREQUENCY_VALUE, FREQUENCY_UNIT, factoidsRequester);
        mStingrayAPIService.addRecurringRequest(recurringRequest);
    }

    public void schedulePostStingrayReadingRequester() {
        PostStingrayReadingRequester postStingrayReadingRequester = new PostStingrayReadingRequester(mStingrayAPIService) {
            @Override
            protected String getRequestParams() {
                JSONObject imsiFields = new JSONObject();
                JSONObject datum = new JSONObject();

                try {
                    imsiFields.put("threat_level", 5);
                    datum.put("stingray_reading", imsiFields);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return datum.toString();
            }

            @Override
            public void onResponse(StingrayReading response) {
                Log.d(TAG, "onResponse");
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse");
            }
        };

        RecurringRequest recurringRequest = new RecurringRequest(FREQUENCY_VALUE, FREQUENCY_UNIT, postStingrayReadingRequester);
        mStingrayAPIService.addRecurringRequest(recurringRequest);
    }
}
