package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityOpinion;
import com.bw.movie.activitys.ActivityOpinionSuccess;
import com.bw.movie.activitys.ActivityUpdateName;
import com.bw.movie.entity.OpinionBean;
import com.bw.movie.entity.UpdateBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinionPersenter(我的意见反馈页面)
 * */

//继承APPDelegate
public class ActivityOpinionPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText opinion_et_text;
    private TextView opinion_commit;
    private CircleImageView opinion_cv_leftreturn;
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

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_opinion;
    }

    //重写初始化上下文方法
    @Override
    public void initContext(Context context) {
        //删了super这行提上去上下文
        this.context=context;
    }

    //重写初始化数据方法
    @Override
    public void initData() {
        super.initData();
        //初始化数据方法
        initwidget();
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        opinion_et_text=(EditText)getView(R.id.opinion_et_text);
        opinion_commit=(TextView)getView(R.id.opinion_commit);
        opinion_cv_leftreturn=(CircleImageView)getView(R.id.opinion_cv_leftreturn);
        //点击事件
        opinion_commit.setOnClickListener(this);
        opinion_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch(view.getId()){
            case R.id.opinion_commit:
                //吐司提交
//                toast(context,"提交");
                //请求意见反馈网络数据方法
                dohttpOpinion();
                break;
            case R.id.opinion_cv_leftreturn:
                //销毁页面强转
                ((ActivityOpinion)context).finish();
                break;
        }
    }

    //请求意见反馈网络数据方法
    private void dohttpOpinion() {
        //获取输入框的内容
        String et_text = opinion_et_text.getText().toString().trim();
        //判断输入内容不能为空
        if(TextUtils.isEmpty(et_text)){
            //吐司请先输入反馈内容
            toast(context,"请先输入反馈内容");
           //为空就返回直接 不往下直接执行
           return;
        }
        //new hasmap
        HashMap<String,String> hmap=new HashMap<>();
        HashMap<String,String> fmap=new HashMap<>();
        //往map集合里添加
        hmap.put("userId", userId1);
        hmap.put("sessionId", sessionId1);
        //入参的数据
        fmap.put("content",et_text);
        //post请求数据 传网址类型0 map集合
        handPostString(Http.OPINION_URL,0,hmap,fmap);
        Logger.i("map里","意见反馈id"+hmap.get("userId")+"sessId"+hmap.get("sessionId")+"输入框内容"+fmap.get("content"));
    }

    //成功方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择类型
        switch (type){
            case 0:
                //打印
                Logger.i("意见反馈数据","哈哈哈"+data);
                //new gson form
                OpinionBean opinionBean = new Gson().fromJson(data, OpinionBean.class);
                //判断message 网络异常,请联系管理员
                if("网络异常,请联系管理员".equals(opinionBean.getMessage())){
                    //吐司网络异常，请联系管理员
                    toast(context,"网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                }else if ("请先登录".equals(opinionBean.getMessage())){
                    //吐司网络异常，请联系管理员
                    toast(context, "登录过期,请重新登录");
                    //吐司完直接返回 不往下执行
                    return;
                }else{
                    //打印log
                    Logger.i("意见反馈成功","成功"+opinionBean.getMessage());
                    //强转上下文跳转
                    context.startActivity(new Intent(context, ActivityOpinionSuccess.class));
                    //清空输入框内容 给输入框赋空值
                    opinion_et_text.setText("");
                }
                break;
        }
    }
    //失败方法
    @Override
    public void failString(String msg) {
        super.failString(msg);
        //吐司失败
        toast(context,"意见反馈失败");
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
    }
}
