package com.bw.movie.persenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityLogin;
import com.bw.movie.activitys.ActivityRegister;
import com.bw.movie.entity.LoginBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.BaseObserver;
import com.bw.movie.net.Http;
import com.bw.movie.net.HttpHelper;
import com.bw.movie.net.HttpRequestListener;
import com.bw.movie.net.OkHttpHelper;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Logger;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import okhttp3.FormBody;
import okhttp3.ResponseBody;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityBeginPersenter(注册页)
 */
public class ActivityRegisterPersenter extends AppDelegate {
    private EditText edname;
    private EditText edsex;
    private EditText edate;
    private EditText edpgone;
    private EditText edemil;
    private EditText edpass;
    private EditText edaffirmpass;
    private Button btregister;
    private static final int REGISTER_URL = 0 * 123;
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }


    @Override
    public void initData() {
        super.initData();
        initwidget();
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.register_bt_register://点击注册
                        String registername = edname.getText().toString().trim();
                        String registerpass = edpass.getText().toString().trim();
                        String registersex = edsex.getText().toString().trim();
                        String registerdate = edate.getText().toString().trim();
                        String registerphone = edpgone.getText().toString().trim();
                        String registeremil = edemil.getText().toString().trim();
                        String registeraffirmpass = edaffirmpass.getText().toString().trim();
                        setRegister(registername, registerpass, registersex, registerdate, registerphone, registeremil, registeraffirmpass);
                        break;
                }
            }
        }, R.id.register_bt_register);
    }

    private void setRegister(String registername, String registerpass, String registersex, String registerdate, String registerphone, String registeremil, String registeraffirmpass) {
        if (TextUtils.isEmpty(registername)) {
            toast("提示", "姓名不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registerpass)) {
            toast("提示", "密码不能为空", 1);
            return;
        }
        if ( TextUtils.isEmpty(registeraffirmpass)) {
            toast("提示", "密码不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registersex)) {
            toast("提示", "性别不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registerdate)) {
            toast("提示", "日期不能为空", 1);
            return;
        }
        if ( TextUtils.isEmpty(registerphone)) {
            toast("提示", "手机号不能为空", 1);
            return;
        }
        if ( TextUtils.isEmpty(registeremil)) {
            toast("提示", "邮箱不能为空", 1);
            return;
        }
        if (!registerpass.equals(registeraffirmpass)) {
            toast("提示", "请输入一致密码", 1);
            return;
        }

        String registerpass1 = EncryptUtil.encrypt(registerpass);


        Map<String, String> map = new HashMap<>();
        map.put("nickName", registername);
        map.put("pwd", registerpass1);
        map.put("pwd2", registerpass1);
        map.put("phone", registerphone);
        if ("男".equals(registersex)) {
            map.put("sex", 1 + "");
        } else if ("女".equals(registersex)) {
            map.put("sex", 2 + "");
        }
        map.put("birthday", registerdate);
        map.put("email", registeremil);
        HttpHelper.getInstens().registerPost("/movieApi/user/v1/registerUser", map, new BaseObserver<ResponseBody>() {

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(string, LoginBean.class);
                    if ("0000".equals(loginBean.getStatus())) {
                        toast("提示", "注册成功", 1);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                ((ActivityRegister) context).finish();
                            }
                        }).start();
                    } else {
                        toast("提示", "注册失败", 1);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable e) {
                Logger.d("Tagger", e.getMessage());
            }
        });
    }

   /* @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case REGISTER_URL:
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                if ("0000".equals(loginBean.getStatus())) {
                    Logger.d("ttttt", "成功");
                    Logger.d("tttttt", data + "成功");
                } else {
                    Logger.d("qqq", "失败");
                    Logger.d("qqq", data + "失败");
                }
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.d("ffff", msg);
    }
*/
    private void initwidget() {
        edname = (EditText) getView(R.id.register_ed_name);
        edate = (EditText) getView(R.id.register_ed_date);
        edemil = (EditText) getView(R.id.register_ed_emil);
        edpass = (EditText) getView(R.id.register_ed_pass);
        edaffirmpass = (EditText) getView(R.id.register_ed_affirmpass);
        edpgone = (EditText) getView(R.id.register_ed_phone);
        edsex = (EditText) getView(R.id.register_ed_sex);
        btregister = (Button) getView(R.id.register_bt_register);

    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }
}
