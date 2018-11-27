package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.facebook.drawee.view.SimpleDraweeView;

public    class FragmentMePresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private SimpleDraweeView sdv_head;
    private SimpleDraweeView sdv_inform;
    private TextView tv_nickname;

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
        sdv_head=(SimpleDraweeView)getView(R.id.sdv_head);
        sdv_inform=(SimpleDraweeView)getView(R.id.sdv_inform);
        tv_nickname=(TextView)getView(R.id.tv_nickname);
        //点击事件
        sdv_head.setOnClickListener(this);
        sdv_inform.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()){
            case R.id.sdv_head:
                //吐司这是头像
                Toast.makeText(context,"这是头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sdv_inform:
                //吐司这是通知
                Toast.makeText(context,"这是通知",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
