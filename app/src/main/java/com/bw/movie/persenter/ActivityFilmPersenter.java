package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityFilm;
import com.bw.movie.activitys.ActivityFilmDetails;
import com.bw.movie.adapter.FileListAdapter;
import com.bw.movie.entity.BackJson;
import com.bw.movie.entity.HortMovieEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ActivityFilmPersenter extends AppDelegate implements View.OnClickListener, FileListAdapter.BackDataListener {

    private static final int FOLLOW_MOVIE_CONTENT = 0x126;
    private Context context;
    private TextView txtHortmovie, txtHortShowing, txtUpcoming;
    private XListView listView;
    private static final int SOONMOVIELIST_CONTENT = 0x123;
    private static final int HOTMOVIELIST_CONTENT = 0x124;
    private static final int RELEAASEMOVIELIST_CONTENT = 0x125;
    private HortMovieEntity movieEntity;
    private int flage = 1;
    private int hortshowingpage = 1;
    private int hortshowingcont = 10;
    private int moviesize = 0;

    private int hortmoviepage = 1;
    private int hortmoviecont = 10;


    private int upcomingpage = 1;
    private int upcomingcont = 10;

    private FileListAdapter filmAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_film;
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initWeight();
        Intent intent = ((ActivityFilm) context).getIntent();
        int flag = intent.getIntExtra("flag", 0);
        //网络请求
        switch (flag) {
            case 1:
                flage = 1;
                txtHortmovie.setBackgroundResource(R.drawable.square_purple);
                txtHortmovie.setTextColor(Color.WHITE);
                txtHortShowing.setBackgroundResource(R.drawable.square_gray);
                txtHortShowing.setTextColor(Color.GRAY);
                txtUpcoming.setBackgroundResource(R.drawable.square_gray);
                txtUpcoming.setTextColor(Color.GRAY);
                doHortMovieHttp();
                break;
            case 2:
                flage = 2;
                txtHortmovie.setBackgroundResource(R.drawable.square_gray);
                txtHortmovie.setTextColor(Color.GRAY);
                txtHortShowing.setBackgroundResource(R.drawable.square_purple);
                txtHortShowing.setTextColor(Color.WHITE);
                txtUpcoming.setBackgroundResource(R.drawable.square_gray);
                txtUpcoming.setTextColor(Color.GRAY);
                doHortShowingHttp();
                break;
            case 3:
                flage = 3;
                txtHortmovie.setBackgroundResource(R.drawable.square_gray);
                txtHortmovie.setTextColor(Color.GRAY);
                txtHortShowing.setBackgroundResource(R.drawable.square_gray);
                txtHortShowing.setTextColor(Color.GRAY);
                txtUpcoming.setBackgroundResource(R.drawable.square_purple);
                txtUpcoming.setTextColor(Color.WHITE);
                doHortUpCommimg();
                break;
        }
    }

    /**
     * 热门电影
     */
    private void doHortMovieHttp() {

        Map<String, String> hortmoviemap = new HashMap<>();
        hortmoviemap.put("page", "" + hortmoviepage + "");
        hortmoviemap.put("count", "" + hortmoviecont + "");
        hortmoviemap.put("userId", getuserId());
        hortmoviemap.put("sessionId", getUserSession());
        getString(Http.HOTMOVIELIST_URL, HOTMOVIELIST_CONTENT, hortmoviemap);

    }

    /**
     * 热映电影
     */
    public void doHortShowingHttp() {
        Map<String, String> hortshowingmap = new HashMap<>();
        hortshowingmap.put("page", "" + hortshowingpage + "");
        hortshowingmap.put("count", "" + hortshowingcont + "");
        hortshowingmap.put("userId", getuserId());
        hortshowingmap.put("sessionId", getUserSession());
        getString(Http.SOONMOVIELIST_URL, SOONMOVIELIST_CONTENT, hortshowingmap);
    }

    /**
     * 即将上映
     */
    public void doHortUpCommimg() {

        Map<String, String> upcomingmap = new HashMap<>();
        upcomingmap.put("page", "" + upcomingpage + "");
        upcomingmap.put("count", "" + upcomingcont + "");
        upcomingmap.put("userId", getuserId());
        upcomingmap.put("sessionId", getUserSession());
        getString(Http.RELEAASEMOVIELIST_URL, RELEAASEMOVIELIST_CONTENT, upcomingmap);
    }

    public String getuserId() {
        String userId = (String) SpUtil.getSpData(context, "userId", "");
        return userId;
    }

    public String getUserSession() {
        String sessionId = (String) SpUtil.getSpData(context, "sessionId", "");
        return sessionId;
    }

    private void initWeight() {
        //初始化控件
        getView(R.id.backimage).setOnClickListener(this);
        txtHortmovie = (TextView) getView(R.id.txt_hortmovie);
        txtHortShowing = (TextView) getView(R.id.txt_hortshowing);
        txtUpcoming = (TextView) getView(R.id.txt_upcoming);
        listView = (XListView) getView(R.id.xshowlist);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int movieId = movieEntity.getResult().get(position).getId();
                Intent intent = new Intent(context, ActivityFilmDetails.class);
                intent.putExtra("movieId", movieId);
                ((ActivityFilm) context).startActivity(intent);
            }
        });
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                doHttp();
                listView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                if (moviesize <= 10) {
                    listView.stopLoadMore();
                    return;
                }
                switch (flage) {
                    case 1:
                        hortmoviecont += 5;
                        doHortMovieHttp();
                        break;
                    case 2:
                        hortshowingcont += 5;
                        doHortShowingHttp();
                        break;
                    case 3:
                        upcomingcont += 5;
                        doHortUpCommimg();
                        break;
                }
                listView.stopLoadMore();
            }
        });
        setClick(this, R.id.txt_hortmovie, R.id.txt_hortshowing, R.id.txt_upcoming);

        //初始化适配器
        filmAdapter = new FileListAdapter(context);
        filmAdapter.setListener(this);
        listView.setAdapter(filmAdapter);
    }


    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case HOTMOVIELIST_CONTENT:
                setHortMovieData(data);
                break;
            case SOONMOVIELIST_CONTENT:
                setHortShowingData(data);
                break;
            case RELEAASEMOVIELIST_CONTENT:
                setUpCommingData(data);
                break;
            case FOLLOW_MOVIE_CONTENT:
                followMovieUser(data);
                break;
        }
    }

    private void followMovieUser(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            toast(context, backJson.getMessage());
            doHttp();
        } else {
            toast(context, "取消关注失败");
        }
    }

    public void doHttp() {
        switch (flage) {
            case 1:
                hortmoviepage = 1;
                doHortMovieHttp();
                break;
            case 2:
                hortmoviepage = 1;
                doHortShowingHttp();
                break;
            case 3:
                upcomingpage = 1;
                doHortUpCommimg();
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        toast(context, "请检查网络");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_hortmovie:
                flage = 1;
                txtHortmovie.setBackgroundResource(R.drawable.square_purple);
                txtHortmovie.setTextColor(Color.WHITE);
                txtHortShowing.setBackgroundResource(R.drawable.square_gray);
                txtHortShowing.setTextColor(Color.GRAY);
                txtUpcoming.setBackgroundResource(R.drawable.square_gray);
                txtUpcoming.setTextColor(Color.GRAY);
                doHortMovieHttp();
                break;
            case R.id.txt_hortshowing:
                flage = 2;
                txtHortmovie.setBackgroundResource(R.drawable.square_gray);
                txtHortmovie.setTextColor(Color.GRAY);
                txtHortShowing.setBackgroundResource(R.drawable.square_purple);
                txtHortShowing.setTextColor(Color.WHITE);
                txtUpcoming.setBackgroundResource(R.drawable.square_gray);
                txtUpcoming.setTextColor(Color.GRAY);
                doHortShowingHttp();
                break;
            case R.id.txt_upcoming:
                flage = 3;
                txtHortmovie.setBackgroundResource(R.drawable.square_gray);
                txtHortmovie.setTextColor(Color.GRAY);
                txtHortShowing.setBackgroundResource(R.drawable.square_gray);
                txtHortShowing.setTextColor(Color.GRAY);
                txtUpcoming.setBackgroundResource(R.drawable.square_purple);
                txtUpcoming.setTextColor(Color.WHITE);
                doHortUpCommimg();
                break;
            case R.id.backimage:
                ((ActivityFilm) context).finish();
                break;
        }
    }

    /**
     * 設置热门电影的数据
     *
     * @param hortMovieData
     */
    public void setHortMovieData(String hortMovieData) {
        HortMovieEntity entity = new Gson().fromJson(hortMovieData, HortMovieEntity.class);
        this.movieEntity = entity;
        moviesize = entity.getResult().size();
        filmAdapter.setHortList(entity.getResult());
    }

    /**
     * 设置热映电影的数据
     *
     * @param hortShowingData
     */
    public void setHortShowingData(String hortShowingData) {
        HortMovieEntity entity = new Gson().fromJson(hortShowingData, HortMovieEntity.class);
        this.movieEntity = entity;
        moviesize = entity.getResult().size();
        filmAdapter.setHortList(entity.getResult());
    }

    /**
     * 设置即将上映的电影数据
     *
     * @param upCommingData
     */
    public void setUpCommingData(String upCommingData) {
        HortMovieEntity entity = new Gson().fromJson(upCommingData, HortMovieEntity.class);
        this.movieEntity = entity;
        moviesize = entity.getResult().size();
        filmAdapter.setHortList(entity.getResult());
    }

    @Override
    public void followMovie(int movieId, boolean start) {

        Boolean islogin = (Boolean) SpUtil.getSpData(context, "isLogin", false);
        if (islogin) {
            String userId = (String) SpUtil.getSpData(context, "userId", "");
            String sessionId = (String) SpUtil.getSpData(context, "sessionId", "");
            Map<String, String> hmap = new HashMap<>();
            hmap.put("userId", userId);
            hmap.put("sessionId", sessionId);
            Map<String, String> qmap = new HashMap<>();
            qmap.put("movieId", movieId + "");
            if (start) {
                HeadOrQuertGet(Http.FOLLOW_MOVIE, FOLLOW_MOVIE_CONTENT, hmap, qmap);
            } else {
                HeadOrQuertGet(Http.CANCEL_MOVIE, FOLLOW_MOVIE_CONTENT, hmap, qmap);
            }
        } else {
            toast("温馨提示", "请先登录", 3);
        }
    }
}