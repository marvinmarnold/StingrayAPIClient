package org.stingraymappingproject.api.clientandroid;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.stingraymappingproject.api.clientandroid.RequestParams.RequestParams;
import org.stingraymappingproject.api.clientandroid.RequestRunnable.JsonObjectResponseRequester;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class ClientServiceTest extends ServiceTestCase<ClientService> {
    String apiBaseUrl = "http://test.example.com/";
    String apiEndpoint1 = "test";

    /**
     * Constructor
     *
     * @param serviceClass The type of the service under test.
     */
    public ClientServiceTest(Class<ClientService> serviceClass) {
        super(serviceClass);
    }

    /**
     * Test basic startup/shutdown of Service
     */
    @SmallTest
    public void testStartable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), ClientService.class);
        startService(startIntent);
    }

    /**
     * Test binding to service
     */
    @MediumTest
    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), ServiceTestCase.class);
        IBinder service = bindService(startIntent);
    }

    /**
     * The name 'test preconditions' is a convention to signal that if this
     * test doesn't pass, the test case was not set up properly and it might
     * explain any and all failures in other tests.  This is not guaranteed
     * to run before other tests, as junit uses reflection to find the tests.
     */
    @SmallTest
    public void testPreconditions() {
    }

    public void testRequestsPointToFullApiUrl() {
        ClientService client = new ClientService();

        client.setApiBaseUrl(apiBaseUrl);

        TestJsonObjectResponseRequester r = new TestJsonObjectResponseRequester(client);
        assertEquals(apiBaseUrl + apiEndpoint1, r.testGetRequestParams());
    }
    class TestJsonObjectResponseRequester extends JsonObjectResponseRequester {

        public TestJsonObjectResponseRequester(ClientService clientService) {
            super(clientService);
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

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(Object response) {

        }
    }
}
