package com.bw.movie.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.bw.movie.R;

public  class ViewPage3D   extends RelativeLayout {

    private Context context;
    private ViewPager viewPager;
    public ViewPage3D(Context context) {
        super(context);
        init(context);
    }

    public ViewPage3D(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewPage3D(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_3d_viewpage,null);
        initWeight(view);
        this.setClipChildren(false);
        addView(view);
    }

    private void initWeight(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp_banner);
    }

    public void setAdapter(PagerAdapter3D pagerAdapter3D){
        viewPager.setAdapter(pagerAdapter3D);
    }

    public void setPageTransformer(RotationPageTransformer rotation,int OffscreenPageLimit,int PageMargin){
        viewPager.setPageTransformer(true,rotation);
        viewPager.setOffscreenPageLimit(OffscreenPageLimit);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        viewPager.setPageMargin(PageMargin);//设置两个Page之间的距离
    }
}
