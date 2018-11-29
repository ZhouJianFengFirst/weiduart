package com.bw.movie.adapter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.DiscussBean;

public class DiscussAdapter extends RecycleAdapter<DiscussBean.ResultBean> {
    public DiscussAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.discuss_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, DiscussBean.ResultBean resultBean, int postion) {
        viewHolder.setSimpleDraweViewUrl(R.id.right_cinema_simp, resultBean.getCommentHeadPic());
        viewHolder.setText(R.id.right_cinema_name, resultBean.getCommentUserName());
        viewHolder.setText(R.id.right_cinema_pl, resultBean.getCommentContent());
        viewHolder.setText(R.id.right_cinema_time, resultBean.getCommentTime() + "");
        viewHolder.setText(R.id.right_cinema_zs, resultBean.getGreatNum() + "");
        if (resultBean.getIsGreat() == 1) {//判断是否点过赞
            viewHolder.setImageResource(R.id.image_cinema_z, R.mipmap.cinema_praise_yes);
        } else {
            viewHolder.setImageResource(R.id.image_cinema_z, R.mipmap.cinema_praise_no);
        }
    }
}
