package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityFilmDetails;
import com.bw.movie.entity.FilmMessageEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SimpDrawViewUtils;
import com.bw.movie.utils.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:周建峰
 * ActivityFilmDetailsPersenter
 */
public class ActivityFilmDetailsPersenter extends AppDelegate {

    private static final int FINDMOVIE_CONTENX = 0x129;
    private Context mContext;
    private SimpleDraweeView smHeart, smbackGround, smFilmImage;
    private TextView title;

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_film_details;
    }

    @Override
    public void initData() {
        super.initData();
        initWeight();
        Intent intent = ((ActivityFilmDetails) mContext).getIntent();
        int movieId = intent.getIntExtra("movieId", 0);
        //网络请求
        initUserMessage(movieId);

    }

    private void initUserMessage(int movieId) {
        Map<String, String> hmap = new HashMap<>();
        hmap.put("userId", getuserId());
        hmap.put("sessionId", getUserSession());
        Map<String, String> qmap = new HashMap<>();
        qmap.put("movieId", movieId + "");
        HeadOrQuertGet(Http.FINDMOVIE_URL, FINDMOVIE_CONTENX, hmap, qmap);
    }


    @Override
    public void successString(String data, int type) {
        super.successString(data, type);

        switch (type) {
            case FINDMOVIE_CONTENX:
                setFilmMessageData(data);
                break;
        }

    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.d("Tagger", "错误");
    }

    /**
     * 获取userid
     *
     * @return
     */
    public String getuserId() {
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


    /**
     * 初始化
     */
    private void initWeight() {
        smHeart = (SimpleDraweeView) getView(R.id.sm_give);
        smHeart.setImageResource(R.drawable.heart_no);
        smbackGround = (SimpleDraweeView) getView(R.id.sm_background);
        smFilmImage = (SimpleDraweeView) getView(R.id.sm_film_image);
        title = (TextView) getView(R.id.txt_title);
    }

    public void setFilmMessageData(String filmMessageData) {
        FilmMessageEntity filmMessageEntity = new Gson().fromJson(filmMessageData, FilmMessageEntity.class);
        if (filmMessageEntity.getResult().isFollowMovie()) {
            smHeart.setImageResource(R.drawable.heart_no);
        } else {
            smHeart.setImageResource(R.drawable.heart_yes);
        }

        SimpDrawViewUtils.showUrlBlur(smbackGround, filmMessageEntity.getResult().getImageUrl(), 3, 5);
        smFilmImage.setImageURI(filmMessageEntity.getResult().getImageUrl());
        title.setText(filmMessageEntity.getResult().getName());
    }
}
