package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityBeginPersenter;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityBeginPersenter(欢迎页)
 */
public class ActivityBegin extends BaseActivity<ActivityBeginPersenter> {


    @Override
    public Class<ActivityBeginPersenter> getDelegateClass() {
        return ActivityBeginPersenter.class;
    }
}
