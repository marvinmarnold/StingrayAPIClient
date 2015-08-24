package org.stingraymappingproject.api.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RecurringRequest;
import org.stingraymappingproject.api.clientandroid.models.Factoid;
import org.stingraymappingproject.api.clientandroid.models.StingrayReading;
import org.stingraymappingproject.api.clientandroid.requesters.FactoidsRequester;
import org.stingraymappingproject.api.clientandroid.requesters.PostStingrayReadingRequester;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private boolean mBoundToStingrayClientService = false;
    private ClientService mStingrayClientService;

    private static final int UPLOAD_FREQUENCY_VALUE = 20;
    private static final TimeUnit UPLOAD_FREQUENCY_UNIT = TimeUnit.MINUTES;

    private static final int FACTOIDS_FREQUENCY_VALUE = 10;
    private static final TimeUnit FACTOIDS_FREQUENCY_UNIT = TimeUnit.SECONDS;

    private static final int NEARBY_FREQUENCY_VALUE = 30;
    private static final TimeUnit NEARBY_FREQUENCY_UNIT = TimeUnit.MINUTES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        startStingrayClientService();
    }

    public void startStingrayClientService() {
        Log.d(TAG, "startStingrayClientService");
        if (!mBoundToStingrayClientService) {
            Log.d(TAG, "startStingrayClientService notBound");
            // Bind to LocalService
            Intent intent = new Intent(MainActivity.this, ClientService.class);
            //Start Service before binding to keep it resident when activity is destroyed
            startService(intent);
            bindService(intent, mClientServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Service Connection to bind the activity to the service
     */
    private final ServiceConnection mClientServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            mStingrayClientService = ((ClientService.ClientServiceBinder) service).getService();
            mBoundToStingrayClientService = true;

            FactoidsRequester factoidsRequester = new FactoidsRequester(mStingrayClientService) {
                @Override
                public void onResponse(Factoid[] response) {
                    List<Factoid> factoids = Arrays.asList(response);
                    Log.d(TAG, "onResponse");
                    for(Factoid f : factoids) {
                        Log.d(TAG, f.getFact());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse");
                }
            };
            RecurringRequest recurringFactoidsRequest = new RecurringRequest(FACTOIDS_FREQUENCY_VALUE, FACTOIDS_FREQUENCY_UNIT, factoidsRequester);
            mStingrayClientService.addRecurringRequest(recurringFactoidsRequest);

            PostStingrayReadingRequester postStingrayReadingRequester = new PostStingrayReadingRequester(mStingrayClientService) {
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
//                @Override
//                protected Map<String, String> getRequestHeaders() {
//                    Map<String, String> headers = new HashMap();
//                    Gson gson = new Gson();
//                    StingrayReading reading = new StingrayReading("12");
//                    Log.d(TAG, "HEADERS: " + gson.toJson(reading));
//                    headers.put("stingray_reading", gson.toJson(reading));
//                    return headers;
//                }
//
//                JSONObject addComonFields(JSONObject jsonObject) throws JSONException {
//                    jsonObject.put("lat", "34.435");
//                    jsonObject.put("long", "-98.405");
//                    jsonObject.put("observed_at", DateFormat.getDateTimeInstance().format(new Date()));
//                    jsonObject.put("version", "test");
//
//                    return jsonObject;
//                }

                @Override
                public void onResponse(StingrayReading response) {
                    Log.d(TAG, "onResponse");
                    Log.d(TAG, "location: " + response.getLocation());
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse");
//                    Log.d(TAG, error.getMessage());
                }
            };
            RecurringRequest postStingrayReadingRequest = new RecurringRequest(FACTOIDS_FREQUENCY_VALUE, FACTOIDS_FREQUENCY_UNIT, postStingrayReadingRequester);
            mStingrayClientService.addRecurringRequest(postStingrayReadingRequest);

            mStingrayClientService.scheduleRecurringRequests();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e(TAG, "Service Disconnected");
            mBoundToStingrayClientService = false;
        }
    };
}
