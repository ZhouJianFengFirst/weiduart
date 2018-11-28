package com.bw.movie.persenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityInform;
import com.bw.movie.mvp.view.AppDelegate;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityInformPersenter(我的通知页)
 * */

//继承APPDelegate
public class ActivityInformPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView inform_message;
    private RecyclerView inform_rv;
    private CircleImageView inform_cv_leftreturn;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_inform;
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
        inform_message=(TextView)getView(R.id.inform_message);
        inform_rv=(RecyclerView)getView(R.id.inform_rv);
        inform_cv_leftreturn=(CircleImageView)getView(R.id.inform_cv_leftreturn);
        //点击事件
        inform_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.inform_cv_leftreturn:
                //销毁本页面
                ((ActivityInform)context).finish();
                break;
        }
    }
}
