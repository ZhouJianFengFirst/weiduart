package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

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
                    case R.id.register_bt_register:
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


    }

    private void initwidget() {
        edname = (EditText) getView(R.id.register_ed_name);
        edate = (EditText) getView(R.id.register_ed_date);
        edemil = (EditText) getView(R.id.register_ed_emil);
        edpass = (EditText) getView(R.id.register_ed_pass);
        edpgone = (EditText) getView(R.id.register_ed_phone);
        edsex = (EditText) getView(R.id.register_ed_sex);
        edaffirmpass = (EditText) getView(R.id.register_ed_affirmpass);
        btregister = (Button) getView(R.id.register_bt_register);

    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
    }
}
