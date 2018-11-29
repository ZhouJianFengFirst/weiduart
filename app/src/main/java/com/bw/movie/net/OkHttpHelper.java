package com.bw.movie.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：OkHttp工具类
 */
public class OkHttpHelper {

    private HttpRequestListener httpRequestListener;
    private static final int SUCCESS_REQUEST = 1;
    private static final int FAIL_REQUEST = 0;

    public OkHttpHelper(HttpRequestListener httpRequestListener) {
        this.httpRequestListener = httpRequestListener;
    }

    public OkHttpHelper doGet(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String mehod = request.method();
                        String host = request.url().host();
                        Log.i("intercept", mehod + "==" + host);
                        return chain.proceed(request);
                    }
                }).
                build();
        Request request = new Request.Builder().get().url(url).build();
        doHttp(okHttpClient, request);
        return this;
    }

    public OkHttpHelper doPost(String url, RequestBody body) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body).build();
        doHttp(okHttpClient, request);
        return this;
    }

    private void doHttp(OkHttpClient okHttpClient, final Request request) {

        final Message msg = Message.obtain();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "失败");
                msg.what = FAIL_REQUEST;
                msg.obj = e.getMessage();
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String data = response.body().string();
                    msg.what = SUCCESS_REQUEST;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_REQUEST:
                    String data = (String) msg.obj;
                    httpRequestListener.SuccessRequest(data);
                    break;
                case FAIL_REQUEST:
                    String mess = (String) msg.obj;
                    httpRequestListener.Filed(mess);
                    break;
            }
        }
    };
}

