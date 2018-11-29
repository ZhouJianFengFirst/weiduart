package com.bw.movie.net;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：HttpHelper(连网工具类 retrofit+RxJava)
 * */
public class HttpHelper {

    private static HttpHelper retrofitHelper;
    private Retrofit retrofit;
    private Context mCotext;

    private HttpHelper() {

    }

    public static HttpHelper getInstens() {
        if (retrofitHelper == null) {
            synchronized (HttpHelper.class) {
                retrofitHelper = new HttpHelper();
            }
        }
        return retrofitHelper;
    }

    public void init(String baseUrl, Context context) {
        this.mCotext = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void doGet(String url, Map<String, String> map, Observer ob) {
        if (map == null) {
            map = new HashMap<>();
        }
        BaseService baseService = retrofit.create(BaseService.class);
        baseService.get(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ob);
    }

    public void doPost(String url, Map<String, String> map, Observer ob) {

        BaseService baseService = retrofit.create(BaseService.class);
        baseService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ob);
    }

    public void upLoad(File file, String uid, Observer ob) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mCotext, "sd卡未挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file","head.jpg",requestBody);
        retrofit.create(BaseService.class)
        .upload(part,uid)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(ob);
    }
}
