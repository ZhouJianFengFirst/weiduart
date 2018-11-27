package com.bw.movie;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityMainPersenter;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：MainActivity
 */
public class MainActivity extends BaseActivity<ActivityMainPersenter> {

    @Override
    public Class<ActivityMainPersenter> getDelegateClass() {
        return ActivityMainPersenter.class;
    }

}