package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class PostStingrayReadingRequester extends JsonObjectResponseRequester {

    public PostStingrayReadingRequester(ClientService clientService) {
        super(clientService);
    }

    @Override
    protected RequestParams getRequestParams() {
        return getRequestParams("stingray_readings", Request.Method.POST);
    }
}