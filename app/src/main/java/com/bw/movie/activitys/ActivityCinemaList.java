package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityCinemaListPersenter;

public class ActivityCinemaList extends BaseActivity<ActivityCinemaListPersenter> {
    @Override
    public Class<ActivityCinemaListPersenter> getDelegateClass() {
        return ActivityCinemaListPersenter.class;
    }
}
