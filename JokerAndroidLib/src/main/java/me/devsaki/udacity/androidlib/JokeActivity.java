package me.devsaki.udacity.androidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public final static String INTENT_PARAM_JOKE = "intent_param_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(INTENT_PARAM_JOKE);
        if(joke==null){
            joke = "Why so serious?";
        }

        TextView tvJoke = (TextView) findViewById(R.id.tvJoke);
        tvJoke.setText(joke);
    }
}
