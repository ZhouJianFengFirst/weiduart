package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.entity.SelectFilmBean;
import com.bw.movie.utils.Logger;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：mafuyan
 * 时间：2018/12/1
 * 作用：关注页面影院适配器
 */
public class AttentionCinemaAdapter extends BaseAdapter {
    //分开封装集合和上下文 实例化集合
    private Context context;

    public AttentionCinemaAdapter(Context context) {
        this.context = context;
    }

    private List<SelectFilmBean.ResultBean> resultBeans=new ArrayList<>();

    //改成设置集合方法
    public void setList(List<SelectFilmBean.ResultBean> resultBeans) {
        this.resultBeans = resultBeans;
        //刷新适配器
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        Logger.i("第一个集合长度",resultBeans.size()+"");
        return resultBeans.size();

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
            view = View.inflate(context, R.layout.attention_cinema_item, null);
            myViewHolder = new MyViewHolder();
            //内部类获取控件
            myViewHolder.attention_cinema_img = view.findViewById(R.id.attention_cinema_img);
            myViewHolder.attention_cinema_name = view.findViewById(R.id.attention_cinema_name);
            myViewHolder.attention_cinema_desc = view.findViewById(R.id.attention_cinema_desc);
            //设置内部类
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        //重新赋值
        myViewHolder.attention_cinema_img.setImageURI(resultBeans.get(i).getLogo());
        myViewHolder.attention_cinema_name.setText(resultBeans.get(i).getName());
        myViewHolder.attention_cinema_desc.setText(resultBeans.get(i).getAddress());

        return view;
    }

    //公共的内部类
    public class MyViewHolder {
        SimpleDraweeView attention_cinema_img;
        TextView attention_cinema_name;
        TextView attention_cinema_desc;
    }

}
