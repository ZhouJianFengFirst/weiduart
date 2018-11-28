package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityNewestPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityNewest(我的最新版本页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityNewest extends BaseActivity<ActivityNewestPersenter> {


    @Override
    public Class<ActivityNewestPersenter> getDelegateClass() {
        //返回本页面的persenter类
        return ActivityNewestPersenter.class ;
    }
}
