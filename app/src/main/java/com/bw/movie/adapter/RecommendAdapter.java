package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.entity.recommendBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends BaseAdapter {
    private Context context;
    private List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> list = new ArrayList<>();

    public RecommendAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<recommendBean.ResultBean.NearbyCinemaListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.recommendadapter_item, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.item_re_img1=view.findViewById(R.id.item_re_img1);
            myViewHolder.item_re_te1=view.findViewById(R.id.item_re_te1);
            myViewHolder.item_re_te2=view.findViewById(R.id.item_re_te2);
            myViewHolder.item_re_te3=view.findViewById(R.id.item_re_te3);
            myViewHolder.item_re_simp=view.findViewById(R.id.item_re_simp);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }


        return view;
    }

    private class MyViewHolder {
        SimpleDraweeView item_re_simp;
        TextView item_re_te1, item_re_te2, item_re_te3;
        ImageView item_re_img1;
    }
}
