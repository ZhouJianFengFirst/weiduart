package com.bw.movie.adapter;

import android.support.v4.view.ViewPager;

public abstract class BasePageChangeAdapter implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public abstract void onPageSelected(int i);

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
