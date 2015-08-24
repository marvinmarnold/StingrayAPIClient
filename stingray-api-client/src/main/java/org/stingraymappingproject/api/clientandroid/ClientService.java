package org.stingraymappingproject.api.clientandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class ClientService extends Service {
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
    private String mApiBaseUrl = "http://api.stingraymappingproject.org/";
    private List<RecurringRequest> mRecurringRequests;

    private RequestQueue mRequestQueue;
    private ArrayList<GsonRequest> mOfflineRequests;
    private ConnectivityManager mConnectivityManager;
    private ScheduledExecutorService mRequestScheduler;
    public boolean isInitialized = false;

    public class ClientServiceBinder extends Binder {
        public ClientService getService() {
            return ClientService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "#onBind");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        init();
        if (intent != null &&
            intent.getAction() != null &&
            intent.getAction().equals(ACTION_SYNC_DATA)) {

            queueOfflineRequests();
        }

        return START_STICKY;
    }

    public void queueOfflineRequests() {
//        Log.d(TAG, "queueOfflineRequests");
//        if(isInitialized && isOnline()) {
//            ArrayList<GsonRequest> t = (ArrayList<GsonRequest>) mOfflineRequests.clone();
//            mOfflineRequests.clear();
//
//            for(GsonRequest offlineRequest : t) {
//                Log.d(TAG, "Syncing offlineRequests");
//                mRequestQueue.add(offlineRequest.buildRequest());
//            }
//        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public void init() {
        if(isInitialized) return;
        mContext = getApplicationContext();
        mRequestScheduler = Executors.newScheduledThreadPool(1);
        mRecurringRequests = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(mContext);
        mOfflineRequests = new ArrayList<GsonRequest>();
        this.mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.isInitialized = true;
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
