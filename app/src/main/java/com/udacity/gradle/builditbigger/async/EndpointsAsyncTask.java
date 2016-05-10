package com.udacity.gradle.builditbigger.async;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;

import java.io.IOException;

import me.devsaki.udacity.androidlib.JokeActivity;
import me.devsaki.udacity.gce.myApi.MyApi;

/**
 * Created by neko on 10/05/2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApiService = null;
    private Activity activity;
    LinearLayout ll;
    ProgressBar progressBar;

    public EndpointsAsyncTask(Activity activity) {
        this.activity = activity;
        ll = (LinearLayout) activity.findViewById(R.id.ll);
        progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ll.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        ll.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        if(result==null){
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JokeActivity.INTENT_PARAM_JOKE, result);
        activity.startActivity(intent);
    }
}
