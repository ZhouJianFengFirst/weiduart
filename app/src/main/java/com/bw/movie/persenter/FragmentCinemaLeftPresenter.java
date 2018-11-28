package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.entity.recommendBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院评论
 */
public class FragmentCinemaLeftPresenter extends AppDelegate {

    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema_left;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();

    }
    //找控件的方法
    private void initwidget() {
        
    }

  
}
