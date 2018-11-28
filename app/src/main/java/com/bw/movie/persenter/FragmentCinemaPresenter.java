package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
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
 * 作用：电影院页面
 */
public class FragmentCinemaPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView recommend;
    private TextView nearby;
    private FrameLayout frag;
    private XListView list1;
    private String reurl = "/movieApi/cinema/v1/findRecommendCinemas";
    private RecommendAdapter recommendAdapter;
    private int page = 1;
    private String longitude = "116.30551391385724";
    private String latitude = "40.04571807462411";
    private RelativeLayout relay;
    private ImageView search;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        dohttp(page);
        //实例化适配器
        recommendAdapter = new RecommendAdapter(context);
        list1.setAdapter(recommendAdapter);
//        list1.setPullLoadEnable(true);
        //设置上拉下拉
        list1.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                dohttp(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                dohttp(page);
//                toast(context,"没有更多了");
            }
        });

    }

    //请求推荐网络
    private void dohttp(int page) {
        HashMap map = new HashMap();
        map.put("userId", "18");
        map.put("sessionId", "15320748258726");
        map.put("page", page);
        map.put("count", "20");
        //请求推荐
        getString(reurl, 0, map);

        list1.stopRefresh();
//        list1.stopLoadMore();
    }

    //请求附近网络
    private void dohttp1(int page, String longitude, String latitude) {
        HashMap map1 = new HashMap();
        map1.put("userId", "18");
        map1.put("sessionId", "15320748258726");
        map1.put("page", page);
        map1.put("count", "20");
        map1.put("longitude", longitude);
        map1.put("latitude", latitude);
        //请求附近
        getString(reurl, 1, map1);
    }

    //请求成功返回
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
//                Log.i("推荐影院",data);
                recommendBean recommendBean = new Gson().fromJson(data, recommendBean.class);
                List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = recommendBean.getResult().getNearbyCinemaList();
                recommendAdapter.setList(nearbyCinemaList);
                break;
            case 1:
                recommendBean recommendBean1 = new Gson().fromJson(data, recommendBean.class);
                List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList1 = recommendBean1.getResult().getNearbyCinemaList();
                recommendAdapter.setList(nearbyCinemaList1);
                break;
        }
    }

    //找控件的方法
    private void initwidget() {
        recommend = (TextView) getView(R.id.text_cinema_recommend);
        nearby = (TextView) getView(R.id.text_cinema_nearby);
        list1 = (XListView) getView(R.id.listview_cinema_list1);
        relay = (RelativeLayout) getView(R.id.relay_cinema_search);
        search = (ImageView) getView(R.id.image_cinema_search);
        setClick(this, R.id.text_cinema_recommend, R.id.text_cinema_nearby);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_cinema_recommend:
                dohttp(page);
                nearby.setBackgroundResource(R.drawable.square_gray);
                recommend.setBackgroundResource(R.drawable.square_purple);
                recommend.setTextColor(Color.WHITE);
                nearby.setTextColor(Color.BLACK);
                break;
            case R.id.text_cinema_nearby:
                dohttp1(page, longitude, latitude);
                nearby.setBackgroundResource(R.drawable.square_purple);
                recommend.setBackgroundResource(R.drawable.square_gray);
                nearby.setTextColor(Color.WHITE);
                recommend.setTextColor(Color.BLACK);
                break;
            case R.id.relay_cinema_search:

                break;

        }
    }
}
