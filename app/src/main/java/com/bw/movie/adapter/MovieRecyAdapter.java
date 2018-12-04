package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityFilm;
import com.bw.movie.activitys.ActivityFilmDetails;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.HortMovieEntity;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class MovieRecyAdapter extends RecycleAdapter<HortMovieEntity.ResultBean> {

    private Context context;

    public MovieRecyAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hortmovie_image;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final HortMovieEntity.ResultBean resultBean, int postion) {
        SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.sm_hortmovie);
        viewHolder.setSimpleDraweViewUrl(R.id.sm_hortmovie, resultBean.getImageUrl())
                .setText(R.id.txt_title, resultBean.getName());
        viewHolder.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityFilmDetails.class);
                intent.putExtra("movieId", resultBean.getId());
                ((MainActivity) context).startActivity(intent);
            }
        }, R.id.sm_hortmovie);
    }
}