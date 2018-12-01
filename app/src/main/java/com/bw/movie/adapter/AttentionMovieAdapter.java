package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityCinemaDetails;
import com.bw.movie.entity.CinemaSearchBean;
import com.bw.movie.entity.MessageSelectBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
*作者：mafuyan
*时间：2018/12/1
*作用：关注页面电影适配器
*/
public class AttentionMovieAdapter extends BaseAdapter {
    //分开封装集合和上下文 实例化集合
    private Context context;

    public AttentionMovieAdapter(Context context) {
        this.context = context;
    }

    private List<MessageSelectBean.ResultBean.MovieListBean> movieListBeans=new ArrayList<>();

    //改成设置集合方法
    public void setList(List<MessageSelectBean.ResultBean.MovieListBean> movieListBeans) {
        this.movieListBeans = movieListBeans;
        //刷新适配器
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieListBeans.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.attention_film_item, null);
            myViewHolder = new MyViewHolder();
           //内部类获取控件
            myViewHolder.attention_film_img=view.findViewById(R.id.attention_film_img);
            myViewHolder.attention_film_name=view.findViewById(R.id.attention_film_name);
            myViewHolder.attention_film_desc=view.findViewById(R.id.attention_film_desc);
            myViewHolder.attention_film_date=view.findViewById(R.id.attention_film_date);
            //设置内部类
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //重新赋值
        myViewHolder.attention_film_img.setImageURI(movieListBeans.get(i).getImageUrl());
        myViewHolder.attention_film_name.setText(movieListBeans.get(i).getName());
        myViewHolder.attention_film_desc.setText(movieListBeans.get(i).getSummary());
        //日期加个空字符串
        myViewHolder.attention_film_date.setText(movieListBeans.get(i).getReleaseTime()+"");

        return view;
    }

    public class MyViewHolder {
        SimpleDraweeView attention_film_img;
        TextView attention_film_name;
        TextView attention_film_desc;
        TextView attention_film_date;
    }

}
