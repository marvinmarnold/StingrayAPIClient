package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.StingrayAPIClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;
import org.stingraymappingproject.api.clientandroid.models.Factoid;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class FactoidsRequester extends Requester<Factoid[]> {
    public FactoidsRequester(StingrayAPIClientService stingrayAPIClientService) {
        super(stingrayAPIClientService);
    }

    @Override
    protected String getRequestParams() {
        return null;
    }

//    @Override
//    protected Map<String, String> getRequestHeaders() {
//        return null;
//    }

    @Override
    protected GsonRequest<Factoid[]> getRequest() {
        return getRequest("factoids", Request.Method.GET, Factoid[].class);
    }
}