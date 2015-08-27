package org.stingraymappingproject.api.clientandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.stingraymappingproject.api.clientandroid.models.Factoid;
import org.stingraymappingproject.api.clientandroid.models.StingrayReading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class StingrayAPIClientService extends Service {
    // Constants
    private static final String TAG = "DataTrackerService";
    public static String ACTION_SYNC_DATA = "ACTION_SYNC_DATA";

    // Final
    private Context mContext;
    private final IBinder mBinder = new ClientServiceBinder();

    public String getApiBaseUrl() {
        return mApiBaseUrl;
    }
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
    public void setApiBaseUrl(String apiBaseUrl) { this.mApiBaseUrl = apiBaseUrl; }


    // Should end with '/'
    private String mApiBaseUrl = "https://stingray-mapping-server.herokuapp.com/";
    private List<RecurringRequest> mRecurringRequests;

    private RequestQueue mRequestQueue;
    private ArrayList<GsonRequest> mOfflineRequests;
    private ConnectivityManager mConnectivityManager;
    private ScheduledExecutorService mRequestScheduler;
    public boolean isInitialized = false;

    protected List<Factoid> mFactoids;

    public List<StingrayReading> getStingrayReadings() {
        return mStingrayReadings;
    }

    public List<Factoid> getFactoids() {
        return mFactoids;
    }

    public void setStingrayReadings(StingrayReading[] stingrayReadings) {
        this.mStingrayReadings = Arrays.asList(stingrayReadings);
    }

    public void addStingrayReading(StingrayReading stingrayReading) {
        this.mStingrayReadings.add(stingrayReading);
    }

    protected List<StingrayReading> mStingrayReadings;

    public class ClientServiceBinder extends Binder {
        public StingrayAPIClientService getService() {
            return StingrayAPIClientService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
//        Log.d(TAG, "#onBind");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.d(TAG, "onStartCommand");
        init();
        if (intent != null &&
            intent.getAction() != null &&
            intent.getAction().equals(ACTION_SYNC_DATA)) {

            queueOfflineRequests();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(!isInitialized) return;
        mRequestScheduler = null;
        isInitialized = false;

        if (!isInitialized) return;
        for(RecurringRequest request : mRecurringRequests) {
            request.cancel();
        }
        mRecurringRequests = null;
    }

    public void queueOfflineRequests() {
//        Log.d(TAG, "queueOfflineRequests");
        if(isInitialized && isOnline()) {
            ArrayList<GsonRequest> t = (ArrayList<GsonRequest>) mOfflineRequests.clone();
            mOfflineRequests.clear();
            for(GsonRequest offlineRequest : t) {
//                Log.d(TAG, "Syncing offlineRequests");
                mRequestQueue.add(offlineRequest);
            }
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public void init() {
        if(isInitialized) return;
        this.mContext = getApplicationContext();
        this.mRequestScheduler = Executors.newScheduledThreadPool(1);
        this.mRecurringRequests = new ArrayList<>();
        this.mRequestQueue = Volley.newRequestQueue(mContext);
        this.mOfflineRequests = new ArrayList<GsonRequest>();
        this.mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.isInitialized = true;
        this.mFactoids = new ArrayList<>();
        this.mStingrayReadings = new ArrayList<>();
    }

    public void scheduleRecurringRequests() {
        if (!isInitialized) return;
        for(RecurringRequest recurringRequest : mRecurringRequests) {
            recurringRequest.schedule(mRequestScheduler);
        }
    }

    public void addRecurringRequest(RecurringRequest recurringRequest) {
        mRecurringRequests.add(recurringRequest);
    }
}
