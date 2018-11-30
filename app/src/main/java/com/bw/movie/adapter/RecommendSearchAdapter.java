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
import com.bw.movie.entity.recommendBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
*作者：gaojiabao
*时间：2018/11/28 8:43
*作用：影院搜索适配器
*/
public class RecommendSearchAdapter extends BaseAdapter {
    private Context context;
    private List<CinemaSearchBean.ResultBean> list = new ArrayList<>();
    private SetOnHeart setOnHeart;

    public RecommendSearchAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CinemaSearchBean.ResultBean> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.recommendadapter_item, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.item_re_img1=view.findViewById(R.id.item_re_img1);
            myViewHolder.item_re_te1=view.findViewById(R.id.item_re_te1);
            myViewHolder.item_re_te2=view.findViewById(R.id.item_re_te2);
            myViewHolder.item_re_te3=view.findViewById(R.id.item_re_te3);
            myViewHolder.item_re_simp=view.findViewById(R.id.item_re_simp);
            myViewHolder.lin_cinema_lay=view.findViewById(R.id.lin_cinema_lay);
            view.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.item_re_simp.setImageURI(Uri.parse(list.get(i).getLogo()));
        myViewHolder.item_re_te1.setText(list.get(i).getName());
        myViewHolder.item_re_te2.setText(list.get(i).getAddress());
        //设置是否喜欢
        if(list.get(i).isFollowCinema()){
            myViewHolder.item_re_img1.setImageResource(R.mipmap.cinema_islike);
        }else {
            myViewHolder.item_re_img1.setImageResource(R.drawable.gray_heart);
        }
        //条目点击事件
        myViewHolder.lin_cinema_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityCinemaDetails.class);
                intent.putExtra("id",list.get(i).getId()+"");
                //跳转影院详情
                context.startActivity(intent);
            }
        });
        //喜欢点击事件
        myViewHolder.item_re_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(i).isFollowCinema()){
                    list.get(i).setFollowCinema(false);
                    setOnHeart.success(list);
                }else{
                    list.get(i).setFollowCinema(true);
                    setOnHeart.success(list);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private class MyViewHolder {
        SimpleDraweeView item_re_simp;
        TextView item_re_te1, item_re_te2, item_re_te3;
        ImageView item_re_img1;
        LinearLayout lin_cinema_lay;
    }
    public void result(SetOnHeart setOnHeart){
        this.setOnHeart=setOnHeart;
    }

    public interface SetOnHeart{
        void success(List<CinemaSearchBean.ResultBean> list);
    }
}
