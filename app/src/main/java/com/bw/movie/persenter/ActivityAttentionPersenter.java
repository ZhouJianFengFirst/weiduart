package com.bw.movie.persenter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityAttention;
import com.bw.movie.mvp.view.AppDelegate;
import com.example.xlistviewlib.XListView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityAttentionPersenter(我的关注页面)
 * */

//继承APPDelegate
public class ActivityAttentionPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView attention_tv_film;
    private TextView attention_tv_cinema;
    private XListView attention_xlv;
    private CircleImageView attention_cv_leftreturn;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_attention;
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

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        attention_tv_film=(TextView)getView(R.id.attention_tv_film);
        attention_tv_cinema=(TextView)getView(R.id.attention_tv_cinema);
        attention_xlv=(XListView)getView(R.id.attention_xlv);
        attention_cv_leftreturn=(CircleImageView)getView(R.id.attention_cv_leftreturn);

        //点击事件
        attention_tv_film.setOnClickListener(this);
        attention_tv_cinema.setOnClickListener(this);
        attention_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.attention_tv_film:
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
                ((ActivityAttention)context).finish();
                break;
        }
    }
}
