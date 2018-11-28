package com.bw.movie.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;

public class ListFilmView extends RelativeLayout {

    private ViewPager viewPager;
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

    public void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.view_iteam_film, null);
        initWeight(view);
        addView(view);
    }

    public void initWeight(View view){
        viewPager = (ViewPager) view.findViewById(R.id.recy_hortmovie);
        txtTitle = (TextView) view.findViewById(R.id.txt_hortmovie);
    }

    public void setAdapter(PagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    public void setTitle(String title){
        txtTitle.setText(title);
    }
}
