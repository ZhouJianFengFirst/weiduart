package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityMap;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.adapter.RecommendSearchAdapter;
import com.bw.movie.entity.CinemaSearchBean;
import com.bw.movie.entity.DiscussDzBean;
import com.bw.movie.entity.recommendBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.LocationUtils;
import com.bw.movie.utils.Logger;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院页面
 */
public class FragmentCinemaPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView recommend, nearby, ss;
    private FrameLayout frag;
    private XListView list1;
    private RecommendAdapter recommendAdapter;
    private String longitude = "116.30551391385724";
    private String latitude = "40.04571807462411";
    private RelativeLayout relay, relay_yes;
    private ImageView search;
    private EditText ed;
    private RecommendSearchAdapter recommendSearchAdapter;
    private String message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1,cinema_name;
    private int count=20;
    private int page = 1;
    private boolean isRe=false;
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
        //获取经纬度
        getlongitude();
        initwidget();
        dohttp(page);
        //实例化适配器
        recommendAdapter = new RecommendAdapter(context);
        recommendSearchAdapter = new RecommendSearchAdapter(context);
        //设置上拉下拉    list1.setPullLoadEnable(true);
        list1.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if(isRe){
                    dohttp1(page, longitude, latitude);
                }else if(!isRe){
                    dohttp(page);
                }
            }

            @Override
            public void onLoadMore() {
                page++;
                if(isRe){
                    dohttp1(page, longitude, latitude);
                }else if(!isRe){
                    dohttp(page);
                }
//                toast(context,"没有更多了");
            }
        });
       //设置关注的回调
        doAdapterListener();
    }
    //获取经纬度
    private void getlongitude() {
        Location location = LocationUtils.getInstance(context).showLocation();
        if (location != null) {
            longitude = String.valueOf(location.getLatitude());
            latitude = String.valueOf(location.getLongitude());
            Log.i("FLY.LocationUtils", "纬度"+latitude+"经度"+longitude);
        }
    }
    //设置关注的回调
    private void doAdapterListener() {
        //影院关注的回调
        recommendAdapter.result(new RecommendAdapter.SetOnHeart() {
            @Override
            public void success(List<recommendBean.ResultBean.NearbyCinemaListBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId",userId1);
                hmap.put("sessionId",sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId","1");
                HeadOrQuertGet(Http.CINEMAHEART_URL,3,hmap,qmap);
            }
        });
        //搜索关注的回调
        recommendSearchAdapter.result(new RecommendSearchAdapter.SetOnHeart() {
            @Override
            public void success(List<CinemaSearchBean.ResultBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId",userId1);
                hmap.put("sessionId",sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId","1");
                HeadOrQuertGet(Http.CINEMAHEART_NO_URL,3,hmap,qmap);
            }
        });
        //影院取关
        recommendAdapter.resultQg(new RecommendAdapter.SetOnHeartQg() {
            @Override
            public void success(List<recommendBean.ResultBean.NearbyCinemaListBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId",userId1);
                hmap.put("sessionId",sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId","1");
                HeadOrQuertGet(Http.CINEMAHEART_NO_URL,4,hmap,qmap);
            }
        });
        //搜索取关
        recommendSearchAdapter.resultQg(new RecommendSearchAdapter.SetOnHeartQg() {
            @Override
            public void success(List<CinemaSearchBean.ResultBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId",userId1);
                hmap.put("sessionId",sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId","1");
                HeadOrQuertGet(Http.CINEMAHEART_URL,4,hmap,qmap);
            }
        });

    }
    //请求推荐网络
    private void dohttp(int page) {
        if(userId1==null){
            userId1="18";
        }
        if(sessionId1==null){
            sessionId1="15320748258726";
        }
        HashMap map = new HashMap();
        map.put("userId",userId1);
        map.put("sessionId", sessionId1);
        map.put("page", page);
        map.put("count",count);
        //请求推荐
        getString(Http.CINEMAALL_URL, 0, map);
        list1.stopRefresh();
//        list1.stopLoadMore();
    }
    //请求附近网络
    private void dohttp1(int page, String longitude, String latitude) {
        if(userId1==null){
            userId1="18";
        }
        if(sessionId1==null){
            sessionId1="15320748258726";
        }
        HashMap map1 = new HashMap();
        map1.put("userId",userId1);
        map1.put("sessionId", sessionId1);
        map1.put("page", page);
        map1.put("count",count);
        map1.put("longitude", longitude);
        map1.put("latitude", latitude);
        //请求附近
        getString(Http.CINEMARE_URL, 1, map1);
        list1.stopRefresh();
        Logger.i("网络里的经纬度",longitude+"经度"+latitude);
    }
    //请求搜索
    private void dohttpSeach(String cinema_name) {
        HashMap map2 = new HashMap();
        map2.put("userId",userId1);
        map2.put("sessionId", sessionId1);
        map2.put("page", page);
        map2.put("count",count);
        map2.put("cinemaName", cinema_name);
        //请求附近
        getString(Http.CINEMASEARCH_URL, 2, map2);
    }
    //请求成功返回
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                Logger.i("fujinwangluo","我执行了"+data);
//                recommendBean recommendBean = new Gson().fromJson(data, recommendBean.class);
//                List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = recommendBean.getResult().getNearbyCinemaList();
                CinemaSearchBean cinemaSearchBean1 = new Gson().fromJson(data, CinemaSearchBean.class);
                List<CinemaSearchBean.ResultBean> searchlist1 = cinemaSearchBean1.getResult();
                list1.setAdapter(recommendSearchAdapter);
                recommendSearchAdapter.setList(searchlist1);
                break;
            case 1:
                Logger.i("tuijianwangluo","我执行了"+data);
                recommendBean recommendBean1 = new Gson().fromJson(data, recommendBean.class);
                List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList1 = recommendBean1.getResult().getNearbyCinemaList();
                list1.setAdapter(recommendAdapter);
                recommendAdapter.setList(nearbyCinemaList1);
                break;
            case 2:
                CinemaSearchBean cinemaSearchBean = new Gson().fromJson(data, CinemaSearchBean.class);
                List<CinemaSearchBean.ResultBean> searchlist = cinemaSearchBean.getResult();
                if (searchlist.size() > 1) {
                    list1.setAdapter(recommendSearchAdapter);
                    recommendSearchAdapter.setList(searchlist);
                } else {
                    toast(context, "没有找到关于" + cinema_name + "的影院");
//                    list1.setAdapter(recommendAdapter);
                }
                break;
            case 3:
                Logger.i("关注",data);
                DiscussDzBean discussDzBean = new Gson().fromJson(data, DiscussDzBean.class);
                if (discussDzBean.getMessage().equals("关注成功")) {
                    toast(context,"关注成功~");
                }   else if(discussDzBean.getMessage().equals("取消关注成功")){
                    toast(context,"取消关注成功~");
                }else if(discussDzBean.getMessage().equals("请先登录")){
                    toast(context, "请先登录~");
                }else if(discussDzBean.getMessage().equals("请先登录")){
                    toast(context, "取消关注失败~");
                }else if(discussDzBean.getMessage().equals("不能重复关注")){
                    toast(context, "不能重复关注~");
                }
                break;
            case 4:
                Logger.i("取关",data);
                DiscussDzBean discussDzBean1 = new Gson().fromJson(data, DiscussDzBean.class);
                if (discussDzBean1.getMessage().equals("关注成功")) {
                    toast(context,"关注成功~");
                }   else if(discussDzBean1.getMessage().equals("取消关注成功")){
                    toast(context,"取消关注成功~");
                }else if(discussDzBean1.getMessage().equals("请先登录")){
                    toast(context, "请先登录~");
                }else if(discussDzBean1.getMessage().equals("请先登录")){
                    toast(context, "取消关注失败~");
                }else if(discussDzBean1.getMessage().equals("不能重复关注")){
                    toast(context, "不能重复关注~");
                }
                break;
        }
    }
    //找控件的方法
    private void initwidget() {
        recommend = (TextView) getView(R.id.text_cinema_recommend);
        nearby = (TextView) getView(R.id.text_cinema_nearby);
        ss = (TextView) getView(R.id.text_cinema_ss);
        ed = (EditText) getView(R.id.ed_cinema);
        list1 = (XListView) getView(R.id.listview_cinema_list1);
        relay = (RelativeLayout) getView(R.id.relay_cinema_search);
        relay_yes = (RelativeLayout) getView(R.id.relay_cinema_search_yes);
        search = (ImageView) getView(R.id.image_cinema_search);
        setClick(this,R.id.image_cinema_seat,R.id.text_cinema_recommend, R.id.text_cinema_nearby, R.id.relay_cinema_search, R.id.text_cinema_ss);
    }
    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_cinema_recommend:
                dohttp(page);
                isRe=false;
                nearby.setBackgroundResource(R.drawable.square_gray);
                recommend.setBackgroundResource(R.drawable.square_purple);
                recommend.setTextColor(Color.WHITE);
                nearby.setTextColor(Color.BLACK);
                break;
            case R.id.text_cinema_nearby:
                dohttp1(page, longitude, latitude);
                isRe=true;
                nearby.setBackgroundResource(R.drawable.square_purple);
                recommend.setBackgroundResource(R.drawable.square_gray);
                nearby.setTextColor(Color.WHITE);
                recommend.setTextColor(Color.BLACK);
                break;
            case R.id.relay_cinema_search:
                showSearch();//显示搜索框
                break;
            case R.id.text_cinema_ss://点击搜索
                Search();//搜索
                hintSearch();//隐藏搜索框
                break;
            case R.id.image_cinema_seat:
                toast(context,"定位");
//                context.startActivity(new Intent(context, ActivityMap.class));
                break;

        }
    }
    //搜索的方法
    private void Search() {
        cinema_name = ed.getText().toString().trim();
        if (TextUtils.isEmpty(cinema_name)) {
            toast(context, "关键字不能为空~");
            dohttp(page);
            return;
        }
        dohttpSeach(cinema_name);
    }
    //隐藏搜索框
    private void hintSearch() {
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(relay_yes, "translationX", 0, widthPixels);
        objectAnimator.setDuration(400);
        objectAnimator.start();
        //延时关闭1000毫秒
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relay_yes.setVisibility(View.GONE);
                relay.setVisibility(View.VISIBLE);
            }
        }, 500);
    }
    //显示搜索框
    private void showSearch() {
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(relay_yes, "translationX", widthPixels, 0);
        objectAnimator.setDuration(400);
        objectAnimator.start();
        relay_yes.setVisibility(View.VISIBLE);
        relay.setVisibility(View.GONE);
    }
    //获取到的值
    public void setData(String message1, String status1, String sessionId1, String userId1, String headPic1, String nickName1, String phone1, String birthday1, String id1, String lastLoginTime1, String sex1) {
        //this.名称=名称提上去
        this.message1 = message1;
        this.status1 = status1;
        this.sessionId1 = sessionId1;
        this.userId1 = userId1;
        this.headPic1 = headPic1;
        this.nickName1 = nickName1;
        this.phone1 = phone1;
        this.birthday1 = birthday1;
        this.id1 = id1;
        this.lastLoginTime1 = lastLoginTime1;
        this.sex1 = sex1;
        Logger.i("影院",nickName1);
        dohttp(page);
    }
}
