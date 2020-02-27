package com.loginapp.creativeteam.tn.loginapplication;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("got-it-driver-app") // should correspond to APP_ID env variable
                .clientKey("gotit")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://got-it-driver-app.herokuapp.com/parse/").build());
    }
}

