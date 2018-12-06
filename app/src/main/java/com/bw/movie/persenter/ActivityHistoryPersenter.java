package com.bw.movie.persenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityHistory;
import com.bw.movie.adapter.HistoryvAdapter;
import com.bw.movie.entity.HistoryBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.QueryMap;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityHistoryPersenter(我的购票记录页面)
 * */

//继承APPDelegate
public class ActivityHistoryPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private XRecyclerView history_rv;
    private CircleImageView history_cv_leftreturn;
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
    private String page="1";
    private String count="5";
    private HistoryvAdapter historyvAdapter;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_history;
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
        //实例化适配器提上去
         historyvAdapter=new HistoryvAdapter(context);
        //设置适配器
        history_rv.setAdapter(historyvAdapter);
        //xrecyccler 刷新
        //开启上拉加载
        history_rv.setPullRefreshEnabled(true);
        history_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                //网络加载方法
                dohttpHistory();
            }

            @Override
            public void onLoadMore() {
                //上拉加载
                //网络加载方法
                dohttpHistory();
            }
        });
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        history_rv=(XRecyclerView)getView(R.id.history_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        history_rv.setLayoutManager(linearLayoutManager);
        history_cv_leftreturn=(CircleImageView)getView(R.id.history_cv_leftreturn);
        //点击事件
        history_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.history_cv_leftreturn:
                //销毁本页面
                ((ActivityHistory)context).finish();
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

        //请求购票记录网络
        dohttpHistory();
    }

    //请求购票记录网络
    private void dohttpHistory() {
        //创建两个hasmap
        HashMap<String,String> map=new HashMap<>();
        HashMap<String,String> hmap=new HashMap<>();
        //往链各个hasmap 装东西
        map.put("userId",userId1);
        map.put("sessionId",sessionId1);
        //入参hmap
        hmap.put("page",page);
        hmap.put("count",count);
        //head0rquerget 请求
        HeadOrQuertGet(Http.HISTORY_URL,0,map,hmap);
        //打印id
        Logger.i("历史id", map.get("userId") + "哈哈哈" + map.get("sessionId")+ "入参" +hmap.get("page") + hmap.get("count"));
    }
    //成功方法

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择类型
        switch (type){
            case 0:
                //打印历史数据
                Logger.i("历史数据接口","哈哈哈"+data);
                //newgsOn  data  bean
                HistoryBean historyBean = new Gson().fromJson(data, HistoryBean.class);
                //判断message 网络异常,请联系管理员
                if("网络异常,请联系管理员".equals(historyBean.getMessage())){
                    //吐司网络异常，请联系管理员
                    toast(context,"网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                }else if ("请先登录".equals(historyBean.getMessage())){
                    //吐司网络异常，请联系管理员
                    toast(context, "登录过期,请重新登录");
                    //吐司完直接返回 不往下执行
                    return;
                }

                //去外面设置适配器
                //在这设置集合
                historyvAdapter.setList(historyBean.getResult());
                //下拉刷新关闭
                history_rv.refreshComplete();
                //上拉加载关闭
                history_rv.loadMoreComplete();
                break;
        }
    }

    //失败方法
    @Override
    public void failString(String msg) {
        super.failString(msg);
        //吐司失败
        toast(context,"失败啦");
    }
}
