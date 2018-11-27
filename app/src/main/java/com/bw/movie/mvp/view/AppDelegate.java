package com.bw.movie.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.net.BaseObserver;
import com.bw.movie.net.HttpHelper;
import com.tapadoo.alerter.Alerter;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：View(实现接口)
 * */
public abstract class AppDelegate implements IDelegate {

    private Context mContext;
    private SparseArray<View> views = new SparseArray<>();
    private View rootView;

    @Override
    public void initData() {

    }

    @Override
    public void creat(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), null);
    }

    protected abstract int getLayoutId();

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initContext(Context context) {
        mContext = context;
    }

    @Override
    public void getString(String url, final int type, Map<String, String> map) {

        BaseObserver ob = new BaseObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                if (responseBody != null){
                    String data = null;
                    try {
                        data = responseBody.string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    successString(data,type);
                }else{
                    failString("请求错误");
                }
            }

            @Override
            public void onError(Throwable e) {
                failString(e.getMessage());
            }
        };
        HttpHelper.getInstens().doGet(url, map,ob);
    }


    @Override
    public void postString(String url, final int type, Map<String,String> map) {

        BaseObserver ob = new BaseObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                if (responseBody != null){
                    String data = null;
                    try {
                        data = responseBody.string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    successString(data,type);
                }else{
                    failString("请求错误");
                }
            }

            @Override
            public void onError(Throwable e) {
                failString(e.getMessage());
            }
        };
        HttpHelper.getInstens().doPost(url, map,ob);

    }

    public void successString(String data, int type) {

    }

    public void failString(String msg) {

    }

    public void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void toast(String title,String msg,int s){
        Alerter.create(((AppCompatActivity)mContext)).setBackgroundColor(R.color.colorPrimary)
                .setText(msg)
                .setTitle(title)
                .setDuration(s)
                .show();
    }

    public <T extends View> T getView(int viewId) {
        T view = (T) views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public void setClick(View.OnClickListener listener, int... ids) {

        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    public void destory(){
        rootView = null;
    }
}
