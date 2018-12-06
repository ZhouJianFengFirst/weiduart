package com.bw.movie;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.bw.movie.net.Http;
import com.bw.movie.net.HttpHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：App
 */
public class App extends Application {
    private static App mainApp;

    public static IWXAPI mWxApi;

    public static App getMainApp() {
        mainApp = new App();
        return mainApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //分包 初始化
        MultiDex.install(this);
        //网络请求
        HttpHelper.getInstens().init(Http.BASE_URL, this);
        //初始化fresco图片控件
        Fresco.initialize(this);
        mWxApi = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516", true);
        // 将该app注册到微信
        mWxApi.registerApp("wxb3852e6a6b7d9516");
    }
}
