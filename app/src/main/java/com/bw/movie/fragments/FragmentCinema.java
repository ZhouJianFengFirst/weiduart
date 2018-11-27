package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaPresenter;

public    class FragmentCinema  extends BaseFragment<FragmentCinemaPresenter>{
    @Override
    public Class<FragmentCinemaPresenter> getDelegateClass() {
        return FragmentCinemaPresenter.class;
    }
}