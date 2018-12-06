package com.bw.movie.persenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.adapter.BasePageChangeAdapter;
import com.bw.movie.fragments.FragmentCinema;
import com.bw.movie.fragments.FragmentFilm;
import com.bw.movie.fragments.FragmentMe;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.UltimateBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：ActivityMainPersenter
 */
public class ActivityMainPersenter extends AppDelegate implements View.OnClickListener {

    private Context context;
    private ImageView btnFilm, btnCream, btnMe;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //重写初始化上下文方法
    @Override
    public void initContext(Context context) {
        //删了super这行提上去上下文
        this.context = context;
    }

    //重写初始化数据方法
    @Override
    public void initData() {
        super.initData();
        //初始化页面
        initwidget();

    }

  /*  private void setar() {
        UltimateBar.newColorBuilder()
                *//* .statusColor(Color.parseColor("#780E40"))*//*
                .build((MainActivity) context)
                .apply();
    }*/

    private void initwidget() {
        //初始化控件
        btnFilm = (ImageView) getView(R.id.btn_film);
        btnCream = (ImageView) getView(R.id.btn_cinema);
        btnMe = (ImageView) getView(R.id.btn_my);
        setClick(this, R.id.btn_film, R.id.btn_cinema, R.id.btn_my);
        viewPager = (ViewPager) getView(R.id.viewpage);
        viewPager.addOnPageChangeListener(new BasePageChangeAdapter() {
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        btnFilm.setImageResource(R.drawable.film_yes);
                        btnCream.setImageResource(R.drawable.cinema_no);
                        btnMe.setImageResource(R.drawable.my_no);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        btnFilm.setImageResource(R.drawable.film_no);
                        btnCream.setImageResource(R.drawable.cinema_yes);
                        btnMe.setImageResource(R.drawable.my_no);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        btnFilm.setImageResource(R.drawable.film_no);
                        btnCream.setImageResource(R.drawable.cinema_no);
                        btnMe.setImageResource(R.drawable.my_yes);
                        break;
                }
            }
        });

        //初始化fragment
        FragmentFilm filmFragment = new FragmentFilm();
        FragmentCinema creamFragment = new FragmentCinema();
        FragmentMe meFragment = new FragmentMe();

        fragments.add(filmFragment);
        fragments.add(creamFragment);
        fragments.add(meFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(((MainActivity) context).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_film:
                viewPager.setCurrentItem(0);
                btnFilm.setImageResource(R.drawable.film_yes);
                btnCream.setImageResource(R.drawable.cinema_no);
                btnMe.setImageResource(R.drawable.my_no);
                break;
            case R.id.btn_cinema:
                viewPager.setCurrentItem(1);
                btnFilm.setImageResource(R.drawable.film_no);
                btnCream.setImageResource(R.drawable.cinema_yes);
                btnMe.setImageResource(R.drawable.my_no);
                break;
            case R.id.btn_my:
                viewPager.setCurrentItem(2);
                btnFilm.setImageResource(R.drawable.film_no);
                btnCream.setImageResource(R.drawable.cinema_no);
                btnMe.setImageResource(R.drawable.my_yes);
                break;

        }
    }
}