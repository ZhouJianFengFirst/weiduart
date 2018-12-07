package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityFilmQueryCinemaPersenter;
import com.bw.movie.utils.SpUtil;

public class ActivityFilmQueryCinema extends BaseActivity<ActivityFilmQueryCinemaPersenter> {


    @Override
    public Class<ActivityFilmQueryCinemaPersenter> getDelegateClass() {
        return ActivityFilmQueryCinemaPersenter.class;
    }

}
