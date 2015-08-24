package org.stingraymappingproject.api.clientandroid;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class GsonRequest<T> extends JsonRequest<T> {
    protected final Gson mGson;

    /** class of type of response */
    protected final Class<T> mClazz;
    private final Response.Listener mSuccessListener;
//    private final Map<String, String> mHeaders;

    public GsonRequest(int method,
                       String requestUrl,
                       String params,
                       Response.Listener<T> successListener,
                       Response.ErrorListener errorListener,
                       Class<T> responseClazz) {
        super(method, requestUrl, params, successListener, errorListener);

//        this.mHeaders = headers;
        this.mSuccessListener = successListener;
        this.mGson = new Gson();
        this.mClazz = responseClazz;
    }

//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
////        return this.mHeaders != null ? this.mHeaders : super.getHeaders();
//        Map<String, String> headers = new HashMap();
//        Gson gson = new Gson();
//        StingrayReading reading = new StingrayReading("12");
//        headers.put("stingray_reading", "77777");
//        return headers;
//    }

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