package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityResetPwd;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Logger;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityResetPwdPersenter(我的重置密码页面)
 */

//继承APPdelegate
public class ActivityResetPwdPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView resetpwd_ok;
    private CircleImageView resetpwd_cv_leftreturn;
    private EditText resetpwd_old_pwd;
    private EditText resetpwd_new_pwd;
    private EditText resetpwd_ok_pwd;
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
    private String pwd1;

    @Override
    protected int getLayoutId() {
        //返回本页面的布局
        return R.layout.activity_reset_pwd;
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

    private void initwidget() {
        //获取控件提上去
        resetpwd_old_pwd = (EditText) getView(R.id.resetpwd_old_pwd);
        resetpwd_new_pwd = (EditText) getView(R.id.resetpwd_new_pwd);
        resetpwd_ok_pwd = (EditText) getView(R.id.resetpwd_ok_pwd);
        resetpwd_ok = (TextView) getView(R.id.resetpwd_ok);
        resetpwd_cv_leftreturn = (CircleImageView) getView(R.id.resetpwd_cv_leftreturn);
        //点击事件
        resetpwd_ok.setOnClickListener(this);
        resetpwd_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.resetpwd_ok:
//                //吐司修改密码成功
//                toast(context,"重置密码成功");
//                //销毁本页面
//                ((ActivityResetPwd)context).finish();
                //请求重置密码网络数据方法
                dohttpResetPwd();
                break;
            case R.id.resetpwd_cv_leftreturn:
                //销毁本页面
                ((ActivityResetPwd) context).finish();
                break;
        }
    }

//    //判断message 网络异常,请联系管理员
//                if("网络异常,请联系管理员".equals(updateBean.getMessage())){
//        //吐司网络异常，请联系管理员
//        toast(context,"网络异常,请联系管理员");
//        //吐司完直接返回 不往下执行
//        return;
//    }

    //请求重置密码网络数据方法
    private void dohttpResetPwd() {
        Logger.i("旧密码", "旧密码" + pwd1 + "用户id" + userId1 + "sessionid" + sessionId1);
        //获取输入框的内容先
        String old_pwd = resetpwd_old_pwd.getText().toString().trim();
        String new_pwd = resetpwd_new_pwd.getText().toString().trim();
        String ok_pwd = resetpwd_ok_pwd.getText().toString().trim();
        //判断旧密码是否和存在sp里面的一致
        if (!old_pwd.equals(pwd1)) {
            //吐司密码不一致
            toast(context, "密码跟原先不一致");
            //如果不一致就return 不往下执行
            return;
        }
        //判断新密码再确认新密码
        if (!new_pwd.equals(ok_pwd)) {
            //吐司密码不一致
            toast(context, "密码跟新输入的不一致");
            //如果不一致就return 不往下执行
            return;
        }

    }

    //获取到的值
    public void setData(String message1, String status1, String sessionId1, String userId1, String headPic1, String nickName1, String phone1, String birthday1, String id1, String lastLoginTime1, String sex1, String pwd1) {
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
        this.pwd1 = pwd1;
        Logger.i("旧密码", pwd1 + "回家");
    }
}
