package com.udacity.gradle.builditbigger.async;

import android.content.Context;
import android.test.AndroidTestCase;

/**
 * Created by neko on 10/05/2016.
 */
public class EnpointsAsyncTaskTest extends AndroidTestCase {

    public void testVerifyNotNullString(){
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(null);
        assertNotNull(endpointsAsyncTask.doInBackground());
    }

    public void testVerifyNotEmptyString(){
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(null);
        assertNotSame("", endpointsAsyncTask.doInBackground());
    }
}
