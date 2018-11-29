package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityResetPwd;
import com.bw.movie.mvp.view.AppDelegate;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityResetPwdPersenter(我的重置密码页面)
 * */

//继承APPdelegate
public class ActivityResetPwdPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText resetpwd_pwd;
    private TextView resetpwd_ok;
    private CircleImageView resetpwd_cv_leftreturn;

    @Override
    protected int getLayoutId() {
        //返回本页面的布局
        return R.layout.activity_reset_pwd;
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

    private void initwidget() {
        //获取控件提上去
        resetpwd_pwd=(EditText)getView(R.id.resetpwd_pwd);
        resetpwd_ok=(TextView)getView(R.id.resetpwd_ok);
        resetpwd_cv_leftreturn=(CircleImageView)getView(R.id.resetpwd_cv_leftreturn);
        //点击事件
        resetpwd_ok.setOnClickListener(this);
        resetpwd_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch(view.getId()){
            case R.id.resetpwd_ok:
                //吐司修改密码成功
                toast(context,"重置密码成功");
                //销毁本页面
                ((ActivityResetPwd)context).finish();
                break;
            case R.id.resetpwd_cv_leftreturn:
                //销毁本页面
                ((ActivityResetPwd)context).finish();
                break;
        }
    }
}
