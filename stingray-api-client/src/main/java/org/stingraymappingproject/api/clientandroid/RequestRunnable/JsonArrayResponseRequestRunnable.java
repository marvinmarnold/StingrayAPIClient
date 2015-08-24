package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import com.android.volley.Response;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.JsonArrayResponseRequestParams;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class JsonArrayResponseRequestRunnable extends RequestRunnable {
    public JsonArrayResponseRequestRunnable(ClientService clientService, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(clientService, successListener, errorListener);
    }

    RequestParams getRequestParams(String endpointPath, int requestMethod) {
        return new JsonArrayResponseRequestParams(getJSONObject(), getRequestUrlForEndpoint(endpointPath), requestMethod, mSuccessListener, mErrorListener);
    }
}