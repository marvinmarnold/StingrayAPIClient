package org.stingraymappingproject.api.clientandroid.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.stingraymappingproject.api.clientandroid.StingrayAPIClientService;

/**
 * Created by Marvin Arnold on 24/08/15.
 */
public abstract class BaseStingrayActivity extends AppCompatActivity {
    private final static String TAG = "BaseStingrayActivity";
    protected Context mContext;
    protected boolean mBoundToStingrayAPIService;
    protected StingrayAPIClientService mStingrayAPIService;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unbind from the StingrayAPI service
        if (mBoundToStingrayAPIService) {
            unbindService(mStingrayAPIServiceConnection);
            mBoundToStingrayAPIService = false;
        }

        stopService(new Intent(mContext, StingrayAPIClientService.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mContext = getApplicationContext();
        startStingrayClientService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void startStingrayClientService() {
        Log.d(TAG, "startStingrayClientService");
        if (!mBoundToStingrayAPIService) {
            // Bind to LocalService
            Intent intent = new Intent(BaseStingrayActivity.this, StingrayAPIClientService.class);
            //Start Service before binding to keep it resident when activity is destroyed
            startService(intent);
            bindService(intent, mStingrayAPIServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    protected final ServiceConnection mStingrayAPIServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            mStingrayAPIService = ((StingrayAPIClientService.ClientServiceBinder) service).getService();
            mBoundToStingrayAPIService = true;

            scheduleRequesters();
            mStingrayAPIService.scheduleRecurringRequests();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.e(TAG, "Service Disconnected");
            mBoundToStingrayAPIService = false;
        }
    };

    abstract protected void scheduleRequesters();
}
