package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityAttention;
import com.bw.movie.adapter.AttentionCinemaAdapter;
import com.bw.movie.adapter.AttentionMovieAdapter;
import com.bw.movie.entity.SelectFilmBean;
import com.bw.movie.entity.SelectMovieBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.Logger;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityAttentionPersenter(我的关注页面)
 */

//继承APPDelegate
public class ActivityAttentionPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView attention_tv_film;
    private TextView attention_tv_cinema;
    private XListView attention_xlv;
    private CircleImageView attention_cv_leftreturn;
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
    private AttentionMovieAdapter attentionMovieAdapter;
    private AttentionCinemaAdapter attentionCinemaAdapter;
    private int page = 1;
    private String count = "10";
    private boolean isRe = false;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_attention;
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
        //实例化适配器 提上去
        attentionMovieAdapter = new AttentionMovieAdapter(context);
        //实例化影院适配器
        attentionCinemaAdapter = new AttentionCinemaAdapter(context);
        //开启上拉加载为true
        attention_xlv.setPullLoadEnable(true);
        //监听上拉刷新下拉刷新
        attention_xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
//                //下拉刷新
//                //请求数据
                if (isRe) {
                    dohttpAttentionTwo();
                } else if (!isRe) {
                    dohttpAttentionOne();
                }
            }

            @Override
            public void onLoadMore() {

                //上拉加载
                //请求数据
                if (isRe) {
                    dohttpAttentionTwo();
                } else if (!isRe) {
                    dohttpAttentionOne();
                }
//                //page++页面加加
//                page++;
//                Logger.i("页数",page+"");
            }
        });
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        attention_tv_film = (TextView) getView(R.id.attention_tv_film);
        attention_tv_cinema = (TextView) getView(R.id.attention_tv_cinema);
        attention_xlv = (XListView) getView(R.id.attention_xlv);
        attention_cv_leftreturn = (CircleImageView) getView(R.id.attention_cv_leftreturn);

        //点击事件
        attention_tv_film.setOnClickListener(this);
        attention_tv_cinema.setOnClickListener(this);
        attention_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.attention_tv_film:
                //关注one
                dohttpAttentionOne();
                isRe = false;
                //吐司
//                toast(context,"电影");
                //给控件重新赋值 给背景改变 设置背景resource
                attention_tv_film.setBackgroundResource(R.drawable.purplechange);
                //字体变颜色黑色
                attention_tv_film.setTextColor(Color.WHITE);
                //在给本空间设置背景和字体颜色
                //给控件重新赋值 给背景改变 设置背景resource
                attention_tv_cinema.setBackgroundResource(R.drawable.square_gray);
                //字体变颜色黑色
                attention_tv_cinema.setTextColor(Color.BLACK);
                break;
            case R.id.attention_tv_cinema:
                //关注two
                dohttpAttentionTwo();
                isRe = true;
                //吐司
//                toast(context,"影院");
                //给控件重新赋值 给背景改变 设置背景resource
                attention_tv_film.setBackgroundResource(R.drawable.square_gray);
                //字体变颜色黑色
                attention_tv_film.setTextColor(Color.BLACK);
                //在给本空间设置背景和字体颜色
                //给控件重新赋值 给背景改变 设置背景resource
                attention_tv_cinema.setBackgroundResource(R.drawable.purplechange);
                //字体变颜色黑色
                attention_tv_cinema.setTextColor(Color.WHITE);
                break;
            case R.id.attention_cv_leftreturn:
                //销毁强转上下文页面
                ((ActivityAttention) context).finish();
                break;
        }
    }

    //关注查询2
    private void dohttpAttentionTwo() {
        //new hasmap
        HashMap<String, String> map = new HashMap<>();
        //往map里面存值
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        HashMap<String, String> qmap = new HashMap<>();
        qmap.put("page", String.valueOf(page));
        qmap.put("count", count);
        //请求get字符串方法 传网址类型随机数0,1
        //调用head请求方法传接口的数据,传类型和map
        HeadOrQuertGet(Http.SELECTFILM_URL, 1, map, qmap);

        Logger.i("第二个集合id", map.get("userId") + "哈哈哈" + map.get("sessionId") + qmap.get("page") + qmap.get("count"));
        //设置停止下拉刷新
        attention_xlv.stopRefresh();
        attention_xlv.stopLoadMore();
    }

    //关注查询1
    private void dohttpAttentionOne() {
        //new hasmap
        HashMap<String, String> map = new HashMap<>();
        //往map里面存值
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        HashMap<String, String> qmap = new HashMap<>();
        qmap.put("page", String.valueOf(page));
        qmap.put("count", count);
        //请求get字符串方法 传网址类型随机数0,1
        //调用head请求方法传接口的数据,传类型和map
        HeadOrQuertGet(Http.SELECTMOVIE_URL, 0, map, qmap);
        Logger.i("第一个集合id", map.get("userId") + "哈哈哈" + map.get("sessionId") + qmap.get("page") + qmap.get("count"));
        //设置停止下拉刷新
        attention_xlv.stopRefresh();
        attention_xlv.stopLoadMore();
    }

    //调用成功方法
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        //选择判断tyep上面的类型
        switch (type) {
            case 0:
                Logger.i("集合1", data);
                //new gson from
                SelectMovieBean selectMovieBean = new Gson().fromJson(data, SelectMovieBean.class);
                //判断message 网络异常,请联系管理员
                if ("网络异常,请联系管理员".equals(selectMovieBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("请先登录".equals(selectMovieBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "登录过期,请重新登录");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("查询成功".equals(selectMovieBean.getMessage())) {
                    //获取对象集合
                    List<SelectMovieBean.ResultBean> result = selectMovieBean.getResult();
                    attention_xlv.setAdapter(attentionMovieAdapter);
                    //去外面实例化设置适配器
                    //在这设置集合
                    attentionMovieAdapter.setList(result);
                }
                break;
            case 1:
                Logger.i("集合2", data);
                //new gson from
                SelectFilmBean selectFilmBean = new Gson().fromJson(data, SelectFilmBean.class);
                //判断message 网络异常,请联系管理员
                if ("网络异常,请联系管理员".equals(selectFilmBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("请先登录".equals(selectFilmBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "登录过期,请重新登录");
                    //吐司完直接返回 不往下执行
                    return;
                } else if ("查询成功".equals(selectFilmBean.getMessage())) {
                    //获取对象集合
                    List<SelectFilmBean.ResultBean> resultBean1 = selectFilmBean.getResult();
                    //去外面设置适配器
                    Logger.i("集合长度2", resultBean1.size() + "");
                    attention_xlv.setAdapter(attentionCinemaAdapter);
                    //在这设置集合
                    attentionCinemaAdapter.setList(resultBean1);
                }
                break;

        }
    }

    //失败方法
    @Override
    public void failString(String msg) {
        super.failString(msg);
        //吐司失败
        toast(context, "失败");
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
        //在onResume里传过来的方法里请求一个默认的网络请求和适配器
        //请求数据
        dohttpAttentionOne();
        //设置电影适配器
        attention_xlv.setAdapter(attentionMovieAdapter);
    }
}
