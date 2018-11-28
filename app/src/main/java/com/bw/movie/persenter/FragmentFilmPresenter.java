package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.adapter.BasePageChangeListener;
import com.bw.movie.entity.SoonMovieEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.view.PagerAdapter3D;
import com.bw.movie.view.RotationPageTransformer;
import com.bw.movie.view.ViewPage3D;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentFilmPresenter extends AppDelegate {

    private static final int SOONMOVIELIST_CONTENT = 0x123;
    private Context context;
    private ViewPage3D viewPage3D;
    private PagerAdapter3D pagerAdapter3D;
    List<String> images = new ArrayList<>();

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
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("count", "10");
        getString(Http.SOONMOVIELIST_URL, SOONMOVIELIST_CONTENT, map);

    }

    private void initWeght() {
        viewPage3D = (ViewPage3D) getView(R.id.viepage_3d);
        viewPage3D.setOnPageChangeListener(new BasePageChangeListener() {
            @Override
            public void onPageSelected(int i) {

            }
        });
        pagerAdapter3D = new PagerAdapter3D(context);
        viewPage3D.setAdapter(pagerAdapter3D);
        viewPage3D.setPageTransformer(new RotationPageTransformer(), 2, 8);
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
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        toast(context,"请检查网络");
    }

    public void setSoonMovieData(String data) {
        images.clear();
        SoonMovieEntity soonMovieEntity = new Gson().fromJson(data, SoonMovieEntity.class);
        for (int i = 0; i < soonMovieEntity.getResult().size(); i++) {
            images.add(soonMovieEntity.getResult().get(i).getImageUrl());
        }
        pagerAdapter3D.setImageurl(images);
    }

}