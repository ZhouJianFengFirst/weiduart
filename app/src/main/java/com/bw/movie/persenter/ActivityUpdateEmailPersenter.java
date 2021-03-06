package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityUpdateEmail;
import com.bw.movie.mvp.view.AppDelegate;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/29
 * 作用：修改邮箱页面
 * */

public class ActivityUpdateEmailPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText update_email;
    private TextView update_email_ok;
    private CircleImageView update_email_cv_leftreturn;
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
        //返回本页面的布局
        return R.layout.activity_update_email;
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

    //初始化数据方法
    private void initwidget() {
        //获取控件提上去
        update_email=(EditText)getView(R.id.update_email);
        update_email_ok=(TextView)getView(R.id.update_email_ok);
        update_email_cv_leftreturn=(CircleImageView)getView(R.id.update_email_cv_leftreturn);
        //点击事件
        update_email_ok.setOnClickListener(this);
        update_email_cv_leftreturn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch(view.getId()){
            case R.id.update_email_ok:
                //吐司修改密码成功
                toast(context,"邮箱不能修改因为登录接口和查询用户信息接口里都没有email参数");
                //销毁本页面
                ((ActivityUpdateEmail)context).finish();
                break;
            case R.id.update_email_cv_leftreturn:
                //销毁本页面
                ((ActivityUpdateEmail)context).finish();
                break;
        }
    }

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
