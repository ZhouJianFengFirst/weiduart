package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityCinemaDetaolsListPersenter;

public class ActivityCinemaDetaolsList extends BaseActivity<ActivityCinemaDetaolsListPersenter> {
    @Override
    public Class<ActivityCinemaDetaolsListPersenter> getDelegateClass() {
        return ActivityCinemaDetaolsListPersenter.class;
    }
}
