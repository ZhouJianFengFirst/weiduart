package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentFilmPresenter;

public    class FragmentFilm  extends BaseFragment<FragmentFilmPresenter> {
    @Override
    public Class<FragmentFilmPresenter> getDelegateClass() {
        return FragmentFilmPresenter.class;
    }
}