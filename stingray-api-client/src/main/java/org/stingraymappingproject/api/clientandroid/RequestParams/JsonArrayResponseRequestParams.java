package org.stingraymappingproject.api.clientandroid.RequestParams;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class JsonArrayResponseRequestParams extends RequestParams {

    public JsonArrayResponseRequestParams(JSONObject jsonObject, String requestUrl, int method, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(jsonObject, requestUrl, method, successListener, errorListener);
    }

    @Override
    public JsonRequest buildRequest() {
        return new JsonArrayRequest(
                getMethod(),
                getRequestUrl(),
                getJsonObject(),
                getSuccessListener(),
                getErrorListener());
    }
}