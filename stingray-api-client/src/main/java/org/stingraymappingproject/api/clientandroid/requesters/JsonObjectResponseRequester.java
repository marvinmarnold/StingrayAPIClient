package org.stingraymappingproject.api.clientandroid.requesters;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.params.JsonObjectResponseRequestParams;
import org.stingraymappingproject.api.clientandroid.params.RequestParams;

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