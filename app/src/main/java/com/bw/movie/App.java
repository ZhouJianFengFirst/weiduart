package com.bw.movie;

import android.app.Application;

import com.bw.movie.net.HttpHelper;

public    class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getInstens().init("http://172.17.8.100/",this);
    }
}
