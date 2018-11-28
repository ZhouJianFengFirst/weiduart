package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityLogin;
import com.bw.movie.activitys.ActivityRegister;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityLogin(登录页面)
 */
public class ActivityLoginPersenter extends AppDelegate {
    private Context mcontext;
    private EditText edphone;
    private EditText edpass;
    private Button btlogin;
    private ImageView imgrepass;
    private ImageView imgsmlogin;
    private ImageView imgthird;
    private ImageView imglookpass;
    private TextView txtjustregister;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_txt_justregister:
                        //点击跳转到注册页面
                        ((ActivityLogin) mcontext).startActivity(new Intent(mcontext, ActivityRegister.class));
                        break;
                }
            }
        }, R.id.login_txt_justregister);
    }

    private void initwidget() {
        edphone = (EditText) getView(R.id.login_ed_phone);
        edpass = (EditText) getView(R.id.login_ed_pass);
        btlogin = (Button) getView(R.id.login_bt_login);
        imgrepass = (ImageView) getView(R.id.login_img_repass);
        imgsmlogin = (ImageView) getView(R.id.login_img_smlogin);
        imgthird = (ImageView) getView(R.id.login_img_third);
        txtjustregister = (TextView) getView(R.id.login_txt_justregister);
        imglookpass = (ImageView) getView(R.id.login_img_lookpass);
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }
}
