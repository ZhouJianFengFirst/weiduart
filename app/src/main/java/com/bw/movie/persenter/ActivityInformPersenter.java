package com.bw.movie.persenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityInform;
import com.bw.movie.adapter.InformRvAdapter;
import com.bw.movie.entity.InformCheckedBean;
import com.bw.movie.entity.InformNumBean;
import com.bw.movie.entity.SelectInformBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityInformPersenter(我的通知页)
 */

//继承APPDelegate
public class ActivityInformPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView inform_message;
    private RecyclerView inform_rv;
    private CircleImageView inform_cv_leftreturn;
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
    private String page = "1";
    private String count = "5";
    private InformRvAdapter informRvAdapter;
    private List<SelectInformBean.ResultBean> result=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_inform;
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
        //new 线性管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        //设置方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //给recycler设置管理器
        inform_rv.setLayoutManager(linearLayoutManager);
        //实例化适配器提上去
        informRvAdapter = new InformRvAdapter(context);
        //适配器接口回调
        informRvAdapter.setOnShowListener(new InformRvAdapter.OnShowListener() {
            @Override
            public void success(int index) {
                //请求状态信息改变的网络传index
                dohttpInformChecked(index);
            }
        });
        //设置适配器
        inform_rv.setAdapter(informRvAdapter);
    }

    //状态改变的网络请求数据
    private void dohttpInformChecked(int index) {
        //new hasmap
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> hmap = new HashMap<>();
        //装两个id
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        //传入参
        hmap.put("id", index+"");
        //get header请求
        HeadOrQuertGet(Http.INFORMCHECKED_URL, 1, map, hmap);
        Logger.i("状态改变id",map.get("userId") + map.get("sessionId") +"状态id"+ hmap.get("id"));
    }

    //请求网络数据方法
    private void dohttpInform() {
        //new hasmap
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> hmap = new HashMap<>();
        //装两个id
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        //传入参
        hmap.put("page", page);
        hmap.put("count", count);
        //get header请求
        HeadOrQuertGet(Http.INFORM_URL, 0, map, hmap);
        Logger.i("通知页面id", map.get("userId") + map.get("sessionId") + hmap.get("page") + hmap.get("count"));
    }

    //成功方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择上面的类型
        switch (type) {
            case 0:
                //打印数据
                Logger.i("系统信息数据", "哈哈" + data);
                //new gson from
                SelectInformBean selectInformBean = new Gson().fromJson(data, SelectInformBean.class);
                //获取bean类的集合返回值
                 result = selectInformBean.getResult();
                //设置适配器去外面实例化适配器
                //给适配器设置集合
                informRvAdapter.setList(result);
                break;
            case 1:
                //打印数据
                Logger.i("状态改变", "哈哈" + data);
                //new  gson  data bean
                InformCheckedBean informCheckedBean = new Gson().fromJson(data, InformCheckedBean.class);
//                int status = Integer.parseInt(informCheckedBean.getStatus());
//                result.get(0).setStatus(status);
//                informRvAdapter.setList(result);
                break;
            case 2:
                //打印数据
                Logger.i("系统消息数量", "哈哈" + data);
                //new  gson  data bean
                InformNumBean informNumBean = new Gson().fromJson(data, InformNumBean.class);
                //给控件赋值
                inform_message.setText("系统消息("+informNumBean.getCount()+"条未读)");
                break;
        }
    }

    //失败方法
    @Override
    public void failString(String msg) {
        super.failString(msg);
        //打印失败
        Logger.i("失败", "系统信息失败");
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        inform_message = (TextView) getView(R.id.inform_message);
        inform_rv = (RecyclerView) getView(R.id.inform_rv);
        inform_cv_leftreturn = (CircleImageView) getView(R.id.inform_cv_leftreturn);
        //点击事件
        inform_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.inform_cv_leftreturn:
                //销毁本页面
                ((ActivityInform) context).finish();
                break;
        }
    }

    //获取到的值
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
        //如果写在外面获取不到就在这获取方法
        //请求网络数据方法
        dohttpInform();
        //请求查询消息数量方法
        dohttpInformNum();
    }

    //请求查询消息数量方法
    private void dohttpInformNum() {
        //new hasmap
        HashMap<String, String> map = new HashMap<>();
        //装两个id
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        //get header请求
        handGetString(Http.INFORMNUM_URL, 2, map);
        Logger.i("系统消息数量id", map.get("userId") + map.get("sessionId"));
    }


}
