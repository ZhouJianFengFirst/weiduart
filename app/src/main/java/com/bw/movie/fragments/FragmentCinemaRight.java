package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaLeftPresenter;
import com.bw.movie.persenter.FragmentCinemaRightPresenter;
/**
*作者：gaojiabao
*时间：2018/11/28 18:39
*作用：影院评论
*/
public class FragmentCinemaRight extends BaseFragment<FragmentCinemaRightPresenter> {
    @Override
    public Class<FragmentCinemaRightPresenter> getDelegateClass() {
        return FragmentCinemaRightPresenter.class;
    }
}