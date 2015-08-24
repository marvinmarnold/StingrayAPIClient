package org.stingraymappingproject.api.clientandroid;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class GsonRequest<T> extends Request<T> {
    public JSONObject getJsonObject() {
        return mRequestJsonObject;
    }
    public Response.Listener getSuccessListener() {
        return mSuccessListener;
    }

    protected final Gson mGson;

    /** class of type of response */
    protected final Class<T> mClazz;
    private final JSONObject mRequestJsonObject;
    private final Response.Listener mSuccessListener;

    public GsonRequest(JSONObject jsonObject,
                       String requestUrl,
                       int method,
                       Response.Listener<T> successListener,
                       Response.ErrorListener errorListener,
                       Class<T> responseClazz) {
        super(method, requestUrl, errorListener);

        this.mRequestJsonObject = jsonObject;
        this.mSuccessListener = successListener;
        this.mGson = new Gson();
        this.mClazz = responseClazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        this.mSuccessListener.onResponse(response);
    }

}