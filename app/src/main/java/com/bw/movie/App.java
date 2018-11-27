package com.bw.movie;

import android.app.Application;

import com.bw.movie.net.HttpHelper;
/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：App
 * */
public    class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.getInstens().init("http://172.17.8.100/",this);
    }
}
