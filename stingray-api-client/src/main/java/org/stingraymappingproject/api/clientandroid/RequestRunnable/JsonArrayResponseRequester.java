package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.JsonArrayResponseRequestParams;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class JsonArrayResponseRequester extends Requester {
    public JsonArrayResponseRequester(ClientService clientService) {
        super(clientService);
    }

    RequestParams getRequestParams(String endpointPath, int requestMethod) {
        return new JsonArrayResponseRequestParams(getJSONObject(), getRequestUrlForEndpoint(endpointPath), requestMethod, this, this);
    }
}
