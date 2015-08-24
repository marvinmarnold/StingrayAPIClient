package org.stingraymappingproject.api.clientandroid.RequestParams;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class JsonObjectResponseRequestParams extends RequestParams {

    public JsonObjectResponseRequestParams(JSONObject jsonObject, String requestUrl, int method, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(jsonObject, requestUrl, method, successListener, errorListener);
    }

    @Override
    public JsonRequest buildRequest() {
        return new JsonObjectRequest(
                getMethod(),
                getRequestUrl(),
                getJsonObject(),
                getSuccessListener(),
                getErrorListener());
    }

}