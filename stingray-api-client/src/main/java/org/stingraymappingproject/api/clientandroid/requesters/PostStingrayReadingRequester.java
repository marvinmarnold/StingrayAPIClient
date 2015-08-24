package org.stingraymappingproject.api.clientandroid.requesters;

import com.android.volley.Request;

import org.stingraymappingproject.api.clientandroid.StingrayAPIClientService;
import org.stingraymappingproject.api.clientandroid.GsonRequest;
import org.stingraymappingproject.api.clientandroid.models.StingrayReading;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class PostStingrayReadingRequester extends Requester<StingrayReading> {

    public PostStingrayReadingRequester(StingrayAPIClientService stingrayAPIClientService) {
        super(stingrayAPIClientService);
    }

    @Override
    protected GsonRequest<StingrayReading> getRequest() {
        return getRequest("stingray_readings", Request.Method.POST, StingrayReading.class);
    }
}