package com.bw.movie.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.UltimateBar;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：persenter(fragment基类)
 */

public abstract class BaseFragment<T extends AppDelegate> extends Fragment {
    protected T delegate;

    public abstract Class<T> getDelegateClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            delegate = getDelegateClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UltimateBar.newImmersionBuilder().applyNav(false)
                .build(getActivity()).apply();
        delegate.initContext(getActivity());
        delegate.creat(inflater, null, savedInstanceState);
        View rootView = delegate.getRootView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        delegate.initData();
    }

    public void initData() {

    }

}