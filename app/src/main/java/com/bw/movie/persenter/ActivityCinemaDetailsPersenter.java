package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.entity.CinemaDetailsBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Logger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 作者：gaojiabao
 * 时间：2018/11/28 8:51
 * 作用：影院详情页面
 */
public class ActivityCinemaDetailsPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SimpleDraweeView simp;
    private TextView name, teseat;
    private RecyclerView rescy;
    private String cinemaurl = "/movieApi/cinema/v1/findCinemaInfo";
    private String id;
    private LinearLayout tan, xq, pl;
    private FrameLayout fram;
    private View left, right;
    private FragmentManager supportFragmentManager;

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
//        toast(context, "影院id" + id);
        dohttp();//请求影院详情
        //实例化fragment

        //管理fragment
        supportFragmentManager = ((ActivityCinemaDetails) context).getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.fram_cinema,null).commit();
    }

    //网络请求影院详情
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId", "18");
        map.put("sessionId", "15320748258726");
        map.put("cinemaId", id);
        getString(cinemaurl, 0, map);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                Logger.i("影院详情", data);
                CinemaDetailsBean cinemaDetailsBean = new Gson().fromJson(data, CinemaDetailsBean.class);
                CinemaDetailsBean.ResultBean derail = cinemaDetailsBean.getResult();
                simp.setImageURI(Uri.parse(derail.getLogo()));
                name.setText(derail.getName());
                teseat.setText(derail.getAddress());
                break;
        }
    }

    //找id的方法
    private void initwidget() {
        simp = (SimpleDraweeView) getView(R.id.simp_cinemadetails_simp);
        name = (TextView) getView(R.id.text_cinemadetails_name);
        teseat = (TextView) getView(R.id.text_cinemadetails_seat);
        rescy = (RecyclerView) getView(R.id.recyview_cinema_rescy);
        tan = (LinearLayout) getView(R.id.lin_cinema_tan);
        xq = (LinearLayout) getView(R.id.text_cinemadetails_xq);
        pl = (LinearLayout) getView(R.id.text_cinemadetails_pl);
        fram = (FrameLayout) getView(R.id.fram_cinema);
        left = (View) getView(R.id.view_cinemadetails_left);
        right = (View) getView(R.id.view_cinemadetails_right);
        setClick(this, R.id.image_cinemadetails_seat, R.id.image_cinemadetails_left, R.id.text_cinemadetails_name, R.id.text_cinemadetails_seat, R.id.simp_cinemadetails_simp, R.id.image_cinema_down);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_cinemadetails_seat://定位
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
                supportFragmentManager.beginTransaction().replace(R.id.fram_cinema,null).commit();
                break;
            case R.id.text_cinemadetails_pl://影院评论
                supportFragmentManager.beginTransaction().replace(R.id.fram_cinema,null).commit();
                break;

        }
    }

    //隐藏弹框
    private void hintDetail() {
        //计算高度
        int hight = context.getResources().getDisplayMetrics().heightPixels;
        //向下的动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tan, "translationY", 300, hight);
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
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tan, "translationY", height, 300);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        tan.setVisibility(View.VISIBLE);
    }
}
