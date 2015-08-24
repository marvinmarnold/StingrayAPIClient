package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class FactoidsRequestRunnable extends JsonArrayResponseRequestRunnable {

    public FactoidsRequestRunnable(ClientService clientService, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(clientService, successListener, errorListener);
    }

    @Override
    protected JSONObject getJSONObject() {
        return null;
    }

    @Override
    protected RequestParams getRequestParams() {
        return getRequestParams("factoids", Request.Method.GET);
    }
}