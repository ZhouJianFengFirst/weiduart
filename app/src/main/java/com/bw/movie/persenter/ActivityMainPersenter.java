package com.bw.movie.persenter;

import android.content.Context;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：ActivityMainPersenter
 * */
public    class ActivityMainPersenter  extends AppDelegate {
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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
        //测试显示
        Toast.makeText(context,"测试",Toast.LENGTH_SHORT).show();
    }
}
