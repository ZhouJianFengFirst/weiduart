package com.bw.movie;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.bw.movie.net.Http;
import com.bw.movie.net.HttpHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
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
   //解决分包和信鸽推送的冲突  要写到这里
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //分包 初始化
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
//        //分包 初始化
//        MultiDex.install(this);
        //网络请求
        HttpHelper.getInstens().init(Http.BASE_URL, this);
        //初始化fresco图片控件
        Fresco.initialize(this);
        mWxApi = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516", true);
        // 将该app注册到微信
        mWxApi.registerApp("wxb3852e6a6b7d9516");
        //信鸽debug
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.setTag(this,"XINGE");

    }
}
