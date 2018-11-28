package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityOpinion;
import com.bw.movie.activitys.ActivityOpinionSuccess;
import com.bw.movie.mvp.view.AppDelegate;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinionPersenter(我的意见反馈页面)
 * */

//继承APPDelegate
public class ActivityOpinionPersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private EditText opinion_et_text;
    private TextView opinion_commit;
    private CircleImageView opinion_cv_leftreturn;

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_opinion;
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
        opinion_et_text=(EditText)getView(R.id.opinion_et_text);
        opinion_commit=(TextView)getView(R.id.opinion_commit);
        opinion_cv_leftreturn=(CircleImageView)getView(R.id.opinion_cv_leftreturn);
        //点击事件
        opinion_commit.setOnClickListener(this);
        opinion_cv_leftreturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch(view.getId()){
            case R.id.opinion_commit:
                //吐司提交
//                toast(context,"提交");
                //强转上下文跳转
                context.startActivity(new Intent(context, ActivityOpinionSuccess.class));
                break;
            case R.id.opinion_cv_leftreturn:
                //销毁页面强转
                ((ActivityOpinion)context).finish();
                break;
        }
    }
}
