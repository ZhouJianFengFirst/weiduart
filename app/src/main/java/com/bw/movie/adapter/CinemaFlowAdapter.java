package com.bw.movie.adapter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.CinemaFlowBean;
/**
*作者：gaojiabao
*时间：2018/11/30 9:10
*作用：影院详情 轮播适配器
*/
public class CinemaFlowAdapter extends RecycleAdapter<CinemaFlowBean.ResultBean> {
    public CinemaFlowAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cinema_flow_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, CinemaFlowBean.ResultBean resultBean, int postion) {
        viewHolder.setSimpleDraweViewUrl(R.id.simp_cinema_flow, resultBean.getImageUrl());
        viewHolder.setText(R.id.text_cinema_flow1,resultBean.getName());
        viewHolder.setText(R.id.text_cinema_flow2,resultBean.getDuration());
    }
}
