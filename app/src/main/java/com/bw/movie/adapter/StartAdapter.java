package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bw.movie.R;

import java.util.List;

/**
 * 作者:xujaihui
 * 时间:2018/11/27
 * 作用:StartAdapter(引导页adapter)
 */
public class StartAdapter extends PagerAdapter {
    private List<Integer> imgs;

    private Context mcontext;
    private String[] desc1 = {"一场电影", "一场电影", "一场电影", "八维移动通信学院作品"};
    private String[] desc2 = {"净化你的灵魂", "看遍人生百态", "荡漾你的心灵", "带你开启一段美好的电影之旅"};

    public StartAdapter(List<Integer> imgs, Context mcontext) {
        this.imgs = imgs;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = View.inflate(mcontext, R.layout.layout_start_item, null);
        ImageView image = (ImageView) inflate.findViewById(R.id.gm_img_start);
        TextView txtdesc1 = (TextView) inflate.findViewById(R.id.gm_txt_describe1);
        TextView txtdesc2 = (TextView) inflate.findViewById(R.id.gm_txt_describe2);
        image.setImageResource(imgs.get(position));
        txtdesc1.setText(desc1[position]);
        txtdesc2.setText(desc2[position]);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
