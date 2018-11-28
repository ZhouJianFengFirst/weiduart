package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityCinemaDetailsPersenter;
/**
 *作者：gaojiabao
 *时间：2018/11/28 8:51
 *作用：影院详情Activity
 */
public class ActivityCinemaDetails extends BaseActivity<ActivityCinemaDetailsPersenter> {

    @Override
    public Class<ActivityCinemaDetailsPersenter> getDelegateClass() {
        return ActivityCinemaDetailsPersenter.class;
    }
}