package org.stingraymappingproject.api.clientandroid;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by Marvin Arnold on 23/08/15.
 */
public class ClientServiceTest extends ServiceTestCase<ClientService> {
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
}
