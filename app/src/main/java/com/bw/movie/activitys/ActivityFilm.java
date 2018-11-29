package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.FilmActivityPersenter;

public class ActivityFilm extends BaseActivity<FilmActivityPersenter> {
    @Override
    public Class<FilmActivityPersenter> getDelegateClass() {
        return FilmActivityPersenter.class;
    }
}
