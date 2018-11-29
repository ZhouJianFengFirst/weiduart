package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.DrawableContainer;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.FileListAdapter;
import com.bw.movie.entity.HortMovieEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ActivityFilmPersenter extends AppDelegate implements View.OnClickListener {

    private Context context;
    private TextView txtHortmovie, txtHortShowing, txtUpcoming;
    private XListView listView;
    private static final int SOONMOVIELIST_CONTENT = 0x123;
    private static final int HOTMOVIELIST_CONTENT = 0x124;
    private static final int RELEAASEMOVIELIST_CONTENT = 0x125;
    private int flage = 1;
    private int hortshowingpage = 1;
    private int hortshowingcont = 10;

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

        //网络请求
        doHortMovieHttp();
    }

    /**
     * 热门电影
     */
    private void doHortMovieHttp() {

        Map<String, String> hortmoviemap = new HashMap<>();
        hortmoviemap.put("page", "" + hortmoviepage + "");
        hortmoviemap.put("count", "" + hortmoviecont + "");
        getString(Http.HOTMOVIELIST_URL, HOTMOVIELIST_CONTENT, hortmoviemap);

    }

    /**
     * 热映电影
     */
    public void doHortShowingHttp() {
        Map<String, String> hortshowingmap = new HashMap<>();
        hortshowingmap.put("page", "" + hortshowingpage + "");
        hortshowingmap.put("count", "" + hortshowingcont + "");
        getString(Http.SOONMOVIELIST_URL, SOONMOVIELIST_CONTENT, hortshowingmap);
    }

    /**
     * 即将上映
     */
    public void doHortUpCommimg() {
        Map<String, String> upcomingmap = new HashMap<>();
        upcomingmap.put("page", "" + upcomingpage + "");
        upcomingmap.put("count", "" + upcomingcont + "");
        getString(Http.RELEAASEMOVIELIST_URL, RELEAASEMOVIELIST_CONTENT, upcomingmap);
    }

    private void initWeight() {
        //初始化控件
        txtHortmovie = (TextView) getView(R.id.txt_hortmovie);
        txtHortShowing = (TextView) getView(R.id.txt_hortshowing);
        txtUpcoming = (TextView) getView(R.id.txt_upcoming);
        listView = (XListView) getView(R.id.xshowlist);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
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
                listView.stopRefresh();
            }

            @Override
            public void onLoadMore() {
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
        }
    }

    /**
     * 設置热门电影的数据
     *
     * @param hortMovieData
     */
    public void setHortMovieData(String hortMovieData) {
        HortMovieEntity entity = new Gson().fromJson(hortMovieData, HortMovieEntity.class);
        filmAdapter.setHortList(entity.getResult());
    }

    /**
     * 设置热映电影的数据
     *
     * @param hortShowingData
     */
    public void setHortShowingData(String hortShowingData) {
        HortMovieEntity entity = new Gson().fromJson(hortShowingData, HortMovieEntity.class);
        filmAdapter.setHortList(entity.getResult());
    }

    /**
     * 设置即将上映的电影数据
     *
     * @param upCommingData
     */
    public void setUpCommingData(String upCommingData) {
        HortMovieEntity entity = new Gson().fromJson(upCommingData, HortMovieEntity.class);
        filmAdapter.setHortList(entity.getResult());
    }
}
