package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院评论
 */
public class FragmentCinemaRightPresenter extends AppDelegate {

    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema_right;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();

    }
    //找控件的方法
    private void initwidget() {
        
    }

  
}
