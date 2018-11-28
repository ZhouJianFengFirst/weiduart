package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityHistoryPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityHistory(我的购票记录页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityHistory extends BaseActivity<ActivityHistoryPersenter> {

    @Override
    public Class<ActivityHistoryPersenter> getDelegateClass() {
        //返回页面的Persenter
        return ActivityHistoryPersenter.class;
    }
}
