package com.bw.movie;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.bw.movie.net.Http;
import com.bw.movie.net.HttpHelper;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：App
 * */
public  class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //分包 初始化
        MultiDex.install(this);
        //网络请求
        HttpHelper.getInstens().init(Http.BASE_URL,this);
        //初始化fresco图片控件
        Fresco.initialize(this);

    }
}
