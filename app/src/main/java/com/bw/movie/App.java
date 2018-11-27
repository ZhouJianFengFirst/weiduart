package com.bw.movie;

import android.app.Application;

import com.bw.movie.net.HttpHelper;
import com.facebook.drawee.backends.pipeline.Fresco;

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
        //初始化fresco图片控件
        Fresco.initialize(this);
    }
}
