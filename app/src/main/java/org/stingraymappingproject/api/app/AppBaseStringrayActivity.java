package org.stingraymappingproject.api.app;

import android.content.Context;
import android.content.Intent;

import org.stingraymappingproject.api.clientandroid.activities.BaseStingrayActivity;

/**
 * Created by Marvin Arnold on 27/08/15.
 */
public abstract class AppBaseStringrayActivity extends BaseStingrayActivity {
    @Override
    public void startStingrayClientService() {
        if (!mBoundToStingrayAPIService) {
            // Bind to LocalService
            Intent intent = new Intent(AppBaseStringrayActivity.this, AppStringrayAPIClientService.class);
            //Start Service before binding to keep it resident when activity is destroyed
            startService(intent);
            bindService(intent, mStingrayAPIServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }
}
