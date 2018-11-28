package com.bw.movie.adapter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.HortMovieEntity;

public class MovieRecyAdapter extends RecycleAdapter<HortMovieEntity.ResultBean> {


    public MovieRecyAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hortmovie_image;
    }

    @Override
    protected void convert(ViewHolder viewHolder, HortMovieEntity.ResultBean resultBean, int postion) {
        viewHolder.setSimpleDraweViewUrl(R.id.sm_hortmovie, resultBean.getImageUrl());
    }
}
