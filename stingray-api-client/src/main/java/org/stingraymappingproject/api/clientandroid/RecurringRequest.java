package org.stingraymappingproject.api.clientandroid;

import android.util.Log;

import org.stingraymappingproject.api.clientandroid.requesters.Requester;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class RecurringRequest {
    private static final String TAG = "RecurringRequest";
    private Requester mRunnable;
    private boolean isScheduled;
    private ScheduledFuture mRequester;
    private final int mFrequencyValue;
    private final TimeUnit mFrequencyUnit;

    public RecurringRequest(int frequencyValue, TimeUnit frequencyUnit, Requester runnable) {
        this.mFrequencyValue = frequencyValue;
        this.mFrequencyUnit = frequencyUnit;
        this.mRunnable = runnable;
    }

    public void schedule(ScheduledExecutorService scheduler) {
        Log.d(TAG, "start scheduleUploader");
        if (isScheduled) return;

        mRequester = scheduler.scheduleAtFixedRate(mRunnable, 0, mFrequencyValue, mFrequencyUnit);
        isScheduled = true;
        Log.d(TAG, "end scheduleUploader");
    }

    public void cancel() {
        if(!isScheduled) return;
        this.mRequester.cancel(true);
        this.isScheduled = false;
    }

}
