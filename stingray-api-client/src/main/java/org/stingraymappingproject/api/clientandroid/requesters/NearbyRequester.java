package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class NearbyRequester extends Requester {

    public NearbyRequester(ClientService clientService) {
        super(clientService);
    }

    @Override
    protected GsonRequest getRequest() {
        return getRequest("nearby", Request.Method.GET, null);
    }
}