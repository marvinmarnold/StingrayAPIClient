package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import com.android.volley.Response;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class RequestRunnable implements Runnable {
    private final ClientService mClientService;
    protected final Response.Listener mSuccessListener;
    protected final Response.ErrorListener mErrorListener;
    protected abstract JSONObject getJSONObject();
    protected abstract RequestParams getRequestParams();

    public String getRequestUrlForEndpoint(String endpointPath) {
        return mClientService.getApiBaseUrl() + endpointPath;
    }

    public RequestRunnable(ClientService clientService, Response.Listener successListener, Response.ErrorListener errorListener) {
        this.mClientService = clientService;
        this.mSuccessListener = successListener;
        this.mErrorListener = errorListener;
    }

    @Override
    public void run() {
        mClientService.getRequestQueue().add(getRequestParams().buildRequest());
    }
}