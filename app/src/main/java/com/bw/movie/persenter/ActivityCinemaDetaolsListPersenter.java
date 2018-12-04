package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetaolsList;
import com.bw.movie.adapter.CinemaSessionsAdapter;
import com.bw.movie.entity.CinemaSessionBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：周建峰
 * ActivityCinemaDetaolsListPersenter
 */
public class ActivityCinemaDetaolsListPersenter extends AppDelegate {

    private static final int CINEMAFLOW_CONTENT = 0x1208;
    private Context mContext;
    private TextView txtCinemaName, txtCinemaAddress;
    private RecyclerView listview;
    private int movieId;
    private String cinemaflow;
    private int filmId;
    private CinemaSessionsAdapter cinemasessAdapter;

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mContext = context;
    }

    @Override
    public void initData() {
        super.initData();
        initWeight();
        Intent intent = ((ActivityCinemaDetaolsList) mContext).getIntent();
        movieId = intent.getIntExtra("movieId", 0);
        filmId = intent.getIntExtra("filmId", 0);
        //网络请求
        getCinemaData(movieId, filmId);
    }

    private void getCinemaData(int movieId, int filmId) {
        Map<String, String> map = new HashMap<>();
        map.put("cinemasId", filmId + "");
        map.put("movieId", movieId + "");
        getString(Http.CINEMASESSION_URL, CINEMAFLOW_CONTENT, map);
    }

    private void initWeight() {
        //初始化数据
        txtCinemaName = (TextView) getView(R.id.txt_cinema_name);
        txtCinemaAddress = (TextView) getView(R.id.txt_address);
        listview = (RecyclerView) getView(R.id.show_cinema_list);

        //初始化Adapter
        cinemasessAdapter = new CinemaSessionsAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        listview.setLayoutManager(layoutManager);
        listview.setAdapter(cinemasessAdapter);
    }


    /**
     * 获取userid
     *
     * @return
     */
    public String getUserId() {
        String userId = (String) SpUtil.getSpData(mContext, "userId", "");
        return userId;
    }

    /**
     * 获取Session
     *
     * @return
     */
    public String getUserSession() {
        String sessionId = (String) SpUtil.getSpData(mContext, "sessionId", "");
        return sessionId;
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case CINEMAFLOW_CONTENT:
                setCinemaflow(data);
                break;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_list;
    }

    public void setCinemaflow(String data) {
        CinemaSessionBean bean = new Gson().fromJson(data, CinemaSessionBean.class);
        cinemasessAdapter.setList(bean.getResult());
    }
}