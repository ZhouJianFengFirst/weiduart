package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityMap;
import com.bw.movie.adapter.CityAdapter;
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
    private String message1, status1, sessionId1, userId1, headPic1, nickName1, phone1, birthday1, id1, lastLoginTime1, sex1, cinema_name;
    private int count = 20;
    private int page = 1;
    private boolean isRe = false;
    private Spinner cinema_spinner;
    private String str;

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
        //初始化控件
        initwidget();
        //获取城市数据
        initCityData();
        dohttp(page);
        //实例化适配器
        recommendAdapter = new RecommendAdapter(context);
        recommendSearchAdapter = new RecommendSearchAdapter(context);
        //设置上拉下拉    list1.setPullLoadEnable(true);
        list1.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if (isRe) {
                    dohttp1(page, longitude, latitude);
                } else if (!isRe) {
                    dohttp(page);
                }
            }

            @Override
            public void onLoadMore() {
                page++;
                if (isRe) {
                    dohttp1(page, longitude, latitude);
                } else if (!isRe) {
                    dohttp(page);
                }
//                toast(context,"没有更多了");
            }
        });
        //设置关注的回调
        doAdapterListener();
    }

    //城市数据下拉框
    private void initCityData() {
        str = (String) cinema_spinner.getSelectedItem();
        cinema_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                str = (String) cinema_spinner.getSelectedItem();
                //把该值传给 TextView
                Logger.i("选择框", str);
                if(str.equals("天津")){
                    longitude="117.20";
                    latitude="39.12";
                }else if(str.equals("上海")){
                    longitude="121.47";
                    latitude="31.23";
                }else if(str.equals("榆次")){
                    longitude="112.75";
                    latitude="37.68";
                }else if(str.equals("衡水")){
                    longitude="115.68";
                    latitude="37.73";
                }else if(str.equals("太原")){
                    longitude="112.55";
                    latitude="37.87";
                }else if(str.equals("郑州")){
                    longitude="113.62";
                    latitude="34.75";
                }else if(str.equals("石家庄")){
                    longitude="114.52";
                    latitude="38.05";
                }else if(str.equals("深圳")){
                    longitude="114.05";
                    latitude="22.55";
                }else if(str.equals("成都")){
                    longitude="104.07";
                    latitude="30.67";
                }else if(str.equals("广州")){
                    longitude="113.27";
                    latitude="23.13";
                }else if(str.equals("北京")){
                   return;
                }
                //改完后切换数据
                dohttp1(page, longitude, latitude);
                toast(context,"已切换到"+str+"经纬度"+longitude+"=="+latitude);
                isRe = true;
                nearby.setBackgroundResource(R.drawable.square_purple);
                recommend.setBackgroundResource(R.drawable.square_gray);
                nearby.setTextColor(Color.WHITE);
                recommend.setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    //获取经纬度
    private void getlongitude() {
        Location location = LocationUtils.getInstance(context).showLocation();
        if (location != null) {
            longitude = String.valueOf(location.getLatitude());
            latitude = String.valueOf(location.getLongitude());
            Log.i("FLY.LocationUtils", "纬度" + latitude + "经度" + longitude);
        }
    }

    //设置关注的回调
    private void doAdapterListener() {
        //影院关注的回调
        recommendAdapter.result(new RecommendAdapter.SetOnHeart() {
            @Override
            public void success(List<recommendBean.ResultBean.NearbyCinemaListBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId", userId1);
                hmap.put("sessionId", sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId", i);
                HeadOrQuertGet(Http.CINEMAHEART_URL, 3, hmap, qmap);
            }
        });
        //搜索关注的回调
        recommendSearchAdapter.result(new RecommendSearchAdapter.SetOnHeart() {
            @Override
            public void success(List<CinemaSearchBean.ResultBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId", userId1);
                hmap.put("sessionId", sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId", i);
                HeadOrQuertGet(Http.CINEMAHEART_NO_URL, 3, hmap, qmap);
            }
        });
        //影院取关
        recommendAdapter.resultQg(new RecommendAdapter.SetOnHeartQg() {
            @Override
            public void success(List<recommendBean.ResultBean.NearbyCinemaListBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId", userId1);
                hmap.put("sessionId", sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId", i);
                HeadOrQuertGet(Http.CINEMAHEART_NO_URL, 4, hmap, qmap);
            }
        });
        //搜索取关
        recommendSearchAdapter.resultQg(new RecommendSearchAdapter.SetOnHeartQg() {
            @Override
            public void success(List<CinemaSearchBean.ResultBean> list, int i) {
                HashMap hmap = new HashMap();
                hmap.put("userId", userId1);
                hmap.put("sessionId", sessionId1);
                HashMap qmap = new HashMap();
                qmap.put("cinemaId", i);
                HeadOrQuertGet(Http.CINEMAHEART_URL, 4, hmap, qmap);
            }
        });

    }

    //请求推荐网络
    private void dohttp(int page) {
        if (userId1 == null) {
            userId1 = "18";
        }
        if (sessionId1 == null) {
            sessionId1 = "15320748258726";
        }
        HashMap map = new HashMap();
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        map.put("page", page);
        map.put("count", count);
        //请求推荐
        getString(Http.CINEMAALL_URL, 0, map);
        list1.stopRefresh();
//        list1.stopLoadMore();
    }

    //请求附近网络
    private void dohttp1(int page, String longitude, String latitude) {
        if (userId1 == null) {
            userId1 = "18";
        }
        if (sessionId1 == null) {
            sessionId1 = "15320748258726";
        }
        HashMap map1 = new HashMap();
        map1.put("userId", userId1);
        map1.put("sessionId", sessionId1);
        map1.put("page", page);
        map1.put("count", count);
        map1.put("longitude", longitude);
        map1.put("latitude", latitude);
        //请求附近
        getString(Http.CINEMARE_URL, 1, map1);
        list1.stopRefresh();
        Logger.i("网络里的经纬度", longitude + "经度" + latitude);
    }

    //请求搜索
    private void dohttpSeach(String cinema_name) {
        HashMap map2 = new HashMap();
        map2.put("userId", userId1);
        map2.put("sessionId", sessionId1);
        map2.put("page", page);
        map2.put("count", count);
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
                Logger.i("fujinwangluo", "我执行了" + data);
//                recommendBean recommendBean = new Gson().fromJson(data, recommendBean.class);
//                List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = recommendBean.getResult().getNearbyCinemaList();
                CinemaSearchBean cinemaSearchBean1 = new Gson().fromJson(data, CinemaSearchBean.class);
                List<CinemaSearchBean.ResultBean> searchlist1 = cinemaSearchBean1.getResult();
                list1.setAdapter(recommendSearchAdapter);
                recommendSearchAdapter.setList(searchlist1);
                break;
            case 1:
                Logger.i("tuijianwangluo", "我执行了" + data);
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
                Logger.i("关注", data);
                DiscussDzBean discussDzBean = new Gson().fromJson(data, DiscussDzBean.class);
                if (discussDzBean.getMessage().equals("关注成功")) {
                    toast(context, "关注成功~");
                } else if (discussDzBean.getMessage().equals("取消关注成功")) {
                    toast(context, "取消关注成功~");
                } else if (discussDzBean.getMessage().equals("请先登录")) {
                    toast(context, "请先登录~");
                } else if (discussDzBean.getMessage().equals("请先登录")) {
                    toast(context, "取消关注失败~");
                } else if (discussDzBean.getMessage().equals("不能重复关注")) {
                    toast(context, "不能重复关注~");
                }
                break;
            case 4:
                Logger.i("取关", data);
                DiscussDzBean discussDzBean1 = new Gson().fromJson(data, DiscussDzBean.class);
                if (discussDzBean1.getMessage().equals("关注成功")) {
                    toast(context, "关注成功~");
                } else if (discussDzBean1.getMessage().equals("取消关注成功")) {
                    toast(context, "取消关注成功~");
                } else if (discussDzBean1.getMessage().equals("请先登录")) {
                    toast(context, "请先登录~");
                } else if (discussDzBean1.getMessage().equals("请先登录")) {
                    toast(context, "取消关注失败~");
                } else if (discussDzBean1.getMessage().equals("不能重复关注")) {
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
        cinema_spinner = (Spinner) getView(R.id.cinema_spinner);
//        getView(R.id.)
        setClick(this, R.id.image_cinema_seat, R.id.text_cinema_recommend, R.id.text_cinema_nearby, R.id.relay_cinema_search, R.id.text_cinema_ss);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_cinema_recommend:
                dohttp(page);
                isRe = false;
                nearby.setBackgroundResource(R.drawable.square_gray);
                recommend.setBackgroundResource(R.drawable.square_purple);
                recommend.setTextColor(Color.WHITE);
                nearby.setTextColor(Color.BLACK);
                break;
            case R.id.text_cinema_nearby:
                dohttp1(page, longitude, latitude);
                isRe = true;
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
        Logger.i("影院", nickName1);
        dohttp(page);
    }

}
