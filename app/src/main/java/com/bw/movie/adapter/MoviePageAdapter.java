package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.entity.HortMovieEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public    class MoviePageAdapter extends PagerAdapter {

    private List<HortMovieEntity.ResultBean> list = new ArrayList<>();
    private Context context;

    public MoviePageAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<HortMovieEntity.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hortmovie_image,null);
        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.sm_hortmovie);
        simpleDraweeView.setImageURI(list.get(position).getImageUrl());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
