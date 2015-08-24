package org.stingraymappingproject.api.clientandroid.params;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class FactoidsRequestParams extends JsonObjectResponseRequestParams {
    public FactoidsRequestParams(JSONObject jsonObject, String endpoint, int method, Response.Listener successListener, Response.ErrorListener errorListener) {
        super(jsonObject, endpoint, method, successListener, errorListener);
    }
    public FactoidsRequestParams(Response.Listener successListener, Response.ErrorListener errorListener) {
        super(null, "factoids", Request.Method.GET, successListener, errorListener);
    }
}