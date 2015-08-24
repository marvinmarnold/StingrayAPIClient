package org.stingraymappingproject.api.clientandroid.RequestRunnable;

import android.test.AndroidTestCase;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.ClientService;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class JsonObjectResponseRequestRunnableTest extends AndroidTestCase {
    String apiBaseUrl = "http://test.example.com/";
    String apiEndpoint1 = "test";
    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();

    }

//    public void testRequestsPointToFullApiUrl() {
//        ClientService client = new ClientService();
//
//        client.setApiBaseUrl(apiBaseUrl);
//
//        Response.Listener successListener = new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        };
//
//        Response.ErrorListener errorListener = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        };
//
//        TestJsonObjectResponseRequestRunnable r = new TestJsonObjectResponseRequestRunnable(client, successListener, errorListener);
//        assertEquals(apiBaseUrl + apiEndpoint1, r.testGetRequestParams());
//    }

    class TestJsonObjectResponseRequestRunnable extends JsonObjectResponseRequestRunnable {

        public TestJsonObjectResponseRequestRunnable(ClientService clientService, Response.Listener successListener, Response.ErrorListener errorListener) {
            super(clientService, successListener, errorListener);
        }

        @Override
        protected JSONObject getJSONObject() {
            return null;
        }

        @Override
        protected RequestParams getRequestParams() {
            return getRequestParams(apiEndpoint1, Request.Method.GET);
        }

        public RequestParams testGetRequestParams() {
            return getRequestParams();
        }
    }
}