package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityResetPwdPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityResetPwd(我的重置密码页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityResetPwd extends BaseActivity<ActivityResetPwdPersenter> {

    @Override
    public Class<ActivityResetPwdPersenter> getDelegateClass() {
        //返回本页面的Persenter类
        return ActivityResetPwdPersenter.class;
    }
}
