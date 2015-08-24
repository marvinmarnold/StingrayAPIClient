package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Response;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class Requester<T> implements Runnable, Response.Listener<T>, Response.ErrorListener {
    private final ClientService mClientService;
    protected abstract JSONObject getJSONObjectParameters();
    protected abstract GsonRequest<T> getRequest();

    public String getRequestUrlForEndpoint(String endpointPath) {
        return mClientService.getApiBaseUrl() + endpointPath;
    }

    public Requester(ClientService clientService) {
        this.mClientService = clientService;
    }

    @Override
    public void run() {
        mClientService.getRequestQueue().add(getRequest());
    }

    protected GsonRequest<T> getRequest(String endpointPath, int requestMethod, Class<T> responseClass) {
        return new GsonRequest<T>(getJSONObjectParameters(), getRequestUrlForEndpoint(endpointPath), requestMethod, this, this, responseClass);
    }
}