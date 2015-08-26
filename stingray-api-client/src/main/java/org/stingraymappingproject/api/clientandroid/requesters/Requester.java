package org.stingraymappingproject.api.clientandroid.requesters;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.stingraymappingproject.api.clientandroid.StingrayAPIClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class Requester<T> implements Runnable, Response.Listener<T>, Response.ErrorListener {
    private final static String TAG = "Requester";
    private final StingrayAPIClientService mStingrayAPIClientService;
    protected abstract String getRequestParams();
    protected abstract GsonRequest<T> getRequest();

    public String getRequestUrlForEndpoint(String endpointPath) {
        return mStingrayAPIClientService.getApiBaseUrl() + endpointPath;
    }

    public Requester(StingrayAPIClientService stingrayAPIClientService) {
        this.mStingrayAPIClientService = stingrayAPIClientService;
    }

    @Override
    public void run() {
        mStingrayAPIClientService.getRequestQueue().add(getRequest());
    }

    protected GsonRequest<T> getRequest(String endpointPath, int requestMethod, Class<T> responseClass) {
        return new GsonRequest<T>(requestMethod, getRequestUrlForEndpoint(endpointPath), getRequestParams(), this, this, responseClass);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d(TAG, "onErrorResponse");
    }
}