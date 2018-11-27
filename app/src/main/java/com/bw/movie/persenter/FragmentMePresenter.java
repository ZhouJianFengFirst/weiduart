package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.facebook.drawee.view.SimpleDraweeView;

public    class FragmentMePresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SimpleDraweeView me_sdv_head;
    private SimpleDraweeView me_sdv_inform;
    private TextView me_tv_nickname;
    private LinearLayout me_liner_message;
    private LinearLayout me_Liner_attention;
    private LinearLayout me_Liner_history;
    private LinearLayout me_Liner_opinion;
    private LinearLayout me_Liner_newest;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
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

    //初始化数据方法
    private void initwidget() {
        //获取控件强转提上去
        me_sdv_head=(SimpleDraweeView)getView(R.id.me_sdv_head);
        me_sdv_inform=(SimpleDraweeView)getView(R.id.me_sdv_inform);
        me_tv_nickname=(TextView)getView(R.id.me_tv_nickname);
        me_liner_message=(LinearLayout)getView(R.id.me_liner_message);
        me_Liner_attention=(LinearLayout)getView(R.id.me_Liner_attention);
        me_Liner_history=(LinearLayout)getView(R.id.me_Liner_history);
        me_Liner_opinion=(LinearLayout)getView(R.id.me_Liner_opinion);
        me_Liner_newest=(LinearLayout)getView(R.id.me_Liner_newest);
        //点击事件
        me_sdv_head.setOnClickListener(this);
        me_sdv_inform.setOnClickListener(this);
        me_liner_message.setOnClickListener(this);
        me_Liner_attention.setOnClickListener(this);
        me_Liner_history.setOnClickListener(this);
        me_Liner_opinion.setOnClickListener(this);
        me_Liner_newest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.me_sdv_head:
                //吐司这是头像
                Toast.makeText(context,"这是头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_sdv_inform:
                //吐司这是通知
                Toast.makeText(context,"这是通知",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_liner_message:
                //吐司这是我的信息
                Toast.makeText(context,"这是我的信息",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_Liner_attention:
                //吐司这是我的关注
                Toast.makeText(context,"这是我的关注",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_Liner_history:
                //吐司这是我的购票记录
                Toast.makeText(context,"这是我的购票记录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_Liner_opinion:
                //吐司这是我的意见反馈
                Toast.makeText(context,"这是我的意见反馈",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_Liner_newest:
                //吐司这是我的最新版本
                Toast.makeText(context,"这是我的最新版本",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
