package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinionPersenter(我的意见反馈页面)
 * */

//继承APPDelegate
public class ActivityOpinionPersenter extends AppDelegate{
    private Context context;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_opinion;
    }

    //重写初始化上下文方法
    @Override
    public void initContext(Context context) {
        //删了super这行提上去上下文
        this.context=context;
    }

    //重写初始化数据方法
    @Override
    public void initData() {
        super.initData();
        //初始化数据方法
        initwidget();
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去

    }
}
