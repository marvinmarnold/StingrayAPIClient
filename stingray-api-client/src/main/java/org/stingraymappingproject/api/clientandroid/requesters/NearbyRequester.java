package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.StingrayAPIClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;
import org.stingraymappingproject.api.clientandroid.models.StingrayReading;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class NearbyRequester extends Requester<StingrayReading[]> {

    public NearbyRequester(StingrayAPIClientService stingrayAPIClientService) {
        super(stingrayAPIClientService);
    }

    @Override
    protected GsonRequest<StingrayReading[]> getRequest() {
        return getRequest("nearby", Request.Method.PUT, StingrayReading[].class);
    }
}