package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;

public    class BackGroundPageAdapter extends PagerAdapter {

    private int page=0;
    private Context context;

    public BackGroundPageAdapter(Context context) {
        this.context = context;
    }

    public void setPage(int page) {
        this.page = page;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return page;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        return container;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }
}
