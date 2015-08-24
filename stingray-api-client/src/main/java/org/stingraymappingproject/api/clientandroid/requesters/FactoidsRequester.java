package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.params.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class FactoidsRequester extends JsonArrayResponseRequester {

    public FactoidsRequester(ClientService clientService) {
        super(clientService);
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