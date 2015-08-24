package org.stingraymappingproject.api.clientandroid;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.stingraymappingproject.api.clientandroid.requesters.Requester;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class StingrayAPIClientServiceTest extends ServiceTestCase<StingrayAPIClientService> {
    String apiBaseUrl = "http://test.example.com/";
    String apiEndpoint1 = "test";

    /**
     * Constructor
     */
    public StingrayAPIClientServiceTest() {
        super(StingrayAPIClientService.class);
    }

    public void testRequestsPointToFullApiUrl() {
        startService();
        StingrayAPIClientService client = getService();
        client.setApiBaseUrl(apiBaseUrl);

        TestRequester r = new TestRequester(client);
        assertEquals(apiBaseUrl + apiEndpoint1, r.getRequest().getUrl());
    }

    public void testInitializes() {
        startService();
        StingrayAPIClientService client = getService();
        client.init();
        assertTrue(client.isInitialized);
    }

    class TestRequester extends Requester {

        public TestRequester(StingrayAPIClientService stingrayAPIClientService) {
            super(stingrayAPIClientService);
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(Object response) {

        }

        @Override
        protected String getRequestParams() {
            return null;
        }

        @Override
        protected GsonRequest getRequest() {
            return getRequest(apiEndpoint1, Request.Method.GET, null);
        }
    }

    /**
     * Test basic startup/shutdown of Service
     */
    @SmallTest
    public void testStartable() {
        startService();
        assertNotNull(getService());
    }

    /**
     * Test binding to service
     */
    @MediumTest
    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), ServiceTestCase.class);
        IBinder service = bindService(startIntent);
        assertNotNull(service);
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

    public void startService() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), StingrayAPIClientService.class);
        startService(startIntent);
    }
}
