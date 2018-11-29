package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityMessage;
import com.bw.movie.activitys.ActivityResetPwd;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.HttpHelper;
import com.bw.movie.utils.SpUtil;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityMessagePersenter(我的信息页面)
 * */

//继承APPDelegate
public class ActivityMessagePersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private RelativeLayout message_rl_head;
    private RelativeLayout message_rl_nickname;
    private RelativeLayout message_rl_sex;
    private RelativeLayout message_rl_date;
    private RelativeLayout message_rl_phone;
    private RelativeLayout message_rl_email;
    private RelativeLayout message_rl_pwd;
    private CircleImageView message_cv_leftreturn;
    private TextView message_tv_exit;
    private String userId;
    private String sessionId;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_message;
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
        //请求获取我的信息网络请求
        dohttpSelect();
        //获取sp里面的数据userId sessionId
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        //获取数据 提上去
         userId = sp.getString("userId", "");
         sessionId = sp.getString("sessionId()", "");
    }

    //请求网络数据
    private void dohttpSelect() {
        //new hasmap
        HashMap<String,String> map=new HashMap<>();
        //往map里面存值
        map.put("userId",userId);
        map.put("sessionId()",sessionId);
        //请求get方法
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        message_rl_head=(RelativeLayout)getView(R.id.message_rl_head);
        message_rl_nickname=(RelativeLayout)getView(R.id.message_rl_nickname);
        message_rl_sex=(RelativeLayout)getView(R.id.message_rl_sex);
        message_rl_date=(RelativeLayout)getView(R.id.message_rl_date);
        message_rl_phone=(RelativeLayout)getView(R.id.message_rl_phone);
        message_rl_email=(RelativeLayout)getView(R.id.message_rl_email);
        message_rl_pwd=(RelativeLayout)getView(R.id.message_rl_pwd);
        message_cv_leftreturn=(CircleImageView)getView(R.id.message_cv_leftreturn);
        message_tv_exit=(TextView)getView(R.id.message_tv_exit);
        //点击事件
        message_rl_head.setOnClickListener(this);
        message_rl_nickname.setOnClickListener(this);
        message_rl_sex.setOnClickListener(this);
        message_rl_date.setOnClickListener(this);
        message_rl_phone.setOnClickListener(this);
        message_rl_email.setOnClickListener(this);
        message_rl_pwd.setOnClickListener(this);
        message_cv_leftreturn.setOnClickListener(this);
        message_tv_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.message_rl_head:
                //吐司切换头像
                Toast.makeText(context,"切换头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_nickname:
                //吐司修改昵称
                Toast.makeText(context,"修改昵称",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_sex:
                //吐司修改性别
                Toast.makeText(context,"修改性别",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_date:
                //吐司修改出生日期
                Toast.makeText(context,"修改出生日期",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_phone:
                //吐司修改手机号
                Toast.makeText(context,"修改手机号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_email:
                //吐司修改邮箱
                Toast.makeText(context,"修改邮箱",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_pwd:
                //吐司重置密码
//                Toast.makeText(context,"重置密码",Toast.LENGTH_SHORT).show();
                //跳转强转上下文
                context.startActivity(new Intent(context, ActivityResetPwd.class));
                break;
            case R.id.message_cv_leftreturn:
                //吐司销毁这个页面,返回上一个
//                Toast.makeText(context,"销毁这个页面,返回上一个",Toast.LENGTH_SHORT).show();
                //强转上下文销毁这个页面
                ((ActivityMessage)context).finish();
                break;
            case R.id.message_tv_exit:
                //吐司退出登录
                Toast.makeText(context,"退出登录",Toast.LENGTH_SHORT).show();
                //销毁本页面
                ((ActivityMessage)context).finish();
                break;

        }
    }
}
