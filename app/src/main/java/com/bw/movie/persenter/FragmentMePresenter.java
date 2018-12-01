package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityAttention;
import com.bw.movie.activitys.ActivityHistory;
import com.bw.movie.activitys.ActivityInform;
import com.bw.movie.activitys.ActivityLogin;
import com.bw.movie.activitys.ActivityMessage;
import com.bw.movie.activitys.ActivityOpinion;
import com.bw.movie.entity.NewestBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：FragmentMePersenter(我的页面)
 */

public class FragmentMePresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SimpleDraweeView me_sdv_head;
    private SimpleDraweeView me_sdv_inform;
    private TextView me_tv_nickname;
    private LinearLayout me_liner_message;
    private LinearLayout me_Liner_attention;
    private LinearLayout me_Liner_history;
    private LinearLayout me_Liner_opinion;
    private LinearLayout me_Liner_newest;
    private String message1;
    private String status1;
    private String sessionId1;
    private String userId1;
    private String headPic1;
    private String nickName1;
    private String phone1;
    private String birthday1;
    private String id1;
    private String lastLoginTime1;
    private String sex1;
    private String ak="0110010010000";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    //重写初始化上下文方法
    @Override
    public void initContext(Context context) {
        //删了super这行提上去上下文
        this.context = context;
    }

    //重写初始化数据方法
    @Override
    public void initData() {
        super.initData();
        //初始化数据方法
        initwidget();
    }

    //初始化数据方法
    private void initwidget() {
        //获取控件强转提上去
        me_sdv_head = (SimpleDraweeView) getView(R.id.me_sdv_head);
        me_sdv_inform = (SimpleDraweeView) getView(R.id.me_sdv_inform);
        me_tv_nickname = (TextView) getView(R.id.me_tv_nickname);
        me_liner_message = (LinearLayout) getView(R.id.me_liner_message);
        me_Liner_attention = (LinearLayout) getView(R.id.me_Liner_attention);
        me_Liner_history = (LinearLayout) getView(R.id.me_Liner_history);
        me_Liner_opinion = (LinearLayout) getView(R.id.me_Liner_opinion);
        me_Liner_newest = (LinearLayout) getView(R.id.me_Liner_newest);
        //点击事件
        me_sdv_head.setOnClickListener(this);
        me_sdv_inform.setOnClickListener(this);
        me_liner_message.setOnClickListener(this);
        me_Liner_attention.setOnClickListener(this);
        me_Liner_history.setOnClickListener(this);
        me_Liner_opinion.setOnClickListener(this);
        me_Liner_newest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.me_sdv_head:
                //吐司这是头像
//                Toast.makeText(context,"这是头像",Toast.LENGTH_SHORT).show();
                //获取登录状态islogin
                Boolean islogin = (Boolean) SpUtil.getSpData(context, "isLogin", false);
                //判断islogin
                if (islogin) {
//                    //吐司已经登录
//                    toast(context, "已登录");
                    //跳转信息
                    context.startActivity(new Intent(context, ActivityMessage.class));
                } else {
                    //吐司已经登录
                    toast(context, "请先登录");
                    //跳转登录
                    context.startActivity(new Intent(context, ActivityLogin.class));
                }
                break;
            case R.id.me_sdv_inform:
                //吐司这是通知
//                Toast.makeText(context,"这是通知",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
//                context.startActivity(new Intent(context, ActivityInform.class));
                //获取登录状态islogin
                Boolean islogin2 = (Boolean) SpUtil.getSpData(context, "isLogin", false);
                //判断islogin
                if (islogin2) {
//                    //吐司已经登录
//                    toast(context, "已登录");
                    //跳转信息
                    context.startActivity(new Intent(context, ActivityInform.class));
                } else {
                    //吐司已经登录
                    toast(context, "请先登录");
                    //跳转登录
                    context.startActivity(new Intent(context, ActivityLogin.class));
                }
                break;
            case R.id.me_liner_message:
                //吐司这是我的信息
//                Toast.makeText(context,"这是我的信息",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
                //获取登录状态islogin
                Boolean islogin1 = (Boolean) SpUtil.getSpData(context, "isLogin", false);
                //判断islogin
                if (islogin1) {
//                    //吐司已经登录
//                    toast(context, "已登录");
                    //跳转信息
                    context.startActivity(new Intent(context, ActivityMessage.class));
                } else {
                    //吐司已经登录
                    toast(context, "请先登录");
                    //跳转登录
                    context.startActivity(new Intent(context, ActivityLogin.class));
                }
                break;
            case R.id.me_Liner_attention:
                //吐司这是我的关注
//                Toast.makeText(context,"这是我的关注",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
                context.startActivity(new Intent(context, ActivityAttention.class));
                break;
            case R.id.me_Liner_history:
                //吐司这是我的购票记录
//                Toast.makeText(context,"这是我的购票记录",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
                context.startActivity(new Intent(context, ActivityHistory.class));
                break;
            case R.id.me_Liner_opinion:
                //吐司这是我的意见反馈
//                Toast.makeText(context,"这是我的意见反馈",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
                context.startActivity(new Intent(context, ActivityOpinion.class));
                break;
            case R.id.me_Liner_newest:
                //吐司这是我的最新版本
//                Toast.makeText(context,"这是我的最新版本",Toast.LENGTH_SHORT).show();
                //跳转页面上下文
//                context.startActivity(new Intent(context, ActivityNewest.class));
                //获取登录状态islogin
                Boolean islogin3 = (Boolean) SpUtil.getSpData(context, "isLogin", false);
                //判断islogin
                if (islogin3) {
//                    //吐司已经登录
//                    toast(context, "已登录");
                    //请求网络数据方法最新版本
                    dohttpNewest();
                } else {
                    //吐司已经登录
                    toast(context, "请先登录");
                    //跳转登录
                    context.startActivity(new Intent(context, ActivityLogin.class));
                }
                break;
        }
    }

    //最新版本判断方法
    private void dohttpNewest() {
        //newhasmap
        HashMap<String,String> map=new HashMap<>();
        //往map里面存值
        map.put("userId",userId1);
        map.put("sessionId",sessionId1);
        map.put("ak",ak);
        //get hand请求数据
        handGetString(Http.NEWEST_URL,3,map);
        Logger.i("版本更新",map.get("userId")+map.get("sessionId")+"版本号"+map.get("ak"));
    }

    //成功方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择版本类型
        switch(type){
            case 3:
                //打印数据
                Logger.i("版本数据信息查询成功","哈哈"+data);
                //new gson  data bean
                NewestBean newestBean = new Gson().fromJson(data, NewestBean.class);
                //吐司最新版本
                toast(context,"已经是最新版本");
                break;
        }
    }

    //失败方法
    @Override
    public void failString(String msg) {
        super.failString(msg);
        //吐司最新版本
        toast(context,"失败了");
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
        Logger.i("我的", nickName1);
        //获取登录状态
        Boolean islogin = (Boolean) SpUtil.getSpData(context,"isLogin",false);
        //判断登录状态
       if(islogin){
           //赋值登录后的值
           me_sdv_head.setImageURI(headPic1);
           me_tv_nickname.setText(nickName1);
       }else {
           //赋值初始值 "res///"
           me_sdv_head.setImageURI("res:///"+R.drawable.user_head);
           me_tv_nickname.setText("登录/注册");
       }

    }
}
