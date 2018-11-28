package com.bw.movie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;

public class ListFilmView extends RelativeLayout {

    private RecyclerView recyview;
    private TextView txtTitle;

    public ListFilmView(Context context) {
        super(context);
        init(context);
    }

    public ListFilmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ListFilmView);
        String string = typedArray.getString(R.styleable.ListFilmView_Title);
        setTitle(string);
    }

    public ListFilmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_iteam_film, null);
        initWeight(view);
        addView(view);
    }

    public void initWeight(View view) {
        recyview = (RecyclerView) view.findViewById(R.id.recy_hortmovie);
        txtTitle = (TextView) view.findViewById(R.id.txt_hortmovie);
    }

    public void setAdapter(RecycleAdapter adapter, @Nullable RecyclerView.LayoutManager layout) {
        recyview.setAdapter(adapter);
        recyview.setLayoutManager(layout);
    }

    public void setTitle(String title) {
        txtTitle.setText(title);
    }
}
