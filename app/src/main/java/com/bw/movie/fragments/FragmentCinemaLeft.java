package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaLeftPresenter;
import com.bw.movie.persenter.FragmentCinemaPresenter;
/**
*作者：gaojiabao
*时间：2018/11/28 18:39
*作用：影院详情
*/
public class FragmentCinemaLeft extends BaseFragment<FragmentCinemaLeftPresenter> {
    @Override
    public Class<FragmentCinemaLeftPresenter> getDelegateClass() {
        return FragmentCinemaLeftPresenter.class;
    }
}