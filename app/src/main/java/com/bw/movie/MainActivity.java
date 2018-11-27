package com.bw.movie;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityMainPersenter;

public class MainActivity extends BaseActivity<ActivityMainPersenter> {

    @Override
    public Class<ActivityMainPersenter> getDelegateClass() {
        return ActivityMainPersenter.class;
    }
}