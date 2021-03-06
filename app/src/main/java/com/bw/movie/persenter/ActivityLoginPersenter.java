package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.App;
import com.bw.movie.R;
import com.bw.movie.activitys.ActivityLogin;
import com.bw.movie.activitys.ActivityRegister;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.entity.LoginBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.net.HttpRequestListener;
import com.bw.movie.net.OkHttpHelper;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SharedUtil;
import com.bw.movie.utils.SpUtil;
import com.bw.movie.utils.Validator;
import com.bw.movie.utils.WXUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityLogin(登录页面)
 */
public class ActivityLoginPersenter extends AppDelegate implements View.OnClickListener {
    private Context mcontext;
    private EditText edphone;
    private EditText edpass;
    private Button btlogin;
    private ImageView imgrepass;
    private ImageView imgsmlogin;
    private ImageView imgthird;
    private ImageView imglookpass;
    private TextView txtjustregister;
    private String loginphone;
    private boolean flag = false;
    private boolean auto = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        boolean isAuto = (boolean) SpUtil.getSpData(mcontext, "isAuto", false);
        if (!isAuto) {
            boolean isremenber = (boolean) SpUtil.getSpData(mcontext, "isRemenber", false);
            /*
             * 判断是否记住密码
             * */
            if (isremenber) {
                //将账号和密码都设置到文本中
                String phone = (String) SpUtil.getSpData(mcontext, "phone", "");
                String pwd = (String) SpUtil.getSpData(mcontext, "pwd", "");
                edphone.setText(phone);
                edpass.setText(pwd);
            }
        } else {
            //跳转界面
            Intent intent = new Intent(mcontext, MainActivity.class);
            ((ActivityLogin) mcontext).startActivity(intent);
            ((ActivityLogin) mcontext).finish();

        }


        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_bt_login://登录按钮
                        loginphone = edphone.getText().toString().trim();
                        String loginpass = edpass.getText().toString().trim();

                        uselogin(loginphone, loginpass);
                        break;
                    case R.id.login_txt_justregister://点击跳转到注册页面
                        ((ActivityLogin) mcontext).startActivity(new Intent(mcontext, ActivityRegister.class));
                        break;
                    case R.id.login_img_lookpass://点击是否隐藏密码
                        pwdShow(edpass, imglookpass);
                        break;
                    case R.id.login_img_repass://点击是否记住密码
                        setCkR();
                        break;
                    case R.id.login_img_smlogin://点击是否自动登录

                        setCkSM();
                        break;
                }
            }
        }, R.id.login_txt_justregister, R.id.login_img_smlogin, R.id.login_bt_login, R.id.login_img_lookpass, R.id.login_img_repass);
    }

    /*
     *登录
     * */
    private void uselogin(final String loginphone, final String loginpass) {

        if (TextUtils.isEmpty(loginphone) && TextUtils.isEmpty(loginpass)) {
            toast("警告", "用户名或密码不能为空！！", 1);
            return;
        }
        if (!Validator.isMobile(loginphone)) {
            toast("警告", "请输入正确手机号哦", 1);
            return;
        }
        String encrypt = EncryptUtil.encrypt(loginpass);
        //网络请求okhttp
        FormBody build = new FormBody.Builder().add("phone", loginphone).add("pwd", encrypt)
                .build();
        new OkHttpHelper(new HttpRequestListener() {
            @Override
            public void SuccessRequest(String data) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                SharedUtil.put(mcontext, "phones", loginBean.getResult().getUserInfo().getPhone());
                SharedUtil.put(mcontext, "sex", loginBean.getResult().getUserInfo().getSex() + "");
                SpUtil.saveData(mcontext, "message", loginBean.getMessage());
                SpUtil.saveData(mcontext, "status", loginBean.getStatus());
                SpUtil.saveData(mcontext, "sessionId", loginBean.getResult().getSessionId());
                SpUtil.saveData(mcontext, "userId", loginBean.getResult().getUserId() + "");
                SpUtil.saveData(mcontext, "headPic", loginBean.getResult().getUserInfo().getHeadPic());
                SpUtil.saveData(mcontext, "nickName", loginBean.getResult().getUserInfo().getNickName());
                SpUtil.saveData(mcontext, "phone", loginBean.getResult().getUserInfo().getPhone());
                SpUtil.saveData(mcontext, "birthday", loginBean.getResult().getUserInfo().getBirthday() + "");
                SpUtil.saveData(mcontext, "id", loginBean.getResult().getUserInfo().getId() + "");
                SpUtil.saveData(mcontext, "lastLoginTime", loginBean.getResult().getUserInfo().getLastLoginTime() + "");
                SpUtil.saveData(mcontext, "sex", loginBean.getResult().getUserInfo().getSex() + "");
                SpUtil.saveData(mcontext, "isLogin", true);
                //重置密码是需要存的数据
                SpUtil.saveData(mcontext, "ppwd", loginpass);
                if (auto) {
                    SpUtil.saveData(mcontext, "isAuto", true);
                } else if (flag) {
                    SpUtil.saveData(mcontext, "pwd", loginpass);
                    SpUtil.saveData(mcontext, "isRemenber", true);

                } else {
                    SpUtil.clear();
                }
                toast("登录", "登录成功", 1);
                /*
                 *开个线程
                 * */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ((ActivityLogin) mcontext).startActivity(new Intent(mcontext, MainActivity.class));
                        ((ActivityLogin) mcontext).finish();
                    }
                }).start();
            }

            @Override
            public void Filed(String msg) {
                toast("登录", "登录失败", 3);
            }
        }).doPost(Http.BASE_URL + "movieApi/user/v1/login", build);
    }

    /*
     * 判断记住密码
     * */
    private void setCkR() {
        if (!flag) {
            imgrepass.setImageResource(R.drawable.login_ck_yes);
        } else {
            imgrepass.setImageResource(R.drawable.login_ck_no);
        }
        //每次置反
        flag = !flag;
    }

    /*
     * 判断是否自动登录
     * */
    private void setCkSM() {
        if (!auto) {
            imgsmlogin.setImageResource(R.drawable.login_ck_yes);
        } else {
            imgsmlogin.setImageResource(R.drawable.login_ck_no);
        }
        //每次置反
        auto = !auto;
    }

    /*
     * 初始化控件
     * */
    private void initwidget() {
        edphone = (EditText) getView(R.id.login_ed_phone);
        edpass = (EditText) getView(R.id.login_ed_pass);
        btlogin = (Button) getView(R.id.login_bt_login);
        imgrepass = (ImageView) getView(R.id.login_img_repass);
        imgsmlogin = (ImageView) getView(R.id.login_img_smlogin);
        imgthird = (ImageView) getView(R.id.login_img_third);
        txtjustregister = (TextView) getView(R.id.login_txt_justregister);
        imglookpass = (ImageView) getView(R.id.login_img_lookpass);
        setClick(this, R.id.login_txt_justregister);
    }

    /**
     * @param editText
     * @param imageView 设置隐藏/显示密码
     */
    public void pwdShow(EditText editText, ImageView imageView) {

        int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if (editText.getInputType() == type) {//密码可见

            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageView.setImageResource(R.drawable.log_icon_eye);
            /*imageView.setImageDrawable(getResources().getDrawable(R.drawable.log_icon_eye));*/
            editText.setSelection(editText.getText().length());     //把光标设置到当前文本末尾

        } else {
            editText.setInputType(type);
            imageView.setImageResource(R.drawable.log_icon_leye);
            /*    imageView.setImageDrawable(getResources().getDrawable(R.drawable.log_icon_leye));*/
            editText.setSelection(editText.getText().length());
        }

    }

    /*
     * 初始化上下文
     * */
    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_txt_justregister:
                wxLogin();
                break;
        }
    }

    private void wxLogin() {
        //先判断是否安装微信APP,按照微信的说法，目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
        if (!WXUtils.isWeixinAvilible(mcontext)) {
            toast(mcontext, "您还未安装微信客户端");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        //像微信发送请求
        App.mWxApi.sendReq(req);
    }
}
