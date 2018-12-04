package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.entity.FilmMessageEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {


    private FilmMessageEntity bean;
    private Context context;


    public void setBean(FilmMessageEntity bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public VideoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        List<FilmMessageEntity.ResultBean.ShortFilmListBean> shortFilmList = bean.getResult().getShortFilmList();
        viewHolder.videoplayer.setUp(shortFilmList.get(i).getVideoUrl(), bean.getResult().getName(), Jzvd.SCREEN_WINDOW_NORMAL);
        Picasso.with(context).load(shortFilmList.get(i).getImageUrl()).fit().into(viewHolder.videoplayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return bean.getResult().getShortFilmList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public JzvdStd videoplayer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoplayer = (JzvdStd) itemView.findViewById(R.id.videoplayer);
        }
    }
}
