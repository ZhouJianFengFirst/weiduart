package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBuyTicket;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.activitys.ActivityMap;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.adapter.CinemaFlowAdapter;
import com.bw.movie.adapter.CinemaSessionsAdapter;
import com.bw.movie.entity.CinemaDetailsBean;
import com.bw.movie.entity.CinemaFlowBean;
import com.bw.movie.entity.CinemaSessionBean;
import com.bw.movie.fragments.FragmentCinemaLeft;
import com.bw.movie.fragments.FragmentCinemaRight;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * 作者：gaojiabao
 * 时间：2018/11/28 8:51
 * 作用：影院详情页面
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class ActivityCinemaDetailsPersenter extends AppDelegate implements View.OnClickListener, CinemaSessionsAdapter.BackClickListener {
    private Context context;
    private SimpleDraweeView simp;
    private TextView name, teseat,rescy_text;
    private XRecyclerView rescy;
    private LinearLayout tan, xq, pl,line;
    private FrameLayout fram;
    private View left, right;
    private FragmentManager supportFragmentManager;
    private FragmentCinemaLeft fragmentCinemaLeft;
    private FragmentCinemaRight fragmentCinemaRight;
    private RecyclerCoverFlow flow;
    private CinemaFlowAdapter cinemaFlowAdapter;
    private CinemaSessionsAdapter cinemaSessionsAdapter;
    private ImageView seat;
    private Bitmap head;
    private String message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1,id;
    private List<CinemaFlowBean.ResultBean> flowlist = new ArrayList<>();
    private List<CinemaSessionBean.ResultBean> sessionlist = new ArrayList<>();
    private int flag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_details;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        Intent intent = ((ActivityCinemaDetails) context).getIntent();
        id = intent.getStringExtra("id");
        dohttpFlow();//请求画廊轮播数据
        //实例化fragment
        fragmentCinemaLeft = new FragmentCinemaLeft();
        fragmentCinemaRight = new FragmentCinemaRight();
        //管理fragment
        supportFragmentManager = ((ActivityCinemaDetails) context).getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fram_cinema, fragmentCinemaLeft).commit();
        //影片场次
        cinemaSessionsAdapter = new CinemaSessionsAdapter(context);
        cinemaSessionsAdapter.setListener(this);
        rescy.setAdapter(cinemaSessionsAdapter);
        //设置画廊的适配器
//        flow.setFlatFlow(true); //平面滚动
        cinemaFlowAdapter = new CinemaFlowAdapter(context);
        flow.setAdapter(cinemaFlowAdapter);
        flow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                flag=position;
                putLine(position);
                Logger.d("Tagger", flowlist.get(position).getName());
                int moveid = flowlist.get(position).getId();
                dohttpsession(moveid);
            }
        });
        rescy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rescy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
            }
        });

    }

    //请求影院场次
    private void dohttpsession(int movieId) {
        HashMap map3 = new HashMap();
        map3.put("cinemasId", id);
        map3.put("movieId", movieId);
        SpUtil.saveData(context, "cinemasId", id);
        getString(Http.CINEMASESSION_URL, 2, map3);
        rescy.refreshComplete();
    }

    //请求画廊轮播数据
    private void dohttpFlow() {
        HashMap map = new HashMap();
        map.put("cinemaId", id);
        getString(Http.CINEMAFLOW_URL, 1, map);
    }

    //网络请求影院详情
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        map.put("cinemaId", id);
        getString(Http.CINEMADETAILS_URL, 0, map);
//        Logger.i("网络请求影院详情",userId1+"哈哈"+sessionId1);
    }

    //成功的方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                CinemaDetailsBean cinemaDetailsBean = new Gson().fromJson(data, CinemaDetailsBean.class);
                CinemaDetailsBean.ResultBean derail = cinemaDetailsBean.getResult();
                simp.setImageURI(Uri.parse(derail.getLogo()));
                name.setText(derail.getName());
                teseat.setText(derail.getAddress());
                SpUtil.saveData(context, "cinemaname", derail.getName());
                SpUtil.saveData(context, "cinemaaddress", derail.getAddress());
                break;
            case 1:
                CinemaFlowBean cinemaFlowBean = new Gson().fromJson(data, CinemaFlowBean.class);
                flowlist = cinemaFlowBean.getResult();
                cinemaFlowAdapter.setList(flowlist);
                dohttpsession(flowlist.get(0).getId());//请求场次
                break;
            case 2:
//                Logger.i("影片场次", data);
                CinemaSessionBean cinemaSessionBean = new Gson().fromJson(data, CinemaSessionBean.class);
                sessionlist = cinemaSessionBean.getResult();
                if (sessionlist.size() > 1) {
                    rescy_text.setVisibility(View.GONE);
                    rescy.setVisibility(View.VISIBLE);
                    cinemaSessionsAdapter.setList(sessionlist);
                } else {
                    rescy_text.setVisibility(View.VISIBLE);
                    rescy.setVisibility(View.GONE);
                }
                break;
        }
    }

    //找id的方法
    private void initwidget() {
        simp = (SimpleDraweeView) getView(R.id.simp_cinemadetails_simp);
        seat = (ImageView) getView(R.id.image_cinemadetails_seat);
        name = (TextView) getView(R.id.text_cinemadetails_name);
        teseat = (TextView) getView(R.id.text_cinemadetails_seat);
        rescy_text = (TextView) getView(R.id.text_cinema_rescy);
        rescy = (XRecyclerView) getView(R.id.recyview_cinema_rescy);
        tan = (LinearLayout) getView(R.id.lin_cinema_tan);
        xq = (LinearLayout) getView(R.id.text_cinemadetails_xq);
        pl = (LinearLayout) getView(R.id.text_cinemadetails_pl);
        fram = (FrameLayout) getView(R.id.fram_cinema);
        left = (View) getView(R.id.view_cinemadetails_left);
        right = (View) getView(R.id.view_cinemadetails_right);
        flow = (RecyclerCoverFlow) getView(R.id.rcf_cinema_flow);
        line = (LinearLayout) getView(R.id.layout_cinema_line);
        setClick(this, R.id.image_cinemadetails_seat, R.id.image_cinemadetails_left, R.id.text_cinemadetails_name, R.id.text_cinemadetails_seat, R.id.simp_cinemadetails_simp, R.id.image_cinema_down, R.id.text_cinemadetails_pl, R.id.text_cinemadetails_xq);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rescy.setLayoutManager(linearLayoutManager);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_cinemadetails_seat://定位
                context.startActivity(new Intent(context, ActivityMap.class));
                break;
            case R.id.image_cinemadetails_left://返回
                ((ActivityCinemaDetails) context).finish();
                break;
            case R.id.text_cinemadetails_name://详情
                showDetail();
                break;
            case R.id.text_cinemadetails_seat://详情
                showDetail();
                break;
            case R.id.simp_cinemadetails_simp://详情
                showDetail();
                break;
            case R.id.image_cinema_down://关闭弹出
                hintDetail();
                break;
            case R.id.text_cinemadetails_xq://影院详情
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.GONE);
                supportFragmentManager.beginTransaction().replace(R.id.fram_cinema, fragmentCinemaLeft).commit();
                break;
            case R.id.text_cinemadetails_pl://影院评论
                left.setVisibility(View.GONE);
                right.setVisibility(View.VISIBLE);
                supportFragmentManager.beginTransaction().replace(R.id.fram_cinema, fragmentCinemaRight).commit();
                break;
        }
    }

    //隐藏弹框
    private void hintDetail() {
        //计算高度
        int hight = context.getResources().getDisplayMetrics().heightPixels;
        //向下的动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tan, "translationY", 550, hight);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        //延时关闭1000毫秒
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tan.setVisibility(View.GONE);
            }
        }, 500);
    }

    //弹出详情
    private void showDetail() {
        int height = context.getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tan, "translationY", height, 550);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        tan.setVisibility(View.VISIBLE);
    }

    //进度条
    public void putLine(int page) {
        line.removeAllViews();
        for (int i = 0; i < flowlist.size(); i++) {
            View view = new View(context);
            if (page == i) {
                view.setBackgroundResource(R.drawable.purplechange);
            } else {
                view.setBackgroundResource(R.drawable.square_gray);
            }
            line.addView(view);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.weight = 3;
            params.height = 3;
            view.setLayoutParams(params);
        }
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
        dohttp();//请求影院详情
    }

    //接口回调
    @Override
    public void back(int postion) {
        Intent intent = new Intent(context, ActivityBuyTicket.class);
        intent.putExtra("ccid", sessionlist.get(postion).getId()+"");
        intent.putExtra("ccbegintime", sessionlist.get(postion).getBeginTime());
        intent.putExtra("ccendtime", sessionlist.get(postion).getEndTime());
        intent.putExtra("cctime", sessionlist.get(postion).getDuration());
        intent.putExtra("ccname", sessionlist.get(postion).getScreeningHall());
        intent.putExtra("seatsTotal", sessionlist.get(postion).getSeatsTotal());
        intent.putExtra("seatsUseCount", sessionlist.get(postion).getSeatsUseCount());
        intent.putExtra("status", sessionlist.get(postion).getStatus());
        SpUtil.saveData(context, "movename", flowlist.get(flag).getName());
        SpUtil.saveData(context, "movieId", flowlist.get(flag).getId());
        context.startActivity(intent);
    }
}
