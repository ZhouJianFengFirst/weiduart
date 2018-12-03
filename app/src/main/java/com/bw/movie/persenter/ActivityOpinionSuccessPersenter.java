package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityOpinionSuccess;
import com.bw.movie.mvp.view.AppDelegate;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinionSuccessPersenter(我的反馈成功页面)
 * */

//继承APPDelegate
public class ActivityOpinionSuccessPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private CircleImageView opinionsuccess_cv_leftreturn;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_opinion_success;
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
        //吐司意见反馈成功并且跳转反馈成功页面
        toast(context,"意见反馈成功");
    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        opinionsuccess_cv_leftreturn=(CircleImageView)getView(R.id.opinionsuccess_cv_leftreturn);
        //点击事件
        opinionsuccess_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch(view.getId()){
            case R.id.opinionsuccess_cv_leftreturn:
                //销毁本页面
                ((ActivityOpinionSuccess)context).finish();
                break;
        }
    }
}
