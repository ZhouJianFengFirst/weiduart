package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bw.movie.R;
import com.bw.movie.entity.recommendBean;

import java.util.ArrayList;
import java.util.List;

public class recommendAdapter extends BaseAdapter{
    private Context context;
    private List<com.bw.movie.entity.recommendBean.ResultBean.NearbyCinemaListBean> list=new ArrayList<>();

    public recommendAdapter(Context context) {
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
        if(view==null){
            view = View.inflate(context, R.layout.recommendadapter_item, null);
            myViewHolder = new MyViewHolder();

        }
        return view;
    }

    private class MyViewHolder{


    }
}
