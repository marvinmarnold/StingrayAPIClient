package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import com.android.volley.Request;
import com.android.volley.Response;

import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class PostStingrayReadingRequest extends JsonObjectResponseRequestRunnable {

    public PostStingrayReadingRequest(ClientService clientService, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(clientService, successListener, errorListener);
    }

    @Override
    protected RequestParams getRequestParams() {
        return getRequestParams("stingray_readings", Request.Method.POST);
    }
}