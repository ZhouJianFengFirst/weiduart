package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityFilmPersenter;

public class ActivityFilm extends BaseActivity<ActivityFilmPersenter> {
    @Override
    public Class<ActivityFilmPersenter> getDelegateClass() {
        return ActivityFilmPersenter.class;
    }
}
