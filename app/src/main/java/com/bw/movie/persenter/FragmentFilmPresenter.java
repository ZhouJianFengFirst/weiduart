package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityFilm;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.adapter.BackGroundPageAdapter;
import com.bw.movie.adapter.BasePageChangeAdapter;
import com.bw.movie.adapter.MovieRecyAdapter;
import com.bw.movie.entity.HortMovieEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.view.ListFilmView;
import com.bw.movie.view.PagerAdapter3D;
import com.bw.movie.view.RotationPageTransformer;
import com.bw.movie.view.ViewPage3D;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FragmentFilmPresenter extends AppDelegate implements View.OnClickListener{

    private static final int SOONMOVIELIST_CONTENT = 0x123;
    private static final int HOTMOVIELIST_CONTENT = 0x124;
    private static final int RELEAASEMOVIELIST_CONTENT = 0x125;
    private Context context;
    private ViewPage3D viewPage3D;
    private PagerAdapter3D pagerAdapter3D;
    private ListFilmView horMovie, hortShowing, upcoming;
    private MovieRecyAdapter upcomingAdapter, hortMovieAdapter, hortShowingAdapter;
    private BackGroundPageAdapter backGroundPageAdapter;
    private LinearLayout linearLine;
    private HortMovieEntity entity;

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initWeght();

        //网络请求
        doHttp();

    }

    /**
     * 网络请求
     */
    private void doHttp() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("count", "10");
        getString(Http.SOONMOVIELIST_URL, SOONMOVIELIST_CONTENT, map);
        getString(Http.HOTMOVIELIST_URL, HOTMOVIELIST_CONTENT, map);
        getString(Http.RELEAASEMOVIELIST_URL, RELEAASEMOVIELIST_CONTENT, map);
    }


    /**
     * 初始化
     */
    private void initWeght() {
        //初始化控件
        ViewPager viewPager =  (ViewPager)getView(R.id.back_viewpage);
        viewPager.addOnPageChangeListener(new BasePageChangeAdapter() {
            @Override
            public void onPageSelected(int i) {
                viewPage3D.setCurrentItem(i);
            }
        });
        horMovie = (ListFilmView) getView(R.id.lf_hortmovie);
        hortShowing = (ListFilmView) getView(R.id.lf_hortshowing);
        upcoming = (ListFilmView) getView(R.id.lf_upcoming);
        viewPage3D = (ViewPage3D) getView(R.id.viepage_3d);
        viewPage3D.setOnPageChangeListener(new BasePageChangeAdapter() {
            @Override
            public void onPageSelected(int i) {
                putLine(i);
            }
        });
        linearLine = (LinearLayout)getView(R.id.layout_put_line);
        setClick(this,R.id.lf_hortmovie,R.id.lf_hortshowing,R.id.lf_upcoming);

        //初始化适配器
        upcomingAdapter = new MovieRecyAdapter(context);
        hortMovieAdapter = new MovieRecyAdapter(context);
        hortShowingAdapter = new MovieRecyAdapter(context);
        pagerAdapter3D = new PagerAdapter3D(context);
        backGroundPageAdapter = new BackGroundPageAdapter(context);

        //設置适配器
        viewPage3D.setAdapter(pagerAdapter3D);
        viewPage3D.setPageTransformer(new RotationPageTransformer(), 2, 8);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context);
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        upcoming.setAdapter(upcomingAdapter, linearLayoutManager1);
        horMovie.setAdapter(hortMovieAdapter,linearLayoutManager2);
        hortShowing.setAdapter(hortShowingAdapter,linearLayoutManager3);
        viewPager.setAdapter(backGroundPageAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film;
    }


    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case SOONMOVIELIST_CONTENT:
                setSoonMovieData(data);
                break;
            case HOTMOVIELIST_CONTENT:
                 setHortMovieData(data);
                break;
            case RELEAASEMOVIELIST_CONTENT:
                setReleaseMovieData(data);
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        toast(context, "请检查网络");
    }


    /**
     * 设置即将上映电影的数据
     * @param data
     */
    public void setSoonMovieData(String data) {

        HortMovieEntity entity = new Gson().fromJson(data, HortMovieEntity.class);
        upcomingAdapter.setList(entity.getResult());
    }


    /**
     * 热门电影的数据
     * @param data
     */
    public void setHortMovieData(String data) {
        HortMovieEntity entity = new Gson().fromJson(data, HortMovieEntity.class);
        hortMovieAdapter.setList(entity.getResult());
    }


    /**
     * 设置热映的电影数据
     * @param data
     */
    public void setReleaseMovieData(String data) {
         entity = new Gson().fromJson(data, HortMovieEntity.class);
        backGroundPageAdapter.setPage(entity.getResult().size());
        pagerAdapter3D.setList(entity.getResult());
        hortShowingAdapter.setList(entity.getResult());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lf_hortmovie:
                Intent intentHortMovie = new Intent(context, ActivityFilm.class);
                intentHortMovie.putExtra("flag",1);
                ((MainActivity)context).startActivity(intentHortMovie);
                break;
            case R.id.lf_hortshowing:
                Intent intentHortShowing = new Intent(context, ActivityFilm.class);
                intentHortShowing.putExtra("flag",2);
                ((MainActivity)context).startActivity(intentHortShowing);
                break;
            case R.id.lf_upcoming:
                Intent intentUpComing = new Intent(context, ActivityFilm.class);
                intentUpComing.putExtra("flag",3);
                ((MainActivity)context).startActivity(intentUpComing);
                break;
        }
    }

    public void putLine(int page){
        linearLine.removeAllViews();
        for (int i = 0; i < entity.getResult().size(); i++) {
            View view = new View(context);
            if (page == i) {
                view.setBackgroundResource(R.drawable.purplechange);
            } else {
                view.setBackgroundResource(R.drawable.square_gray);
            }
            linearLine.addView(view);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.weight = 5;
            params.height = 2;
            view.setLayoutParams(params);
        }
    }


}