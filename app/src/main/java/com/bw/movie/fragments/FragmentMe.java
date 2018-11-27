package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentMePresenter;

public    class FragmentMe extends BaseFragment<FragmentMePresenter>{
    @Override
    public Class<FragmentMePresenter> getDelegateClass() {
        return FragmentMePresenter.class;
    }
}