package com.bw.movie.persenter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bw.movie.R;
import com.bw.movie.activitys.ActivityRegister;
import com.bw.movie.entity.LoginBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.BaseObserver;
import com.bw.movie.net.HttpHelper;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.Logger;
import com.bw.movie.view.SexBox;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityBeginPersenter(注册页)
 */
public class ActivityRegisterPersenter extends AppDelegate implements SexBox.SexBoxListener {
    private EditText edname,edpgone,edpass,edemil,edaffirmpass;
    private Context context;
    private TextView edate;
    private Button btregister;
    private SexBox sexboxsex;
    private int sex = 0;
    private OptionsPickerView pvOptions;
    private TimePickerView pvTime;

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
                        /*String registersex = sexboxsex.getText().toString().trim();*/
                        //时间选择器
                        pvTime = new TimePickerView(context, TimePickerView.Type.ALL);
                        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
                        pvTime.setTime(new Date());
                        pvTime.setCyclic(false);

                        pvTime.setTitle("请选择出生日期");

                        //pvTime.setTime();
                        pvTime.setCancelable(true);
                        //时间选择后回调
                        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                            @Override
                            public void onTimeSelect(Date date) {
                                edate.setText(date.getTime()+"");
                                Logger.d("qwert",date.getTime()+"");
                            }
                        });
                        //弹出时间选择器
                        edate.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                pvTime.show();
                            }
                        });
                        //选项选择器
                        pvOptions = new OptionsPickerView(context);

                      
                        String registerphone = edpgone.getText().toString().trim();
                        String registeremil = edemil.getText().toString().trim();
                        String registeraffirmpass = edaffirmpass.getText().toString().trim();
                        setRegister(registername,sex, registerpass, registerphone, registeremil, registeraffirmpass);
                        break;
                }
            }
        }, R.id.register_bt_register);
    }

    private void setRegister(String registername,int sex ,String registerpass, String registerphone, String registeremil, String registeraffirmpass) {
        if (TextUtils.isEmpty(registername)) {
            toast("提示", "姓名不能为空", 1);
            return;
        }
      if (TextUtils.isEmpty(sex+"")) {
            toast("提示", "请选择性别", 1);
            return;
        }
     /*   if (TextUtils.isEmpty(registerdate)) {
            toast("提示", "日期不能为空", 1);
            return;
        }*/
        if (TextUtils.isEmpty(registerphone)) {
            toast("提示", "手机号不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registeremil)) {
            toast("提示", "邮箱不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registerpass)) {
            toast("提示", "密码不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(registeraffirmpass)) {
            toast("提示", "密码不能为空", 1);
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
        map.put("sex",sex+"");
     /*   if ("男".equals(registersex)) {
            map.put("sex", 1 + "");
        } else if ("女".equals(registersex)) {
            map.put("sex", 2 + "");
        }*/
    /*    map.put("birthday", registerdate);*/
        map.put("email", registeremil);
        HttpHelper.getInstens().PostForm("/movieApi/user/v1/registerUser", map, new BaseObserver<ResponseBody>() {

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
    private void initwidget() {
        edname = (EditText) getView(R.id.register_ed_name);
        edate = (TextView) getView(R.id.register_ed_date);
        edemil = (EditText) getView(R.id.register_ed_emil);
        edpass = (EditText) getView(R.id.register_ed_pass);
        edaffirmpass = (EditText) getView(R.id.register_ed_affirmpass);
        edpgone = (EditText) getView(R.id.register_ed_phone);
        sexboxsex = (SexBox) getView(R.id.register_ed_sex);
        btregister = (Button) getView(R.id.register_bt_register);
        sexboxsex.setSexBoxListener(this);

    }


    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    public void getSex(int sex) {
        this.sex = sex;
        toast(context,sex+"");
    }


    }

