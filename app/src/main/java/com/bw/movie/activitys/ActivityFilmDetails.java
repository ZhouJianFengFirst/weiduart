package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityFilmDetailsPersenter;

public    class ActivityFilmDetails extends BaseActivity<ActivityFilmDetailsPersenter> {
    @Override
    public Class<ActivityFilmDetailsPersenter> getDelegateClass() {
        return ActivityFilmDetailsPersenter.class;
    }
}
