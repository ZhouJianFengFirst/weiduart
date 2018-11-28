package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityInformPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityInform(我的通知页)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityInform extends BaseActivity<ActivityInformPersenter> {

    @Override
    public Class<ActivityInformPersenter> getDelegateClass() {
        //返回本页面的Persenter
        return ActivityInformPersenter.class;
    }
}
