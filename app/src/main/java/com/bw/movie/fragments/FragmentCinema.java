package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaPresenter;
/**
*作者：高佳宝
*时间：2018/11/27 18:53
*作用：电影院Activity
*/
public class FragmentCinema  extends BaseFragment<FragmentCinemaPresenter>{
    @Override
    public Class<FragmentCinemaPresenter> getDelegateClass() {
        return FragmentCinemaPresenter.class;
    }
}