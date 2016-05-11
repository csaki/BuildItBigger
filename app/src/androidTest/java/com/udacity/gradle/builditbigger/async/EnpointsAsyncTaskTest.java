package com.udacity.gradle.builditbigger.async;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.test.AndroidTestCase;
import android.view.View;

import com.udacity.gradle.builditbigger.MainActivity;

/**
 * Created by neko on 10/05/2016.
 */
public class EnpointsAsyncTaskTest extends AndroidTestCase {

    EndpointsAsyncTask endpointsAsyncTask;

    public void setUp(){
        endpointsAsyncTask = new EndpointsAsyncTask(new MainActivity(){
            @Nullable
            @Override
            public View findViewById(@IdRes int id) {
                return null;
            }
        });
    }

    public void testVerifyNotNullString(){
        assertNotNull(endpointsAsyncTask.doInBackground());
    }

    public void testVerifyNotEmptyString(){
        assertNotSame("", endpointsAsyncTask.doInBackground());
    }
}
