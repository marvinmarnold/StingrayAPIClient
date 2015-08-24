package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class PostStingrayReadingRequester extends Requester {

    public PostStingrayReadingRequester(ClientService clientService) {
        super(clientService);
    }

    @Override
    protected GsonRequest getRequest() {
        return getRequest("stingray_readings", Request.Method.POST, null);
    }
}