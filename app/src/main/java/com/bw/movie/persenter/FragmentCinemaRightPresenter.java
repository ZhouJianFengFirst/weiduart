package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.adapter.DiscussAdapter;
import com.bw.movie.entity.CinemaDetailsBean;
import com.bw.movie.entity.DiscussBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院评论
 */
public class FragmentCinemaRightPresenter extends AppDelegate {

    private Context context;
    private RecyclerView recy;
    private String id;
    private String page = "1";
    private String count = "20";
    private DiscussAdapter discussAdapter;
    private String message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1,cinema_name;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema_right;
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
        discussAdapter = new DiscussAdapter(context);
        recy.setAdapter(discussAdapter);
    }

    //请求评论的接口
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId",userId1);
        map.put("sessionId",sessionId1);
        map.put("cinemaId", id);
        map.put("page", page);
        map.put("count", count);
        getString(Http.CINEMARIGHT_URL, 0, map);
        Logger.i("详情右侧",userId1+"hjk"+sessionId1+"ef"+id);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                DiscussBean discussBean = new Gson().fromJson(data, DiscussBean.class);
                List<DiscussBean.ResultBean> result = discussBean.getResult();
                discussAdapter.setList(result);
                break;
        }
    }

    //找控件的方法
    private void initwidget() {
        recy = (RecyclerView) getView(R.id.right_cinema_recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recy.setLayoutManager(linearLayoutManager);
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
