package com.example.prince.hackathon.activity;

import android.app.Application;

/**
 * Created by prince on 3/28/17.
 */

public class SplashConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Don't do this! This is just so cold launches take some time
//        SystemClock.sleep(TimeUnit.SECONDS.toMillis(8));
    }

}
