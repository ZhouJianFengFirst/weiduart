package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBuyTicket;
import com.bw.movie.activitys.ActivityCinemaDetaolsList;
import com.bw.movie.adapter.CinemaSessionsAdapter;
import com.bw.movie.entity.CinemaSessionBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：周建峰
 * ActivityCinemaDetaolsListPersenter
 */
public class ActivityCinemaDetaolsListPersenter extends AppDelegate implements CinemaSessionsAdapter.BackClickListener {

    private static final int CINEMAFLOW_CONTENT = 0x1208;
    private Context mContext;
    private TextView txtCinemaName, txtCinemaAddress, txtType, txtCredit, txtTime, txtPlace, txtTitle;
    private RecyclerView listview;
    private int movieId;
    private String cinemaflow;
    private int filmId;
    private CinemaSessionsAdapter cinemasessAdapter;
    private String picUrl;
    private String movieName;
    private String movieAddress;
    private TextView txtContnt;
    private String[] split;
    private CinemaSessionBean sessionBean;

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mContext = context;
    }

    //intent.putExtra("movieName", movieName);
//        intent.putExtra("movieAddress", movieAddress);
    @Override
    public void initData() {
        super.initData();
        Intent intent = ((ActivityCinemaDetaolsList) mContext).getIntent();
        movieId = intent.getIntExtra("movieId", 0);
        filmId = intent.getIntExtra("filmId", 0);
        picUrl = intent.getStringExtra("picUrl");
        movieName = intent.getStringExtra("movieName");
        movieAddress = intent.getStringExtra("movieAddress");
        String txtContent = intent.getStringExtra("content");
        split = txtContent.split(",");
        initWeight();
        //网络请求
        getCinemaData(movieId, filmId);
    }

    private void getCinemaData(int movieId, int filmId) {
        Map<String, String> map = new HashMap<>();
        map.put("cinemasId", filmId + "");
        map.put("movieId", movieId + "");
        getString(Http.CINEMASESSION_URL, CINEMAFLOW_CONTENT, map);
    }

    private void initWeight() {
        //初始化数据
        txtCinemaName = (TextView) getView(R.id.txt_cinema_name);
        txtCinemaAddress = (TextView) getView(R.id.txt_address);
        listview = (RecyclerView) getView(R.id.show_cinema_list);
        SimpleDraweeView smCinemaimage = (SimpleDraweeView) getView(R.id.sm_cinema_image);
        txtTitle = (TextView) getView(R.id.txt_title);
        txtType = (TextView) getView(R.id.txt_type);
        txtCredit = (TextView) getView(R.id.txt_credit);
        txtTime = (TextView) getView(R.id.txt_time);
        txtPlace = (TextView) getView(R.id.txt_place);

        smCinemaimage.setImageURI(picUrl);
        txtCinemaName.setText(movieName);
        txtCinemaAddress.setText(movieAddress);
        txtTitle.setText(split[0]);
        txtType.setText("类型：" + split[1]);
        txtCredit.setText("导演：" + split[2]);
        txtTime.setText("时长：" + split[3]);
        txtPlace.setText("产地：" + split[4]);

        //初始化Adapter
        cinemasessAdapter = new CinemaSessionsAdapter(mContext);
        cinemasessAdapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        listview.setLayoutManager(layoutManager);
        listview.setAdapter(cinemasessAdapter);
    }


    /**
     * 获取userid
     *
     * @return
     */
    public String getUserId() {
        String userId = (String) SpUtil.getSpData(mContext, "userId", "");
        return userId;
    }

    /**
     * 获取Session
     *
     * @return
     */
    public String getUserSession() {
        String sessionId = (String) SpUtil.getSpData(mContext, "sessionId", "");
        return sessionId;
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case CINEMAFLOW_CONTENT:
                setCinemaflow(data);
                break;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema_list;
    }

    public void setCinemaflow(String data) {
        sessionBean = new Gson().fromJson(data, CinemaSessionBean.class);
        cinemasessAdapter.setList(sessionBean.getResult());
    }

    @Override
    public void back(int postion) {
        Intent intent = new Intent(mContext, ActivityBuyTicket.class);
        intent.putExtra("ccid", sessionBean.getResult().get(postion).getId()+"");
        intent.putExtra("ccbegintime", sessionBean.getResult().get(postion).getBeginTime());
        intent.putExtra("ccendtime", sessionBean.getResult().get(postion).getEndTime());
        intent.putExtra("cctime", sessionBean.getResult().get(postion).getDuration());
        intent.putExtra("ccname", sessionBean.getResult().get(postion).getScreeningHall());
        intent.putExtra("seatsTotal", sessionBean.getResult().get(postion).getSeatsTotal());
        intent.putExtra("seatsUseCount", sessionBean.getResult().get(postion).getSeatsUseCount());
        intent.putExtra("status", sessionBean.getResult().get(postion).getStatus());
        SpUtil.saveData(mContext, "cinemaname", movieName);
        SpUtil.saveData(mContext, "cinemaaddress", movieAddress);
        SpUtil.saveData(mContext, "movename", split[0]);
        mContext.startActivity(intent);
    }
}