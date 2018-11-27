package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：ActivityGuidancePersenter(引导页)
 * */
public class ActivityGuidancePersenter extends AppDelegate {


    private Context mcontext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guidance;
    }


    @Override
    public void initData() {
        super.initData();
        initwidget();
    }

    private void initwidget() {
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext=context;
    }
}
