package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityFilmDetailsPersenter;

import cn.jzvd.Jzvd;

public class ActivityFilmDetails extends BaseActivity<ActivityFilmDetailsPersenter> {
    @Override
    public Class<ActivityFilmDetailsPersenter> getDelegateClass() {
        return ActivityFilmDetailsPersenter.class;
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
