package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.fragments.FragmentCinema;
import com.bw.movie.fragments.FragmentFilm;
import com.bw.movie.fragments.FragmentMe;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.tabview.TabView;
import com.bw.movie.tabview.TabViewChild;
import com.bw.movie.utils.UltimateBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：ActivityMainPersenter
 * */
public    class ActivityMainPersenter  extends AppDelegate {
    private Context context;
    private TabView tabView;
    private List<TabViewChild> childs = new ArrayList<>();
    private FragmentManager manger;

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
        //初始化页面
        initwidget();

        TabViewChild tabFilm = new TabViewChild(R.drawable.film_yes,R.drawable.film_no,"影片",new FragmentFilm());
        TabViewChild tabCineam = new TabViewChild(R.drawable.cinema_yes,R.drawable.cinema_no,"影院",new FragmentCinema());
        TabViewChild tabMe = new TabViewChild(R.drawable.my_yes,R.drawable.my_no,"我的",new FragmentMe());
        childs.add(tabFilm);
        childs.add(tabCineam);
        childs.add(tabMe);
        manger = ((MainActivity)context).getSupportFragmentManager();
        tabView.setTabViewChild(childs,manger);
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {
                if (position == 1) {
                    setar();
                }
            }
        });
    }

    private void setar() {
        UltimateBar.newColorBuilder()
                .statusColor(Color.parseColor("#780E40"))
                .build((MainActivity) context)
                .apply();
    }

    private void initwidget() {
        tabView = getView(R.id.tv_nav);
    }
}