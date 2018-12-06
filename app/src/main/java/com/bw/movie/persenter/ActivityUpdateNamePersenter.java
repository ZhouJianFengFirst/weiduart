package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityUpdateName;
import com.bw.movie.entity.UpdateBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.net.HttpRequestListener;
import com.bw.movie.net.OkHttpHelper;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.SpUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;

/**
 * 作者：mafuyan
 * 时间：2018/11/29
 * 作用：修改昵称页面
 */

public class ActivityUpdateNamePersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText update_name;
    private TextView update_name_ok;
    private CircleImageView update_name_cv_leftreturn;
    private String nickname1;
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
        return R.layout.activity_update_name;
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
        //获取意图里的值赋值给新控件
        Intent intent = ((ActivityUpdateName) context).getIntent();
        //获取意图里的值
        nickname1 = intent.getStringExtra("nickName1");
        //赋值给新控件
        update_name.setText(nickname1);
    }

    private void dohttpUpdateName() {
        //获取输入框的内容
        String name = update_name.getText().toString().trim();
        //new hasmap
        HashMap<String, String> hmap = new HashMap<>();
        HashMap<String, String> fmap = new HashMap<>();
        //往map集合里添加
        hmap.put("userId", userId1);
        hmap.put("sessionId", sessionId1);
        //入参的数据
        fmap.put("nickName", name);
        fmap.put("sex", "2");
        fmap.put("email", "123456@163.com");
        //post请求数据 传网址类型0 map集合
        handPostString(Http.UPDATA_URL, 0, hmap, fmap);
        Logger.i("map里", "用户id" + hmap.get("userId") + "sessId" + hmap.get("sessionId") + fmap.get("nickName"));
    }

    //成功方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择类型
        switch (type) {
            case 0:
                //打印
                Logger.i("修改昵称数据", "哈哈哈" + data);
                //new gson form
                UpdateBean updateBean = new Gson().fromJson(data, UpdateBean.class);
                //判断message 网络异常,请联系管理员
                if ("网络异常,请联系管理员".equals(updateBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("请先登录".equals(updateBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "登录过期,请重新登录");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("修改成功".equals(updateBean.getMessage())) {
                    Logger.i("修改后", updateBean.getResult().getNickName());
                    //存到sp
                    SpUtil.saveData(context, "nickName", updateBean.getResult().getNickName());
                    String nickName2 = (String) SpUtil.getSpData(context, "nickName", "");
                    Logger.i("存到sp的name", nickName2);
                    //吐司修改密码成功
                    toast(context, "修改昵称成功");
                    //销毁本页面
                    ((ActivityUpdateName) context).finish();
                }
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        Logger.i("修改昵称失败", msg);
    }

    //初始化数据方法
    private void initwidget() {
        //获取控件提上去
        update_name = (EditText) getView(R.id.update_name);
        update_name_ok = (TextView) getView(R.id.update_name_ok);
        update_name_cv_leftreturn = (CircleImageView) getView(R.id.update_name_cv_leftreturn);
        //点击事件
        update_name_ok.setOnClickListener(this);
        update_name_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.update_name_ok:
                //在获取sp值的这个方法请求网络数据
                //请求网络数据
                dohttpUpdateName();
                break;
            case R.id.update_name_cv_leftreturn:
                //销毁本页面
                ((ActivityUpdateName) context).finish();
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
