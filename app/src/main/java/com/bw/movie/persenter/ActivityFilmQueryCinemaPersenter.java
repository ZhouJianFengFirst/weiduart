package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityFilmQueryCinema;
import com.bw.movie.adapter.RecommendSearchAdapter;
import com.bw.movie.entity.CinemaSearchBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * 作者:xujiahui
 * 作用:ActivityFilmQueryCinemaPersenter(film查询影院)
 */
public class ActivityFilmQueryCinemaPersenter extends AppDelegate implements View.OnClickListener {


    private Context mcontext;
    private XListView list1;
    private int count = 20;
    private int page = 1;
    private RecommendSearchAdapter recommendSearchAdapter;
    private String film_name;
    private String userId;
    private String sessionId;
    private ImageView imgreturn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_film_query_cinema;
    }

    @Override
    public void initData() {
        super.initData();
        initWidget();

        Intent intent = ((ActivityFilmQueryCinema) mcontext).getIntent();
        film_name = intent.getStringExtra("trim");
        userId = intent.getStringExtra("userId");
        sessionId = intent.getStringExtra("sessionId");

        dohttpSeach(film_name);
    }

    private void initWidget() {
        list1 = (XListView) getView(R.id.query_listview_cinema_list1);
        imgreturn = (ImageView) getView(R.id.query_img_cinema_return);
        imgreturn.setOnClickListener(this);
       recommendSearchAdapter = new RecommendSearchAdapter(mcontext);
    }


    //请求搜索
    private void dohttpSeach(String cinema_name) {
        HashMap map2 = new HashMap();
        map2.put("userId", userId);
        map2.put("sessionId", sessionId);
        map2.put("page", page);
        map2.put("count", count);
        map2.put("cinemaName", cinema_name);
        //请求附近
        getString(Http.CINEMASEARCH_URL, 2, map2);
    }


    //    获取上下文
    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 2:
                Logger.d("成功",data+"");
                Gson gson = new Gson();
                CinemaSearchBean cinemaSearchBean = gson.fromJson(data, CinemaSearchBean.class);
                List<CinemaSearchBean.ResultBean> searchlist = cinemaSearchBean.getResult();
                if (searchlist.size() > 1) {
                    list1.setAdapter(recommendSearchAdapter);
                    recommendSearchAdapter.setList(searchlist);
                    Logger.d("成功",searchlist.size()+"yyy");

                } else {
                    toast(mcontext, "没有找到关于" + film_name + "的影院");
//                    list1.setAdapter(recommendAdapter);
                }
                break;
        }
    }
    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.d("影片",msg.toString()+"失败");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_img_cinema_return:
                ((ActivityFilmQueryCinema)mcontext).finish();
                break;
        }

    }
}
