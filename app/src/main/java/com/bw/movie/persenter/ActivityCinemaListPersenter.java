package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetaolsList;
import com.bw.movie.activitys.ActivityCinemaList;
import com.bw.movie.adapter.CinemaListAdapter;
import com.bw.movie.contract.Contract;
import com.bw.movie.entity.BackJson;
import com.bw.movie.entity.CinemaListBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.SpUtil;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ActivityCinemaListPersenter extends AppDelegate implements Contract.BackIsFocusListener, Contract.BackMovieIdListener {

    private static final int FINDCINEMAS_BYMOVIE_CONTENT = 0x201;
    private static final int CINEMAHEART_CONTENT = 0x202;
    private static final int CINEMAHEART_NO_CONTENT = 0x203;
    private Context context;
    private TextView txtTitle;
    private XListView listView;
    private CinemaListAdapter cinemaListAdapter;
    private String filmName;
    private int filmId;
    private String picUrl;
    private String content;

    @Override
    public void initData() {
        super.initData();

        //获取movieId
        Intent intent = ((ActivityCinemaList) context).getIntent();
        filmId = intent.getIntExtra("filmId", 0);
        filmName = intent.getStringExtra("filmName");
        picUrl = intent.getStringExtra("picUrl");
        content = intent.getStringExtra("content");
        //初始化控件
        initWeight();

        //网络请求
        getCinemaData(filmId);

    }

    private void initWeight() {
        //初始化控件
        txtTitle = (TextView) getView(R.id.txt_title);
        txtTitle.setText(filmName);
        listView = (XListView) getView(R.id.listview_cinema_list);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                listView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                listView.stopLoadMore();
            }
        });
        //初始化adapter
        cinemaListAdapter = new CinemaListAdapter(context);
        cinemaListAdapter.setListener(this);
        cinemaListAdapter.setBackMovieIdListener(this);
        listView.setAdapter(cinemaListAdapter);
    }

    /**
     * @param filmId
     */
    private void getCinemaData(int filmId) {
        Map<String, String> map = new HashMap<>();
        map.put("movieId", filmId + "");
        getString(Http.FINDCINEMAS_BYMOVIEID_URL, FINDCINEMAS_BYMOVIE_CONTENT, map);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case FINDCINEMAS_BYMOVIE_CONTENT:
                setCinemaList(data);
                break;
            case CINEMAHEART_CONTENT:
                setCinemaHartSuccess(data);
                break;
            case CINEMAHEART_NO_CONTENT:
                setCinemaHartErrot(data);
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema;
    }

    public void setCinemaList(String cinemaList) {
        CinemaListBean cinemaListBean = new Gson().fromJson(cinemaList, CinemaListBean.class);
        cinemaListAdapter.setList(cinemaListBean.getResult());
    }

    @Override
    public void backFocus(boolean focus, int cinemaId) {
        if (focus) {
            HashMap hmap = new HashMap();
            hmap.put("userId", getUserId());
            hmap.put("sessionId", getUserSession());
            HashMap qmap = new HashMap();
            qmap.put("cinemaId", cinemaId + "");
            HeadOrQuertGet(Http.CINEMAHEART_URL, CINEMAHEART_CONTENT, hmap, qmap);
        } else {
            HashMap hmap = new HashMap();
            hmap.put("userId", getUserId());
            hmap.put("sessionId", getUserSession());
            HashMap qmap = new HashMap();
            qmap.put("cinemaId", cinemaId);
            HeadOrQuertGet(Http.CINEMAHEART_NO_URL, CINEMAHEART_NO_CONTENT, hmap, qmap);
        }
    }

    /**
     * 获取userid
     *
     * @return
     */
    public String getUserId() {
        String userId = (String) SpUtil.getSpData(context, "userId", "");
        return userId;
    }

    /**
     * 获取Session
     *
     * @return
     */
    public String getUserSession() {
        String sessionId = (String) SpUtil.getSpData(context, "sessionId", "");
        return sessionId;
    }

    public void setCinemaHartSuccess(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            toast(context, backJson.getMessage());
            getCinemaData(filmId);
        } else {
            toast(context, backJson.getMessage());
        }

    }

    public void setCinemaHartErrot(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            toast(context, backJson.getMessage());
            getCinemaData(filmId);
        } else {
            toast(context, backJson.getMessage());
        }
    }

    @Override
    public void backMovieId(int movieId, String movieName, String movieAddress) {
        Intent intent = new Intent(context, ActivityCinemaDetaolsList.class);
        intent.putExtra("movieId", movieId);
        intent.putExtra("filmId", filmId);
        intent.putExtra("picUrl", picUrl);
        intent.putExtra("movieName", movieName);
        intent.putExtra("movieAddress", movieAddress);
        intent.putExtra("content", content);
        //跳转影院详情
        context.startActivity(intent);
    }
}