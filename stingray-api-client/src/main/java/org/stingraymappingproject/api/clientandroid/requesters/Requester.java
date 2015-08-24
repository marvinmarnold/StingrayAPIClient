package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Response;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.params.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class Requester implements Runnable, Response.Listener, Response.ErrorListener {
    private final ClientService mClientService;
    protected abstract JSONObject getJSONObject();
    protected abstract RequestParams getRequestParams();

    public String getRequestUrlForEndpoint(String endpointPath) {
        return mClientService.getApiBaseUrl() + endpointPath;
    }

    public Requester(ClientService clientService) {
        this.mClientService = clientService;
    }

    @Override
    public void run() {
        mClientService.getRequestQueue().add(getRequestParams().buildRequest());
    }
}