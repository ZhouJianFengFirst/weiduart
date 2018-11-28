package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaLeftPresenter;
import com.bw.movie.persenter.FragmentCinemaPresenter;

public class FragmentCinemaLeft extends BaseFragment<FragmentCinemaLeftPresenter> {
    @Override
    public Class<FragmentCinemaLeftPresenter> getDelegateClass() {
        return FragmentCinemaLeftPresenter.class;
    }
}