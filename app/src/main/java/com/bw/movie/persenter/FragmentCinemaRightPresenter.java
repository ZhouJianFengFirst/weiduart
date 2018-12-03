package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.adapter.DiscussAdapter;
import com.bw.movie.entity.CinemaDetailsBean;
import com.bw.movie.entity.DiscussBean;
import com.bw.movie.entity.DiscussDzBean;
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
public class FragmentCinemaRightPresenter extends AppDelegate implements View.OnClickListener {

    private Context context;
    private RecyclerView recy;
    private String id;
    private String page = "1";
    private String count = "20";
    private DiscussAdapter discussAdapter;
    private String message1, status1, sessionId1, userId1, headPic1, nickName1, phone1, birthday1, id1, lastLoginTime1, sex1, cinema_name;
    private ImageView write;
    private LinearLayout lin_pl;
    private TextView te_pl;
    private EditText ed_pl;
//    private static int TYPE = 0;//判断是否点赞成功

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
        //适配器点击事件回调
        discussAdapter.ruselt(new DiscussAdapter.SetOnItem() {
            @Override
            public void success(DiscussBean.ResultBean resultBean, int dzId) {
                HashMap map = new HashMap();
                map.put("userId", userId1);
                map.put("sessionId", sessionId1);
                HashMap fmap = new HashMap();
                fmap.put("commentId", dzId);
                handPostString(Http.CINEMARIGHT_DZ_URL, 1, map, fmap);
            }
        });

    }

    //请求评论的接口
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        map.put("cinemaId", id);
        map.put("page", page);
        map.put("count", count);
        getString(Http.CINEMARIGHT_URL, 0, map);
        Logger.i("详情右侧", userId1 + "hjk" + sessionId1 + "ef" + id);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                Logger.i("平路列表", data);
                DiscussBean discussBean = new Gson().fromJson(data, DiscussBean.class);
                List<DiscussBean.ResultBean> result = discussBean.getResult();
                discussAdapter.setList(result);
                break;
            case 1:
                Logger.i("评论点赞data", data);
                DiscussDzBean discussDzBean = new Gson().fromJson(data, DiscussDzBean.class);
                if (discussDzBean.getMessage().equals("点赞成功")) {
                    Logger.i("评论点赞", discussDzBean.getMessage());
                } else if (discussDzBean.getMessage().equals("不能重复点赞")) {
                    toast(context, "您已经点过赞了~");
                } else if (discussDzBean.getMessage().equals("请先登录")) {
                    toast(context, "请先登录");
                }
                break;
            case 2:
                Logger.i("发表评论", data);
                DiscussDzBean discussDzBean1 = new Gson().fromJson(data, DiscussDzBean.class);
                if(discussDzBean1.getMessage().equals("请先登陆")){
                    toast(context,"登录信息已过期~");
                    return;
                }else if(discussDzBean1.getMessage().equals("评论成功")){
                    toast(context,"评论成功~");
                    dohttp();
                    lin_pl.setVisibility(View.GONE);
                    write.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.i("请求失败","原因"+msg);
    }

    //找控件的方法
    private void initwidget() {
        recy = (RecyclerView) getView(R.id.right_cinema_recy);
        write = (ImageView) getView(R.id.image_cinemadetails_write);
        lin_pl = (LinearLayout) getView(R.id.cinema_lin_pl);
        te_pl = (TextView) getView(R.id.cinema_te_pl);
        ed_pl = (EditText) getView(R.id.cinema_ed_pl);
        setClick(this, R.id.image_cinemadetails_write, R.id.cinema_te_pl);
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
        Logger.i("影院", nickName1);
        dohttp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_cinemadetails_write:
                lin_pl.setVisibility(View.VISIBLE);
                write.setVisibility(View.GONE);
                break;
            case R.id.cinema_te_pl:
                String s = ed_pl.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    toast(context, "评论内容不能为空~");
                    lin_pl.setVisibility(View.GONE);
                    write.setVisibility(View.VISIBLE);
                    return;
                }
                HashMap hmap = new HashMap();
                hmap.put("userId", userId1);
                hmap.put("sessionId", sessionId1);
                HashMap fmap = new HashMap();
                fmap.put("cinemaId",id);
                fmap.put("commentContent",s);
                handPostString(Http.CINEMARIGHT_WRITE_URL,2,hmap,fmap);
                break;
        }
    }
}
