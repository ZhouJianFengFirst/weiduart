package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaLeftPresenter;
import com.bw.movie.persenter.FragmentCinemaRightPresenter;

public class FragmentCinemaRight extends BaseFragment<FragmentCinemaRightPresenter> {
    @Override
    public Class<FragmentCinemaRightPresenter> getDelegateClass() {
        return FragmentCinemaRightPresenter.class;
    }
}