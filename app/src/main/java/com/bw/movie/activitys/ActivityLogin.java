package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityLoginPersenter;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityLogin(登录页面)
 */
public class ActivityLogin extends BaseActivity<ActivityLoginPersenter> {


    @Override
    public Class<ActivityLoginPersenter> getDelegateClass() {
        return ActivityLoginPersenter.class;
    }
}
