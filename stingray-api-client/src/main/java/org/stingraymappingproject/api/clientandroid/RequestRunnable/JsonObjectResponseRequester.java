package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.JsonObjectResponseRequestParams;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class JsonObjectResponseRequester extends Requester {
    public JsonObjectResponseRequester(ClientService clientService) {
        super(clientService);
    }

    protected RequestParams getRequestParams(String endpointPath, int requestMethod) {
        return new JsonObjectResponseRequestParams(getJSONObject(), getRequestUrlForEndpoint(endpointPath), requestMethod, this, this);
    }
}