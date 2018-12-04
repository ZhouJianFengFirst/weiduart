package com.bw.movie.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

public class StillAdapter extends RecycleAdapter<String> {

    public StillAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_film_stil_image;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String s, int postion) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) viewHolder.getView(R.id.sm_film_image);
        simpleDraweeView.setImageURI(s);
    }
}