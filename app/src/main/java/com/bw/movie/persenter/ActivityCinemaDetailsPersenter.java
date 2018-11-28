package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.mvp.view.AppDelegate;
/**
*作者：gaojiabao
*时间：2018/11/28 8:51
*作用：影院详情页面
*/
public class ActivityCinemaDetailsPersenter extends AppDelegate {
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_details;
    }

    @Override
    public void initContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = ((ActivityCinemaDetails) context).getIntent();
        String id = intent.getStringExtra("id");
        toast(context,"影院id"+id);
    }
}
