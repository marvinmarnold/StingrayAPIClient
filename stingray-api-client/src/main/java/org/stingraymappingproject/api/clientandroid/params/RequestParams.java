package org.stingraymappingproject.api.clientandroid.params;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public abstract class RequestParams {
    public JSONObject getJsonObject() {
        return mRequestJsonObject;
    }
    public int getMethod() {
        return mRequestMethod;
    }
    public Response.Listener getSuccessListener() {
        return mSuccessListener;
    }
    public Response.ErrorListener getErrorListener() {
        return mErrorListener;
    }
    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    private final JSONObject mRequestJsonObject;
    private final String mRequestUrl;
    private final int mRequestMethod;
    private final Response.Listener mSuccessListener;
    private final Response.ErrorListener mErrorListener;

    public RequestParams(JSONObject jsonObject,
                       String requestUrl,
                       int method,
                       Response.Listener successListener,
                       Response.ErrorListener errorListener) {

        this.mRequestJsonObject = jsonObject;
        this.mRequestUrl = requestUrl;
        this.mRequestMethod = method;
        this.mSuccessListener = successListener;
        this.mErrorListener = errorListener;
    }

    public abstract JsonRequest buildRequest();
}