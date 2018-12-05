package com.bw.movie.persenter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaList;
import com.bw.movie.activitys.ActivityFilmDetails;
import com.bw.movie.adapter.CommentAdapter;
import com.bw.movie.adapter.StillAdapter;
import com.bw.movie.adapter.VideoAdapter;
import com.bw.movie.contract.Contract;
import com.bw.movie.entity.BackJson;
import com.bw.movie.entity.CommentEntity;
import com.bw.movie.entity.FilmMessageEntity;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SimpDrawViewUtils;
import com.bw.movie.utils.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import cn.jzvd.Jzvd;

/**
 * 作者:周建峰
 * ActivityFilmDetailsPersenter
 */
public class ActivityFilmDetailsPersenter extends AppDelegate implements View.OnClickListener, Contract.BackFlagListener {

    private static final int FINDMOVIE_CONTENX = 0x129;
    private static final int FOLLOW_MOVIE_CONTENT = 0x1211;
    private static final int CANCEL_MOVIE_CONTENT = 0x1212;
    private static final int FINDALL_COMMENT = 0x130;
    private static final int COMMENTGREAT_CONTENT = 0x1213;
    private Context mContext;
    private SimpleDraweeView smHeart, smbackGround, smFilmImage;
    private TextView title;
    private SimpleDraweeView smFilmPic;
    private TextView txtType, txtCredit, txtTime, txtPlace, txtFilmContent;
    private RelativeLayout layoutDetalis;
    private TextView txtActorName, txtBengName;
    private LinearLayout layout_prevue, layoutStill, layouFilm;
    private RecyclerView listPrevue, listStill;
    private VideoAdapter videoAdapter;
    private StillAdapter stillAdapter;
    private boolean isFollowMovie;
    private int MovieId;
    private int Pwidth = 0;
    private int Pheight = 0;
    private XRecyclerView listComment;
    private CommentAdapter commentAdapter;
    private int movieId;
    private FilmMessageEntity filmMessageEntity;
    private int heightpixels;

    /**
     * 初始化上下文
     *
     * @param context
     */
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
        movieId = intent.getIntExtra("movieId", 0);
        //网络请求
        initFilmMessage(movieId);
        initMovieComment(movieId);
    }

    private void initMovieComment(int movieId) {
        Map<String, String> hmap = new HashMap<>();
        hmap.put("userId", getuserId());
        hmap.put("sessionId", getUserSession());
        Map<String, String> qmap = new HashMap<>();
        qmap.put("movieId", movieId + "");
        qmap.put("page", "1");
        qmap.put("count", "10");
        HeadOrQuertGet(Http.FINDALLMOVIE_COMMENT_URL, FINDALL_COMMENT, hmap, qmap);
    }


    /**
     * 设置页面数据进行网络请求
     *
     * @param movieId
     */
    private void initFilmMessage(int movieId) {
        Map<String, String> hmap = new HashMap<>();
        hmap.put("userId", getuserId());
        hmap.put("sessionId", getUserSession());
        Map<String, String> qmap = new HashMap<>();
        qmap.put("movieId", movieId + "");
        HeadOrQuertGet(Http.FINDMOVIE_URL, FINDMOVIE_CONTENX, hmap, qmap);
    }


    /**
     * 成功
     *
     * @param data
     * @param type
     */
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);

        switch (type) {
            case FINDMOVIE_CONTENX:
                setFilmMessageData(data);
                break;
            case FOLLOW_MOVIE_CONTENT:
                setFollowMovie(data);
                break;
            case CANCEL_MOVIE_CONTENT:
                setCancelMovie(data);
                break;
            case FINDALL_COMMENT:
                setCommentData(data);
                break;
            case COMMENTGREAT_CONTENT:
                setTagreData(data);
                break;
        }
    }

    private void setTagreData(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            toast(mContext, backJson.getMessage());
        }
        initMovieComment(movieId);
    }

    private void setCommentData(String data) {
        CommentEntity commentEntity = new Gson().fromJson(data, CommentEntity.class);
        commentAdapter.setList(commentEntity.getResult());
    }

    private void setFollowMovie(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            smHeart.setImageResource(R.drawable.heart_yes);
            toast(mContext, backJson.getMessage() + "成功");
            isFollowMovie = false;
        } else {
            toast(mContext, backJson.getMessage() + "失败");
        }
    }

    public void setCancelMovie(String data) {
        BackJson backJson = new Gson().fromJson(data, BackJson.class);
        if ("0000".equals(backJson.getStatus())) {
            smHeart.setImageResource(R.drawable.heart_no);
            toast(mContext, backJson.getMessage() + "成功");
            isFollowMovie = true;
        } else {
            toast(mContext, backJson.getMessage() + "失败");
        }
    }

    /**
     * 失败
     *
     * @param msg
     */
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
        DisplayMetrics dm = new DisplayMetrics();
        Pwidth = dm.heightPixels;
        Pheight = dm.widthPixels;

        //评论
        layouFilm = (LinearLayout) getView(R.id.layou_film);
        listComment = (XRecyclerView) getView(R.id.list_comment);
        //剧照
        layoutStill = (LinearLayout) getView(R.id.layout_still);
        listStill = (RecyclerView) getView(R.id.list_still);
        //预告页面的控件
        layout_prevue = (LinearLayout) getView(R.id.layout_prevue);
        listPrevue = (RecyclerView) getView(R.id.list_prevue);
        //详情页面的控件
        txtBengName = (TextView) getView(R.id.txt_btnEng_name);
        txtActorName = (TextView) getView(R.id.txt_actor_name);
        layoutDetalis = (RelativeLayout) getView(R.id.layout_details);
        smFilmPic = (SimpleDraweeView) getView(R.id.sm_film_pic);
        txtType = (TextView) getView(R.id.txt_type);
        txtCredit = (TextView) getView(R.id.txt_credit);
        txtTime = (TextView) getView(R.id.txt_time);
        txtFilmContent = (TextView) getView(R.id.txt_film_content);
        txtPlace = (TextView) getView(R.id.txt_place);
        //主页控件
        smHeart = (SimpleDraweeView) getView(R.id.sm_give);
        smbackGround = (SimpleDraweeView) getView(R.id.sm_background);
        smFilmImage = (SimpleDraweeView) getView(R.id.sm_film_image);
        setClick(this, R.id.back_finsh,
                R.id.btn_details, R.id.btn_prevue,
                R.id.btn_photo, R.id.btn_film,
                R.id.iv_down, R.id.iv_down1,
                R.id.iv_down2, R.id.sm_give, R.id.iv_down3,
                R.id.txt_buy);
        title = (TextView) getView(R.id.txt_title);

        //初始化适配器
        videoAdapter = new VideoAdapter(mContext);
        stillAdapter = new StillAdapter(mContext);
        commentAdapter = new CommentAdapter(mContext);
        commentAdapter.setListener(this);
        //设置适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        listPrevue.setLayoutManager(linearLayoutManager);
        listPrevue.setAdapter(videoAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        listStill.setLayoutManager(staggeredGridLayoutManager);
        listStill.setAdapter(stillAdapter);

        LinearLayoutManager linearCommentManager = new LinearLayoutManager(mContext);
        listComment.setLayoutManager(linearCommentManager);
        listComment.setAdapter(commentAdapter);
    }


    /**
     * 设置
     *
     * @param filmMessageData
     */
    public void setFilmMessageData(String filmMessageData) {
        filmMessageEntity = new Gson().fromJson(filmMessageData, FilmMessageEntity.class);
        if (filmMessageEntity.getResult().isFollowMovie()) {
            smHeart.setImageResource(R.drawable.heart_no);
        } else {
            smHeart.setImageResource(R.drawable.heart_yes);
        }
        SimpDrawViewUtils.showUrlBlur(smbackGround, filmMessageEntity.getResult().getImageUrl(), 3, 5);
        smFilmImage.setImageURI(filmMessageEntity.getResult().getImageUrl());
        title.setText(filmMessageEntity.getResult().getName());
        smFilmPic.setImageURI(filmMessageEntity.getResult().getImageUrl());
        txtType.setText("类型：" + filmMessageEntity.getResult().getMovieTypes());
        txtCredit.setText("导演：" + filmMessageEntity.getResult().getDirector());
        txtTime.setText("时长：" + filmMessageEntity.getResult().getDuration());
        txtPlace.setText("产地：" + filmMessageEntity.getResult().getPlaceOrigin());
        txtFilmContent.setText(filmMessageEntity.getResult().getSummary());
        String[] split = filmMessageEntity.getResult().getStarring().split(",");
        StringBuffer buffer = new StringBuffer();
        for (String name : split) {
            buffer.append(name + "\n\n");
        }
        txtActorName.setText(buffer);
        txtBengName.setText(buffer);
        videoAdapter.setBean(filmMessageEntity);
        //设置数据
        stillAdapter.setList(filmMessageEntity.getResult().getPosterList());
        isFollowMovie = filmMessageEntity.getResult().isFollowMovie();
        if (isFollowMovie) {
            smHeart.setImageResource(R.drawable.heart_no);
        } else {
            smHeart.setImageResource(R.drawable.heart_yes);
        }
        MovieId = filmMessageEntity.getResult().getId();
    }


    /**
     * 设置动画
     *
     * @param view
     * @param in
     * @param to
     */
    public void setAnimation(View view, int in, int to) {
        heightpixels = mContext.getResources().getDisplayMetrics().heightPixels;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", in, to);
        animator.setDuration(500);
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_details:
                setAnimation(layoutDetalis, heightpixels, 550);
                layoutDetalis.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_prevue:
                setAnimation(layout_prevue, heightpixels, 550);
                layout_prevue.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_photo:
                setAnimation(layoutStill, heightpixels, 550);
                layoutStill.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_film:
                setAnimation(layouFilm, heightpixels, 550);
                layouFilm.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_down:
                setAnimation(layoutDetalis, 550, heightpixels);
                break;
            case R.id.iv_down1:
                setAnimation(layout_prevue, 550, heightpixels);
                Jzvd.releaseAllVideos();
                break;
            case R.id.back_finsh:
                ((ActivityFilmDetails) mContext).finish();
                break;
            case R.id.iv_down2:
                setAnimation(layoutStill, 550, heightpixels);
                break;
            case R.id.iv_down3:
                setAnimation(layouFilm, 550, heightpixels);
                break;
            case R.id.sm_give:
                give();
                break;
            case R.id.txt_buy:
                BuyTicket();
                break;
        }
    }

    private void BuyTicket() {
        FilmMessageEntity.ResultBean result = filmMessageEntity.getResult();
        Intent intent = new Intent(mContext, ActivityCinemaList.class);
        intent.putExtra("filmId", movieId);
        intent.putExtra("filmName", filmMessageEntity.getResult().getName());
        intent.putExtra("picUrl", filmMessageEntity.getResult().getImageUrl());
        String content = result.getName() + "," + result.getMovieTypes() + "," + result.getDirector() + "," + result.getDuration() + "," + result.getPlaceOrigin();
        intent.putExtra("content", content);
        ((ActivityFilmDetails) mContext).startActivity(intent);
    }

    /**
     * 关注
     */
    private void give() {
        if (isFollowMovie) {
            Map<String, String> hmap = new HashMap<>();
            hmap.put("userId", getuserId());
            hmap.put("sessionId", getUserSession());
            Map<String, String> qmap = new HashMap<>();
            qmap.put("movieId", MovieId + "");
            HeadOrQuertGet(Http.FOLLOW_MOVIE, FOLLOW_MOVIE_CONTENT, hmap, qmap);
        } else {
            Map<String, String> hmap = new HashMap<>();
            hmap.put("userId", getuserId());
            hmap.put("sessionId", getUserSession());
            Map<String, String> qmap = new HashMap<>();
            qmap.put("movieId", MovieId + "");
            HeadOrQuertGet(Http.CANCEL_MOVIE, CANCEL_MOVIE_CONTENT, hmap, qmap);
        }
    }

    @Override
    public void backFlag(int flag, int commentId) {
        Logger.i("ActivityFilmDetailsPersenter", commentId + "==" + flag);
        if (flag == 0) {
            Map<String, String> hmap = new HashMap<>();
            hmap.put("userId", getuserId());
            hmap.put("sessionId", getUserSession());
            Map<String, String> fmap = new HashMap<>();
            fmap.put("commentId", commentId + "");
            HeadOrFormPost(Http.COMMENTGREAT_URL, COMMENTGREAT_CONTENT, hmap, fmap);
        }
    }
}