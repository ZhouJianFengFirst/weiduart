package com.bw.movie.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：View(接口)
 */
public interface IDelegate {

    void initData();

    void creat(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View getRootView();

    void initContext(Context context);

    void handGetString(String url, int type, Map<String, String> map);

    void handPostString(String url, int type, Map<String, String> hmap, Map<String, String> fmap);

    void getString(String url, int type, Map<String, String> map);

    void postString(String url, int type, Map<String, String> map);

}
