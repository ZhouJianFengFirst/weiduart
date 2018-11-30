package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.adapter.RecommendAdapter;
import com.bw.movie.entity.CinemaDetailsBean;
import com.bw.movie.entity.recommendBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Logger;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院详情
 */
public class FragmentCinemaLeftPresenter extends AppDelegate {

    private Context context;
    private String cinemaurl = "/movieApi/cinema/v1/findCinemaInfo";
    private String id;
    private TextView te1,te2,te3;
    private String message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1,cinema_name;
    private int count=20;
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema_left;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = ((ActivityCinemaDetails) context).getIntent();
        id = intent.getStringExtra("id");
        initwidget();
    }

    //找控件的方法
    private void initwidget() {
          te1 = (TextView)getView(R.id.text_left_te1);
          te2 = (TextView)getView(R.id.text_left_te2);
          te3 = (TextView)getView(R.id.text_left_te3);
    }

    //网络请求影院详情
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId",userId1);
        map.put("sessionId",sessionId1);
        map.put("cinemaId", id);
        getString(cinemaurl, 0, map);
        Logger.i("详情左侧",userId1+"hjk"+sessionId1+"ef"+id);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                Logger.i("影院详情", data);
                CinemaDetailsBean cinemaDetailsBean = new Gson().fromJson(data, CinemaDetailsBean.class);
                CinemaDetailsBean.ResultBean derail = cinemaDetailsBean.getResult();
               te1.setText(derail.getAddress());
               te2.setText(derail.getPhone());
               te3.setText(derail.getVehicleRoute());
                break;
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
        Logger.i("影院",nickName1);
        dohttp();

    }


}
